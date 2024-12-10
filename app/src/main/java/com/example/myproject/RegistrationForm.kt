package com.example.myproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegistrationForm : AppCompatActivity() {
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    lateinit var textView4: TextView
    lateinit var textView5: TextView
    lateinit var textView6: TextView
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var editText4: EditText
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_form)
        textView1=findViewById(R.id.txt1)
        textView2=findViewById(R.id.txt2)
        textView3=findViewById(R.id.txt3)
        textView4=findViewById(R.id.txt4)
        textView5=findViewById(R.id.txt5)
        textView6=findViewById(R.id.txt6)
        button1=findViewById(R.id.btn1)
        button2=findViewById(R.id.btn2)
        editText1=findViewById(R.id.edt1)
        editText2=findViewById(R.id.edt2)
        editText3=findViewById(R.id.edt3)
        editText4=findViewById(R.id.edt4)
        checkBox=findViewById(R.id.checkBox)

        button1.setOnClickListener{
            var name=editText1.text.toString()
            var password=editText2.text.toString()
            var phoneno=editText3.text.toString()
            var email=editText4.text.toString()
            if (checkBox.isChecked){
                if(name!="" && name.length>=5 && password!="" && password.length>=5 && phoneno!="" && phoneno.length==10 && email!=""){
                    saveUserDetails(name, password)
                    Toast.makeText(this,"your Details are saved successfully",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Please Enter Valid Details",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Please Accept the Terms and Conditions",Toast.LENGTH_SHORT).show()
            }
        }
        button2.setOnClickListener{
            if (button1.isClickable){
            var name=editText1.text.toString()
            var password=editText2.text.toString()
            var i=Intent(this,LoginPage::class.java)
            i.putExtra("uname",name)
            i.putExtra("password",password)
            startActivity(i)}
            else{
                Toast.makeText(this,"Save your Details",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveUserDetails(name: String, password: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("USER_NAME", name)
        editor.putString("USER_PASSWORD", password)
        editor.apply()
    }
}