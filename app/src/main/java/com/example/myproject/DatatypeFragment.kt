package com.example.myproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


class DatatypeFragment : Fragment() {
    lateinit var btn1:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_datatype, container, false)
        val btn:ImageButton=view.findViewById(R.id.hyphen)
        btn.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        val btn1 =view.findViewById<Button>(R.id.navigateToEditorButton)
        btn1.setOnClickListener {
            // URL of the online C editor
            val url = "https://www.programiz.com/c-programming/online-compiler/"
            // Create an Intent to open the URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        return view
    }

}