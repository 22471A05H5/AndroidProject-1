package com.example.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Statements : AppCompatActivity() {
    lateinit var firstButton: Button
    lateinit var secondButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        val firstButton = findViewById<Button>(R.id.firstButton)
        val secondButton = findViewById<Button>(R.id.secondButton)
        val thirdButton=findViewById<Button>(R.id.thirdButton)
        val fourthButton=findViewById<Button>(R.id.fourthButton)
        firstButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Fragment1())
                .addToBackStack(null)
                .commit()
        }

        secondButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Fragment2())
                .addToBackStack(null)
                .commit()
        }
        thirdButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Fragment3())
                .addToBackStack(null)
                .commit()
        }
        fourthButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Fragment4())
                .addToBackStack(null)
                .commit()
        }
    }
}
