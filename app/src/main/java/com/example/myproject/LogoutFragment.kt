package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class LogoutFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_logout, container, false)
        val btn1:Button=view.findViewById(R.id.cancelbtn)
        val btn2:Button=view.findViewById(R.id.confirmbtn)
        btn1.setOnClickListener{
            val fragment=HomeFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        btn2.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()


            val intent = Intent(requireContext(), RegistrationForm::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }


}