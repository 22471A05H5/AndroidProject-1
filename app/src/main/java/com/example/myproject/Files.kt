package com.example.myproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class Files : AppCompatActivity() {
    lateinit var textView: TextView
    private  var texttodisplay="In programming, we may require some specific input data to be generated several numbers of times. Sometimes, it is not enough to only display the data on the console. The data to be displayed may be very large, and only a limited amount of data can be displayed on the console, and since the memory is volatile, it is impossible to recover the programmatically generated data again and again. However, if we need to do so, we may store it onto the local file system which is volatile and can be accessed every time. Here, comes the need of file handling in C."+"\n"+"File handling in C enables us to create, update, read, and delete the files stored on the local file system through our C program"+"\n"+"\n"+" The following operations can be performed on a file."+"\n"+"\n"+"\n"+" 1. Creation of the new file"+"\n"+" 2. Opening an existing file"+"\n"+" 3. Reading from the file\n" +
            " 4. Writing to the file\n" +
            " 5. Deleting the file"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)
        textView=findViewById(R.id.txt)
        animateText()
    }
    private fun animateText() {
        val handler = Handler(Looper.getMainLooper())
        var index = 0
        handler.post(object : Runnable {
            override fun run() {
                if (index < texttodisplay.length) {
                    textView.append(texttodisplay[index].toString())
                    index++
                    handler.postDelayed(this, 20) // Adjust the delay as needed
                }
            }
        })
    }
}