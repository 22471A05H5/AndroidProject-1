package com.example.myproject

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase


class QuizFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var finishButton: Button
    private lateinit var restartButton: Button
    private lateinit var btn:ImageButton
    private lateinit var btn1:Button
    private lateinit var btn2:Button
    private lateinit var btn3:Button
    private lateinit var btn4:Button
    private lateinit var btn5:Button
    private lateinit var btn6:Button
    private lateinit var btn7:Button
    private lateinit var btn8:Button
    private lateinit var btn9:Button
    private lateinit var btn10:Button

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
        "10.ow many characters can a string hold when declared as follows?char name[20]:  "
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
        "const","Exteernal","The current value of a","20"
    )
    private var userAnswers = MutableList<String?>(questions.size) { null }

    private lateinit var database: FirebaseDatabase
    private var userName: String? = null
    private var rollNo: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance()


        userName = arguments?.getString("name")
        rollNo = arguments?.getString("rollNo")

        if (userName == null || rollNo == null) {
            Toast.makeText(requireContext(), "Missing user details!", Toast.LENGTH_SHORT).show()
            return
        }


        questionTextView = view.findViewById(R.id.questionTextView)
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup)
        previousButton = view.findViewById(R.id.previousButton)
        nextButton = view.findViewById(R.id.nextButton)
        finishButton = view.findViewById(R.id.finishButton)
        restartButton = view.findViewById(R.id.restartButton)
        btn=view.findViewById(R.id.hyphen)
        btn1=view.findViewById(R.id.button1)
        btn2=view.findViewById(R.id.button2)
        btn3=view.findViewById(R.id.button3)
        btn4=view.findViewById(R.id.button4)
        btn5=view.findViewById(R.id.button5)
        btn6=view.findViewById(R.id.button6)
        btn7=view.findViewById(R.id.button7)
        btn8=view.findViewById(R.id.button8)
        btn9=view.findViewById(R.id.button9)
        btn10=view.findViewById(R.id.button10)

        btn.setOnClickListener{
            val fragment=CFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new,fragment)?.commit()
        }
        btn1.setOnClickListener{
            currentQuestionIndex=0
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn2.setOnClickListener{
            currentQuestionIndex=1
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn3.setOnClickListener{
            currentQuestionIndex=2
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn4.setOnClickListener{
            currentQuestionIndex=3
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn5.setOnClickListener{
            currentQuestionIndex=4
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn6.setOnClickListener{
            currentQuestionIndex=5
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn7.setOnClickListener{
            currentQuestionIndex=6
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn8.setOnClickListener{
            currentQuestionIndex=7
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn9.setOnClickListener{
            currentQuestionIndex=8
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }
        btn10.setOnClickListener{
            currentQuestionIndex=9
            val question=getQuestion(currentQuestionIndex)
            val options=getOptions(currentQuestionIndex)
            questionTextView.text=question
            optionsRadioGroup.removeAllViews()
            for(option in options){
                val radioButton=RadioButton(requireContext())
                radioButton.text=option
                radioButton.setOnClickListener{
                    userAnswers[currentQuestionIndex] = option
                }
                optionsRadioGroup.addView(radioButton)
            }
            previousButton.isEnabled = currentQuestionIndex > 0
            nextButton.isEnabled = currentQuestionIndex < questions.size - 1
        }


        userAnswers = MutableList(questions.size) { null }

        // Set button click listeners
        previousButton.setOnClickListener { showPreviousQuestion() }
        nextButton.setOnClickListener { showNextQuestion() }
        finishButton.setOnClickListener { finishQuiz() }
        restartButton.setOnClickListener { restartQuiz() }


        updateQuestion()
    }

    private fun updateQuestion() {
        val question = getQuestion(currentQuestionIndex)
        val options = getOptions(currentQuestionIndex)


        questionTextView.text = question

        optionsRadioGroup.removeAllViews()
        for (option in options) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = option
            radioButton.setOnClickListener {
                userAnswers[currentQuestionIndex] = option
            }
            optionsRadioGroup.addView(radioButton)
        }


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


        val quizResultsRef = database.reference.child("quizResults").child(rollNo!!)


        quizResultsRef.setValue(result).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Quiz Finished! ", Toast.LENGTH_LONG).show()
                val fragment = RetrieveFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_container_new, fragment)?.commit()
            } else {
                Toast.makeText(requireContext(), "Failed to save results.", Toast.LENGTH_LONG).show()
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
        Toast.makeText(requireContext(), "Quiz Restarted", Toast.LENGTH_SHORT).show()
    }

    private fun getQuestion(index: Int): String {
        return questions.getOrElse(index) { "Unknown question" }
    }

    private fun getOptions(index: Int): List<String> {
        return options.getOrElse(index) { emptyList() }
    }
}









