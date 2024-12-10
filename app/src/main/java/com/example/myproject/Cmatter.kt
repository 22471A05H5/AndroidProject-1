package com.example.myproject



    import android.annotation.SuppressLint
    import android.os.Bundle
    import android.os.Handler
    import android.os.Looper
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity

    class Cmatter : AppCompatActivity() {

        private lateinit var textView1: TextView
        private val textToDisplay ="C is a general-purpose programming language created by Dennis Ritchie at the Bell Laboratories in 1972.\n" +
        "\n" +
        "It is a very popular language, despite being old. The main reason for its popularity is because it is a fundamental language in the field of computer science.\n" +
        "\n" +
        "            C is strongly associated with UNIX, as it was developed to write the UNIX operating system."

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            textView1 = findViewById(R.id.txt2)

            animateText()
        }

        private fun animateText() {
            val handler = Handler(Looper.getMainLooper())
            var index = 0
            handler.post(object : Runnable {
                override fun run() {
                    if (index < textToDisplay.length) {
                        textView1.append(textToDisplay[index].toString())
                        index++
                        handler.postDelayed(this, 100) // Adjust the delay as needed
                    }
                }
            })
        }
    }