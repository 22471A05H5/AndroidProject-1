package com.example.myproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class Introduction_to_c : AppCompatActivity() {
    lateinit var textView: TextView
    private val textToDisplay = "C is a general-purpose programming language created by Dennis Ritchie at the Bell Laboratories in 1972.\n" +
            "\n" +
            "It is a very popular language, despite being old. The main reason for its popularity is because it is a fundamental language in the field of computer science.\n" +
            "\n" +
            "            C is strongly associated with UNIX, as it was developed to write the UNIX operating system."
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python_matter)
        textView=findViewById(R.id.txt1)
        animateText()
    }
    private fun animateText() {
        val handler = Handler(Looper.getMainLooper())
        var index = 0
        handler.post(object : Runnable {
            override fun run() {
                if (index < textToDisplay.length) {
                    textView.append(textToDisplay[index].toString())
                    index++
                    handler.postDelayed(this, 100) // Adjust the delay as needed
                }
            }
        })
    }
}