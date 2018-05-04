package com.example.ankit.newtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife
import com.example.ankit.newtask.model.LoginResponse
import com.example.ankit.newtask.retrofit.APIService
import com.example.ankit.newtask.retrofit.Client
import kotlinx.android.synthetic.main.user_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ankit on 14/3/18.
 */
class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_dashboard)

        ButterKnife.bind(this)
        tv_user_name.text = "username"
        tv_user_email.text = "email"
        tv_user_username.text = "usrname"
        btn_logout.text = "logout"
        btn_show_hide_profile.text = "hideProfile"

        val user_token = intent.getStringExtra("token_id")

        //set Activity title
        title = getString(R.string.user_dashboard)

        btn_show_hide_profile!!.setOnClickListener { v ->

            val apiService = Client.client?.create(APIService::class.java)
            val call = apiService?.getProfile(user_token)

            call?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {

                        if (btn_show_hide_profile!!.text == getString(R.string.btn_show_profile)) {
                            //show profile
                            tv_user_name!!.text = response.body()?.user?.name
                            tv_user_username!!.text = response.body()?.user?.username
                            tv_user_email!!.text = response.body()?.user?.email
                            btn_show_hide_profile!!.setText(R.string.btn_hide_profile)
                            //llDashboard!!.visibility = View.VISIBLE
                        } else {
                            //hide profile
                            tv_user_name!!.text = ""
                            tv_user_username!!.text = ""
                            tv_user_email!!.text = ""
                            btn_show_hide_profile!!.setText(R.string.btn_show_profile)
                            //llDashboard!!.visibility = View.GONE
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@DashBoardActivity, "Error: $t",
                            Toast.LENGTH_SHORT).show()
                }
            })
        }

        btn_logout!!.setOnClickListener { v ->
            /***
             * remove user_token from shared preference on logout and make a toast appear
             * to show delete msg
             */
            val preferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            preferences.edit().remove("token").commit()
            Toast.makeText(this, "token deleted Successfully \n$user_token", Toast.LENGTH_SHORT).show()

            /***
             * go to login page on logout
             */
            val gotoLoginIntent = Intent(this@DashBoardActivity, LoginActivity::class.java)
            startActivity(gotoLoginIntent)
            finish()
        }
    }
}