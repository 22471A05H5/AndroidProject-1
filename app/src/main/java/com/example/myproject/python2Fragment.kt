package com.example.myproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class python2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_python2, container, false)
        val imageButton:ImageButton=view.findViewById(R.id.hyphen)
        imageButton.setOnClickListener{
            val fragment=pythonFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        return view
        }
    }


