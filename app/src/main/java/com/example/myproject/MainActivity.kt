package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val delayMillis:Long=2000


    lateinit var imageView: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView=findViewById(R.id.image1)
        Handler().postDelayed({
            startActivity(Intent(this,LoginPage::class.java))
        },delayMillis)
        Toast.makeText(this,"Welcome to Coding High School",Toast.LENGTH_LONG).show()
    }
}