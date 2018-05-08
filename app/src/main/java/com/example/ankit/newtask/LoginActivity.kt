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

/**
 * Created by ankit on 22/3/18.
 */

class LoginActivity : HelperActivity() {

    private var sharedPref: SharedPreferences? = null


    override fun create() {

        setTitle("Kotlin")

        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val login = Login(
                et_login_username.text.toString(),
                et_login_password.text.toString()
        )

        btn_register1.setOnClickListener {
            launch(RegisterActivity::class.java)
        }

        btn_login1.setOnClickListener {

            if (et_login_username.text.toString().isNotEmpty() &&
                    et_login_password.text.toString().isNotEmpty()) {

                val apiService = Client.client?.create(APIService::class.java)
                val call = apiService?.authenticateUser(login)

                call?.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {

                            launch(DashBoardActivity::class.java)


                            /***
                             * get token in a String called user_token
                             * store the token in shared pref
                             * also pass the token in
                             * add Toast for success event
                             */
                            val user_token = response.body()?.token
                            sharedPref?.edit()?.putString("token", user_token)?.apply()

                            Toast.makeText(this@LoginActivity,
                                    "User logged in \n success: " + response.body()?.isSuccess
                                            + "\n name: " + response.body()?.user?.name
                                            + "\n Token stored successfully in Shared Pref" + user_token,
                                    Toast.LENGTH_SHORT).show()

                            //go to dashboard
                            val gotoDashboardIntent = Intent(this@LoginActivity,
                                    DashBoardActivity::class.java)
                            gotoDashboardIntent.putExtra("token_id", user_token)
                            startActivity(gotoDashboardIntent)
                            finish()


                            // clear fields
                            et_login_username.setText("")
                            et_login_password.setText("")
                        } else {
                            Toast.makeText(this@LoginActivity,
                                    "Login Credentials is Wrong...", Toast.LENGTH_SHORT).show()


                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error: $t",
                                Toast.LENGTH_SHORT).show()
                    }
                })
            }

            //launch(RegisterActivity::class.java)
        }
    }

    override fun isToolbarPresent(): Boolean = true


    override fun getLayout(): Int = R.layout.user_login


    override fun getActivity(): Activity = this


    /*override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_login)

        et_login_username.hint="hello"

        var et_login_username = findViewById(R.id.et_login_username) as? EditText
        var et_login_password = findViewById(R.id.et_login_password) as? EditText

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnRegister= findViewById<Button>(R.id.btn_register)



        //ButterKnife.bind(this)
        //set title here
        title = getString(R.string.user_login)

        // define shared pref
        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        */
    /***
     * check if token is already  present.
     * if token exist store it in a string
     * then pass along with intent to the dashboard activity
     *//*
        if (sharedPref.contains("token")) {
            val user_token = sharedPref.getString("token", "token not found")

            val gotoDashboardIntent = Intent(this@LoginActivity,
                    DashBoardActivity::class.java)
            gotoDashboardIntent.putExtra("token_id", user_token)
            startActivity(gotoDashboardIntent)
            finish()
        }

        */
    /***
     * click event for register button
     *//*
        btnRegister!!.setOnClickListener { v ->
            val gotoRegisterIntent = Intent(this@LoginActivity,
                    RegisterActivity::class.java)
            startActivity(gotoRegisterIntent)
        }

        */
    /***
     * click event for login button
     *//*
        btnLogin!!.setOnClickListener { v ->

            val login = Login(
                    et_login_username!!.text.toString(),
                    et_login_password!!.text.toString()
            )

            val apiService = Client.client?.create(APIService::class.java)
            val call = apiService?.authenticateUser(login)

            call?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>,
                                        response: Response<LoginResponse>) {
                    if (response.isSuccessful) {

                        */
    /***
     * get token in a String called user_token
     * store the token in shared pref
     * also pass the token in
     * add Toast for success event
     *//*
                        val userToken = response.body()!!.token
                        sharedPref.edit().putString("token", userToken).commit()

                        Toast.makeText(this@LoginActivity,
                                "User logged in \n success: " + response.body()!!.isSuccess
                                        + "\n id: " + response.body()!!.user
                                        + "\n Token stored successfully in Shared Pref" + userToken,
                                Toast.LENGTH_SHORT).show()

                        //go to dashboard
                        val gotoDashboardIntent = Intent(this@LoginActivity,
                                DashBoardActivity::class.java)
                        gotoDashboardIntent.putExtra("token_id", userToken)
                        startActivity(gotoDashboardIntent)
                        finish()

                        // clear fields
                        et_login_username!!.setText("")
                        et_login_password!!.setText("")
                    } else {
                        Toast.makeText(this@LoginActivity,
                                "Login Credentials is Wrong...", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: $t",
                            Toast.LENGTH_SHORT).show()
                }
            })
        }
    }*/
}
