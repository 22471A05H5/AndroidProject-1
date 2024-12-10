package com.example.myproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


class StatementsFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_statements, container, false)
        val btn1:Button=view.findViewById(R.id.firstButton)
        val btn2:Button=view.findViewById(R.id.secondButton)
        val btn3:Button=view.findViewById(R.id.thirdButton)
        val btn4:Button=view.findViewById(R.id.fourthButton)
        val btn5:ImageButton=view.findViewById(R.id.hyphen)
        btn1.setOnClickListener{
            val fragment=Fragment1()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,fragment)?.commit()
        }
        btn2.setOnClickListener{
            val fragment=Fragment2()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,fragment)?.commit()
        }
        btn3.setOnClickListener{
            val fragment=Fragment3()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,fragment)?.commit()
        }
        btn4.setOnClickListener{
            val fragment=Fragment4()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,fragment)?.commit()
        }
        btn5.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        return view
    }


}