package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast


class FeedbackFragment : Fragment() {
    lateinit var img1:ImageButton
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var button: Button



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_feedback, container, false)
        edt1=view.findViewById(R.id.edt1)
        edt2=view.findViewById(R.id.edt2)
        edt3=view.findViewById(R.id.edt3)
        button=view.findViewById(R.id.buttonSubmit)
        button.setOnClickListener{
            val name=edt1.text.toString().trim()
            val emaill=edt2.text.toString().trim()
            val mess=edt3.text.toString().trim()
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(emaill) || TextUtils.isEmpty(mess)){
                return@setOnClickListener
            }
            sendEmail(name,emaill,mess)
        }


        img1=view.findViewById(R.id.img1)
        img1.setOnClickListener{
            openInstagramProfile("programming_india")
        }

        return view
    }

    private fun openInstagramProfile(username: String) {
        val uri = Uri.parse("https://www.instagram.com/$username")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val twitterButton = view.findViewById<ImageButton>(R.id.img2)
        twitterButton.setOnClickListener {
            openTwitter()
        }
        val linkedInButton = view.findViewById<ImageButton>(R.id.img3)
        linkedInButton.setOnClickListener {
            openLinkedIn()
        }
    }

    private fun openTwitter() {
        val twitterUrl = "https://twitter.com"  // Replace with your Twitter URL
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(twitterUrl)
        }

        startActivity(intent)
    }



    private fun openLinkedIn() {
        val linkedInUrl = "https://www.linkedin.com/in/mounica-pulagorla-3a8272276/"  // Replace with your LinkedIn profile URL
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(linkedInUrl)
        }

        // Check if there's an app that can handle the URL
        startActivity(intent)
    }
    private fun sendEmail(name: String,emaill:String,mess:String){
        val intent=Intent(Intent.ACTION_SEND).apply {
            type="message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("pulagorlalakshmi90@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT,"contact form message from $name")
            putExtra(Intent.EXTRA_TEXT,"Name:$name\nEmail:$emaill\n\nMessage:\n$mess")
        }
        try{
            startActivity(Intent.createChooser(intent,"Send email using:"))

        }
        catch (ex:android.content.ActivityNotFoundException){
            ex.printStackTrace()
        }
    }


}