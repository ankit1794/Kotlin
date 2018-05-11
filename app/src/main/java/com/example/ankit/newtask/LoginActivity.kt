package com.example.ankit.newtask

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.ankit.newtask.model.Login
import com.example.ankit.newtask.model.LoginResponse
import com.example.ankit.newtask.retrofit.APIService
import com.example.ankit.newtask.retrofit.Client
import kotlinx.android.synthetic.main.user_login.*
import me.rohanpeshkar.helper.HelperActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/***
 * get token in a String called user_token
 * store the token in shared pref
 * also pass the token in
 * add Toast for success event
 */
/**
 * Created by ankit on 22/3/18.
 */

class LoginActivity : HelperActivity() {


    private var sharedPref: SharedPreferences? = null

    override fun create() {

        et_login_username.hint = "username"
        et_login_password.hint = "password"

        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        btn_register1.setOnClickListener {
            launch(RegisterActivity::class.java)
        }

        btn_login1.setOnClickListener {

            val login = Login(
                    et_login_username.text.toString(),
                    et_login_password.text.toString()
            )

            if (et_login_username.text.toString().isNotEmpty() &&
                    et_login_password.text.toString().isNotEmpty()) {

                val apiService = Client.client?.create(APIService::class.java)
                val call = apiService?.authenticateUser(login)

                call?.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {

                            /***
                             * get token in a String called user_token
                             * store the token in shared pref
                             * also pass the token in
                             * add Toast for success event
                             */
                            val userToken = response.body()?.token
                            sharedPref?.edit()?.putString("token", userToken)?.apply()

                            Toast.makeText(this@LoginActivity,
                                    "User logged in \n success: " + response.body()?.isSuccess
                                            + "\n id: " + response.body()?.user?.name
                                            + "\n Token stored successfully in Shared Pref" + userToken,
                                    Toast.LENGTH_SHORT).show()

                            //go to dashboard
                            val gotoDashboardIntent = Intent(this@LoginActivity,
                                    DashBoardActivity::class.java)
                            gotoDashboardIntent.putExtra("token_id", userToken)
                            startActivity(gotoDashboardIntent)
                            finish()

                            // clear fields
                            et_login_username.setText("")
                            et_login_password.setText("")
                        } else {
                            showToast("Login Credentials are Wrong...")
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error: $t",
                                Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }

    override fun isToolbarPresent(): Boolean = true

    override fun getLayout(): Int = R.layout.user_login

    override fun getActivity(): Activity = this
}

