package com.example.myproject


import android.widget.*

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.*

import com.google.firebase.BuildConfig

class SettingssFragment : Fragment() {

    private lateinit var themeSwitch: Switch
    private lateinit var languageSpinner: Spinner
    private lateinit var versionTextView: TextView
    private lateinit var aboutButton: Button
    private lateinit var privacyButton: Button
    private lateinit var helpButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settingss, container, false)

        themeSwitch = view.findViewById(R.id.themeSwitch)
        languageSpinner = view.findViewById(R.id.languageSpinner)
        versionTextView = view.findViewById(R.id.versionTextView)
        aboutButton = view.findViewById(R.id.aboutButton)
        privacyButton = view.findViewById(R.id.privacyButton)
        helpButton = view.findViewById(R.id.helpButton)

        sharedPreferences = requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        setupThemeSwitch()
        setupLanguageSpinner()
        setupVersionInfo()
        setupButtons()

        return view
    }

    private fun setupThemeSwitch() {
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)
        themeSwitch.isChecked = isDarkMode
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveThemePreference(isChecked)
            applyTheme(isChecked)
        }
    }

    private fun setupLanguageSpinner() {
        val languages = arrayOf("English", "Spanish", "French")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        languageSpinner.setSelection(
            languages.indexOf(sharedPreferences.getString("language", "English"))
        )
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                saveLanguagePreference(languages[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupVersionInfo() {
        val versionName = BuildConfig.VERSION_NAME
        versionTextView.text = "Version: $versionName"
    }

    private fun setupButtons() {
        aboutButton.setOnClickListener {
            showAboutDialog()
        }

        privacyButton.setOnClickListener {
            showPrivacySettings()
        }

        helpButton.setOnClickListener {
            showHelpAndFeedback()
        }
    }

    private fun saveThemePreference(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("darkMode", isDarkMode)
        editor.apply()
    }

    private fun applyTheme(isDarkMode: Boolean) {
        // Logic to apply dark or light theme
    }

    private fun saveLanguagePreference(language: String) {
        val editor = sharedPreferences.edit()
        editor.putString("language", language)
        editor.apply()
        // Refresh the activity to apply language changes
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("About")
            .setMessage("This is a Coding High School app. Version: ${BuildConfig.VERSION_NAME}")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showPrivacySettings() {
        AlertDialog.Builder(requireContext())
            .setTitle("Privacy Settings")
            .setMessage("privacy settings information.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showHelpAndFeedback() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:help@example.com")
            putExtra(Intent.EXTRA_SUBJECT, "Feedback/Help Request")
        }
        startActivity(intent)
    }
}
