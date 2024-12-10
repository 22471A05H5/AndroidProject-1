package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class C : AppCompatActivity() {
    lateinit var scrollView: ScrollView
    lateinit var textView: TextView
    lateinit var imageButton1: ImageButton
    lateinit var imageButton2: ImageButton
    lateinit var imageButton3: ImageButton
    lateinit var imageButton4: ImageButton
    lateinit var imageButton5: ImageButton
    lateinit var imageButton6: ImageButton
    lateinit var imageButton7:ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        imageButton1 = findViewById(R.id.imgbtn2)
        imageButton2 = findViewById(R.id.img2)
        imageButton3 = findViewById(R.id.img3)
        imageButton4 = findViewById(R.id.img4)
        imageButton5 = findViewById(R.id.img5)
        imageButton6 = findViewById(R.id.img6)
        imageButton7 = findViewById(R.id.imgbtn)

        imageButton1.setOnClickListener {
            var i = Intent(this, Introduction_to_c::class.java)
            startActivity(i)
        }
        imageButton2.setOnClickListener {
            var i = Intent(this, python_matter::class.java)
            startActivity(i)
        }
        imageButton3.setOnClickListener {
            var i = Intent(this, Statements::class.java)
            startActivity(i)
        }
        imageButton4.setOnClickListener {
            var i = Intent(this, Function::class.java)
            startActivity(i)
        }
        imageButton5.setOnClickListener {
            var i = Intent(this, Files::class.java)
            startActivity(i)
        }
        imageButton6.setOnClickListener {
            var i = Intent(this, quiz::class.java)
            startActivity(i)
        }
        imageButton7.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, HomeFragment())
                .addToBackStack(null)
                .commit()

        }
    }

}