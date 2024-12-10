package com.example.myproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView


class IntroductionToPythonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_introduction_to_python, container, false)
        val imageButton: ImageButton =view.findViewById(R.id.hyphen)
        imageButton.setOnClickListener{
            val fragment=pythonFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val title1:TextView=view.findViewById(R.id.title1)
        val title3:TextView=view.findViewById(R.id.title3)
        val title2:TextView=view.findViewById(R.id.title2)
        val content1:TextView=view.findViewById(R.id.content1)
        val content2:TextView=view.findViewById(R.id.content2)
        val content3:TextView=view.findViewById(R.id.content3)
        title1.setOnClickListener {

            toggleVisibility(content1)
        }


        title2.setOnClickListener {
            toggleVisibility(content2)
        }
        title3.setOnClickListener {
            toggleVisibility(content3)
        }


    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}