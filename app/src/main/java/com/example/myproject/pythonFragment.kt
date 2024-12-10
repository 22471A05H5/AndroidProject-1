package com.example.myproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


class pythonFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_python, container, false)
        val img1:ImageButton=view.findViewById(R.id.img1)
        val img2:ImageButton=view.findViewById(R.id.img2)
        val img3:ImageButton=view.findViewById(R.id.img3)
        val img4:ImageButton=view.findViewById(R.id.img4)
        val img5:ImageButton=view.findViewById(R.id.img5)
        val img6:ImageButton=view.findViewById(R.id.img6)
        val img7:ImageButton=view.findViewById(R.id.img7)
        val btn:ImageButton=view.findViewById(R.id.hyphen)

        btn.setOnClickListener{
            val fragment=HomeFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }

        img1.setOnClickListener{
            val fragment=IntroductionToPythonFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        img2.setOnClickListener{
            val fragment=python2Fragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }

        return view
    }


}