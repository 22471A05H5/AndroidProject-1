package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginPage : AppCompatActivity() {
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var button1: Button
    lateinit var button2: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        textView1=findViewById(R.id.txt1)
        textView2=findViewById(R.id.txt2)
        textView3=findViewById(R.id.txt3)
        button1=findViewById(R.id.btn1)
        button2=findViewById(R.id.btn2)
        editText1=findViewById(R.id.edt1)
        editText2=findViewById(R.id.edt2)



        button2.setOnClickListener{
            var i=Intent(this,RegistrationForm::class.java)
            startActivity(i)
        }
        button1.setOnClickListener{

            var name=editText1.text.toString()
            var password=editText2.text.toString()
            var i=intent


            if (name.isNotEmpty() && password.isNotEmpty()) {
                if (validateUserDetails(name, password)) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainMenuBar::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show()
            }
        }

        }
    private fun validateUserDetails(name: String, password: String): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("USER_NAME", null)
        val savedPassword = sharedPreferences.getString("USER_PASSWORD", null)

        return name == savedName && password == savedPassword
    }

    }
