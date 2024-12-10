package com.example.myproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class FormFragment : Fragment() {
    private lateinit var nameEditText: EditText
    private lateinit var rollNoEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var btn:ImageButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nameEditText = view.findViewById(R.id.nameEditText)
        rollNoEditText = view.findViewById(R.id.rollNoEditText)
        submitButton = view.findViewById(R.id.submitButton)
        btn=view.findViewById<ImageButton>(R.id.hyphen)
        btn.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }


        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val rollNo = rollNoEditText.text.toString()

            if (name.isBlank() || rollNo.isBlank()) {
                Toast.makeText(requireContext(), "Please enter both name and roll number", Toast.LENGTH_SHORT).show()
            } else {

                    val quizFragment = QuizFragment()
                    val bundle = Bundle().apply {
                        putString("name", nameEditText.text.toString())
                        putString("rollNo", rollNoEditText.text.toString())
                    }
                    quizFragment.arguments = bundle


                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_new, quizFragment) // replace instead of add
                        .commit()


            }
        }
    }

}
