package com.example.myproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.FirebaseDatabase

class QuizzActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var finishButton: Button
    private lateinit var restartButton: Button
    private lateinit var btn: ImageButton
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn10: Button

    private var currentQuestionIndex = 0
    private val questions = listOf(
        "1.Who is the founder of C?",
        "2.All keywords in C are in ?",
        "3.How many keywords are in C Language?",
        "4.What is an example of iteration in C?",
        "5.Which of the following is not a valid C variable name?",
        "6.Which of the following cannot be a variable name in C?",
        "7.Which keyword is used to prevent any changes in the variable within a C program?",
        "8.Functions in C Language are always?",
        "9.What is the output of this statement \"printf(\"%d\", (a++))\"?",
        "10.How many characters can a string hold when declared as follows?char name[20]:  "
    )
    private val options = listOf(
        listOf("Dennis Ritchie", "James Gosling", "Guido Van Rossum", "Sun microsystems"),
        listOf("UpperCase", "LowerCase", "CamelCase", "None of the mentioned"),
        listOf("32", "30", "24", "39"),
        listOf("for", "while", "do-while", "all of the mentioned"),
        listOf("int number;","float rate;","int variable_count;","int main;"),
        listOf("volatile","true","friend","export"),
        listOf("immutable","mutable","const","volatile"),
        listOf("Internal","External","both","None"),
        listOf("The value of (a + 1)","The current value of a","Error message","Garbage"),
        listOf("18","19","20","None of the these")
    )
    private val correctAnswers = listOf(
        "Dennis Ritchie",
        "UpperCase",
        "32",
        "all of the mentioned",
        "int main",
        "friend",
        "const",
        "External",
        "The current value of a",
        "20"
    )
    private var userAnswers = MutableList<String?>(questions.size) { null }

    private lateinit var database: FirebaseDatabase
    private var userName: String? = null
    private var rollNo: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        database = FirebaseDatabase.getInstance()

        // Retrieve name and roll number from the previous activity
        userName = intent.getStringExtra("name")
        rollNo = intent.getStringExtra("rollNo")

        if (userName == null || rollNo == null) {
            Toast.makeText(this, "Missing user details!", Toast.LENGTH_SHORT).show()
            return
        }

        // Initialize the TextView, RadioGroup, and Buttons
        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)
        finishButton = findViewById(R.id.finishButton)
        restartButton = findViewById(R.id.restartButton)

        btn = findViewById(R.id.hyphen)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)
        btn10 = findViewById(R.id.button10)

        btn1.setOnClickListener { showQuestion(0) }
        btn2.setOnClickListener { showQuestion(1) }
        btn3.setOnClickListener { showQuestion(2) }
        btn4.setOnClickListener { showQuestion(3) }
        btn5.setOnClickListener { showQuestion(4) }
        btn6.setOnClickListener { showQuestion(5) }
        btn7.setOnClickListener { showQuestion(6) }
        btn8.setOnClickListener { showQuestion(7) }
        btn9.setOnClickListener { showQuestion(8) }
        btn10.setOnClickListener { showQuestion(9) }

        // Set button click listeners
        previousButton.setOnClickListener { showPreviousQuestion() }
        nextButton.setOnClickListener { showNextQuestion() }
        finishButton.setOnClickListener { finishQuiz() }
        restartButton.setOnClickListener { restartQuiz() }

        // Initialize the first question
        updateQuestion()
    }

    private fun showQuestion(index: Int) {
        currentQuestionIndex = index
        updateQuestion()
    }

    private fun updateQuestion() {
        val question = getQuestion(currentQuestionIndex)
        val options = getOptions(currentQuestionIndex)

        // Update the TextView with the selected question
        questionTextView.text = question

        // Clear the previous options and add new ones
        optionsRadioGroup.removeAllViews()
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.setOnClickListener {
                userAnswers[currentQuestionIndex] = option
            }
            optionsRadioGroup.addView(radioButton)
        }

        // Update button states
        previousButton.isEnabled = currentQuestionIndex > 0
        nextButton.isEnabled = currentQuestionIndex < questions.size - 1
    }

    private fun showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            updateQuestion()
        }
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            updateQuestion()
        }
    }

    private fun finishQuiz() {
        val score = calculateScore()
        val result = QuizResult(userName!!, rollNo!!, score)

        // Store data under the user's roll number
        val quizResultsRef = database.reference.child("quizResults").child(rollNo!!)

        // Push data to Firebase
        quizResultsRef.setValue(result).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Quiz Finished! Your score: $score/${questions.size}", Toast.LENGTH_LONG).show()
                // Do something like navigating to the result activity
            } else {
                Toast.makeText(this, "Failed to save results.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun calculateScore(): Int {
        var score = 0
        for (i in questions.indices) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++
            }
        }
        return score
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        userAnswers = MutableList(questions.size) { null } // Reset answers
        updateQuestion()
        Toast.makeText(this, "Quiz Restarted", Toast.LENGTH_SHORT).show()
    }

    private fun getQuestion(index: Int): String {
        return questions.getOrElse(index) { "Unknown question" }
    }

    private fun getOptions(index: Int): List<String> {
        return options.getOrElse(index) { emptyList() }
    }
}
