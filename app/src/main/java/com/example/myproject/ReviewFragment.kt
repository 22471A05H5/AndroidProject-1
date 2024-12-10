package com.example.myproject

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment

class ReviewFragment : Fragment() {

    private lateinit var ratingBarExperience: RatingBar
    private lateinit var ratingBarDifficulty: RatingBar
    private lateinit var feedbackText: EditText
    private lateinit var recommendSwitch: Switch
    private lateinit var feedbackOptionsGroup: RadioGroup
    private lateinit var submitButton: Button

    private val CHANNEL_ID = "feedback_channel"
    private val NOTIFICATION_ID = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ratingBarExperience = view.findViewById(R.id.ratingBarExperience)
        ratingBarDifficulty = view.findViewById(R.id.ratingBarDifficulty)
        feedbackText = view.findViewById(R.id.feedbackText)
        recommendSwitch = view.findViewById(R.id.recommendSwitch)
        feedbackOptionsGroup = view.findViewById(R.id.feedbackOptionsGroup)
        submitButton = view.findViewById(R.id.submitButton)


        createNotificationChannel()

        submitButton.setOnClickListener {
            val experienceRating = ratingBarExperience.rating
            val difficultyRating = ratingBarDifficulty.rating
            val feedback = feedbackText.text.toString()
            val recommend = recommendSwitch.isChecked
            val selectedOptionId = feedbackOptionsGroup.checkedRadioButtonId
            val selectedOption = view.findViewById<RadioButton>(selectedOptionId)?.text


            showNotification()



        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Feedback Submission"
            val descriptionText = "Channel for feedback submission notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    @SuppressLint("MissingPermission")
    private fun showNotification() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_add_alert_24) // Use your app's notification icon
            .setContentTitle("Feedback Submitted")
            .setContentText("Thank you for your feedback!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {

            notify(NOTIFICATION_ID, builder.build())
        }
    }}