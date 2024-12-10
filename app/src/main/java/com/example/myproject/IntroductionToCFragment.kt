package com.example.myproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class IntroductionToCFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_introduction_to_c, container, false)
        val btn:ImageButton=view.findViewById(R.id.dennie)
        val btn1:ImageButton=view.findViewById(R.id.hyphen)
        btn.setOnClickListener{
            val fragment=DennisFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,fragment)?.commit()
        }
        btn1.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        return view

    }


}