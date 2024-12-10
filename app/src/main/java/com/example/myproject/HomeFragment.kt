package com.example.myproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view= inflater.inflate(R.layout.fragment_home, container, false)
        val btn:Button=view.findViewById(R.id.btn1)
        val btn2:Button=view.findViewById(R.id.btn2)
        btn.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        btn2.setOnClickListener{
            val fragment=pythonFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        return view

    }
}
