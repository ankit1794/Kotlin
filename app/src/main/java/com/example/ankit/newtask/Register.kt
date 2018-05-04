package com.example.ankit.newtask

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife
import com.example.ankit.newtask.model.User
import com.example.ankit.newtask.model.UserApiResponse
import com.example.ankit.newtask.retrofit.APIService
import com.example.ankit.newtask.retrofit.Client
import kotlinx.android.synthetic.main.user_registeration.*
import retrofit2.Call
import retrofit2.Response


/**
 * Created by ankit on 14/3/18.
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_registeration)

        ButterKnife.bind(this)

        et_register_name.hint="name"
        et_register_username.hint="username"
        et_register_email.hint="email"
        et_register_password.hint="pasword"
        btn_login.text="LOgin"
        btn_register.text="register"

        //set register title
        title = "User Register"

        btn_register!!.setOnClickListener { v ->
            val user = User(
                    et_register_name.text.toString(),
                    et_register_username.text.toString(),
                    et_register_email.text.toString(),
                    et_register_password.text.toString()
            )
            sendRequest(user)
        }

        btn_login.setOnClickListener { v ->
            val gotoLoginIntent = Intent(this@RegisterActivity,
                    LoginActivity::class.java)
            startActivity(gotoLoginIntent)
        }
    }

    private fun sendRequest(user: User) {

        val apiService = Client.client?.create(APIService::class.java)
        val call = apiService?.registerUser(user)

        call?.enqueue(object : retrofit2.Callback<UserApiResponse> {
            override fun onResponse(call: Call<UserApiResponse>,
                                    response: Response<UserApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "User Registered\n success: "
                            + response.body()?.isSuccess, Toast.LENGTH_SHORT).show()

                    // clear fields
                    et_register_name.setText("")
                    et_register_username.setText("")
                    et_register_email.setText("")
                    et_register_password.setText("")

                    val gotoLoginIntent = Intent(this@RegisterActivity,
                            LoginActivity::class.java)
                    startActivity(gotoLoginIntent)
                }
            }

            override fun onFailure(call: Call<UserApiResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Registration Failed... $t",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }


}