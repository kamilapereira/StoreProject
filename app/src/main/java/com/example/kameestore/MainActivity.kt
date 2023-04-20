package com.example.kameestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val signIn = findViewById<Button>(R.id.signIn)
        val createAccount = findViewById<Button>(R.id.createAccount)

        signIn.setOnClickListener{
            val usernameString = username.text.toString()
            val passwordString = password.text.toString()
            val requestString = """{
                    "username": "${usernameString}",
                    "password": "${passwordString}"
                    }""".trimMargin()
            Log.i("MAIN_ACT", "Request Body: $requestString")
            val request = Request.Builder()
                .url("https://fakestoreapi.com/auth/login")
                .post(requestString.toRequestBody())
                .header("Content-Type", "application/json")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("MAIN_ACT", "Failure: $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string()
                        Log.i("MAIN_ACT", "Success: $requestString")
                    } else {
                        Handler(Looper.getMainLooper()).post{
                            Toast.makeText(this@MainActivity, "Unsuccessful ${response.code}", Toast.LENGTH_LONG).show()
                        }
                        Log.i("MAIN_ACT", "Unsuccessful ${response.code} ${response.body?.string()}")
                    }
                }
            })
        }

        createAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }
}

