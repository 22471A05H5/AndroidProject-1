package com.example.myproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class statements_in_c : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFirst(view: View) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, Fragment1())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun onClickSecond(view: View) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout,Fragment2())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}