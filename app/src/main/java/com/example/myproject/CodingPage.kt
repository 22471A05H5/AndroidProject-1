package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class CodingPage : AppCompatActivity() {

    lateinit var textView1: TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var imageButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coding_page)
        textView1 = findViewById(R.id.txt1)
        button1=findViewById(R.id.btn1)
        button2=findViewById(R.id.btn2)
        button3=findViewById(R.id.btn3)
        button4=findViewById(R.id.btn4)
        button5=findViewById(R.id.btn5)

        imageButton=findViewById(R.id.imgbtn)
        imageButton.setOnClickListener{
            var i=Intent(this,MainMenuBar::class.java)
            startActivity(i)
        }

        button1.setOnClickListener{
            var i=Intent(this,C::class.java)
            startActivity(i)
        }
    }
}