package com.example.myproject



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class CFragment : Fragment() {
   @SuppressLint("MissingInflatedId")
   override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
               val view= inflater.inflate(R.layout.fragment_c, container, false)
       val btn:ImageButton=view.findViewById(R.id.imgg1)
       val btn1:ImageButton=view.findViewById(R.id.hyphen)
       val btn2:ImageButton=view.findViewById(R.id.img2)
       val btn3:ImageButton=view.findViewById(R.id.img3)
       val btn4:ImageButton=view.findViewById(R.id.img7)
       val btn5:ImageButton=view.findViewById(R.id.img5)
       val btn6:ImageButton=view.findViewById(R.id.img6)
       val btn10:ImageButton=view.findViewById(R.id.img4)


       btn.setOnClickListener{
           val fragment=IntroductionToCFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn1.setOnClickListener{
           val fragment=HomeFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn2.setOnClickListener{
           val fragment=DatatypeFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn3.setOnClickListener{
           val fragment=StatementsFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn4.setOnClickListener{
           val fragment=FormFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn5.setOnClickListener{
           val intent=Intent(activity,Files::class.java)
           startActivity(intent)
       }
       btn6.setOnClickListener{
           val fragment=DatatypeFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
       btn10.setOnClickListener{
           val fragment=FunctionFragment()
           val transaction=fragmentManager?.beginTransaction()
           transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
       }
    return view
   }


}


