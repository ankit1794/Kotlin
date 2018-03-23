package com.example.ankit.newtask

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ankit.newtask.model.Login
import com.example.ankit.newtask.model.LoginResponse
import com.example.ankit.newtask.retrofit.APIService
import com.example.ankit.newtask.retrofit.Client
import kotlinx.android.synthetic.main.user_login.*
import kotlinx.android.synthetic.main.user_registeration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ankit on 22/3/18.
 */

class LoginActivity : AppCompatActivity() {

   /* @BindView(R.id.et_login_username)
    internal var loginUsername: EditText? = null

    @BindView(R.id.et_login_password)
    internal var loginPassword: EditText? = null

    @BindView(R.id.btn_login)
    internal var btnLogin: Button? = null

    @BindView(R.id.btn_register)
    internal var btnRegister: Button? = null*/

    internal lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_login)

        et_login_username.text="hello"

        var et_login_username = findViewById(R.id.et_login_username) as? EditText
        //var et_login_email= findViewById(R.id.et_lo) as? EditText
        var et_login_password = findViewById(R.id.et_login_password) as? EditText
        //et_login_username.setText("UserName")
        //et_register_email.setText("Email")
        //et_login_password.setText("password")


        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnRegister= findViewById<Button>(R.id.btn_register)



        //ButterKnife.bind(this)
        //set title here
        title = getString(R.string.user_login)

        // define shared pref
        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        /***
         * check if token is already  present.
         * if token exist store it in a string
         * then pass along with intent to the dashboard activity
         */
        if (sharedPref.contains("token")) {
            val user_token = sharedPref.getString("token", "token not found")

            val gotoDashboardIntent = Intent(this@LoginActivity,
                    DashBoardActivity::class.java)
            gotoDashboardIntent.putExtra("token_id", user_token)
            startActivity(gotoDashboardIntent)
            finish()
        }

        /***
         * click event for register button
         */
        btnRegister!!.setOnClickListener { v ->
            val gotoRegisterIntent = Intent(this@LoginActivity,
                    RegisterActivity::class.java)
            startActivity(gotoRegisterIntent)
        }

        /***
         * click event for login button
         */
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

                        /***
                         * get token in a String called user_token
                         * store the token in shared pref
                         * also pass the token in
                         * add Toast for success event
                         */
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
    }
}
