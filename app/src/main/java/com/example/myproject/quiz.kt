package com.example.myproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class quiz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val emp=findViewById<FrameLayout>(R.id.empty)
        val question1=findViewById<TextView>(R.id.q1)
        val question2=findViewById<TextView>(R.id.q2)
        findViewById<View>(R.id.container)

    }
}