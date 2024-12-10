package com.example.myproject

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.firebase.BuildConfig
import kotlinx.coroutines.launch
import java.io.InputStream


class SettingsFragment : Fragment() {

    private val REQUEST_CODE_PERMISSIONS = 1001
    private val REQUEST_CODE_IMAGE_PICK = 1002

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText
    private lateinit var profileImageView: ImageView
    private lateinit var saveButton: Button
    private lateinit var deleteAccountButton: Button
    private lateinit var versionTextView: TextView
    private lateinit var notificationSwitch: Switch
    private lateinit var editUsernameIcon: ImageView
    private lateinit var editEmailIcon: ImageView
    private lateinit var editPasswordIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        usernameEditText = view.findViewById(R.id.usernameEditText)

        passwordEditText = view.findViewById(R.id.passwordEditText)
        profileImageView = view.findViewById(R.id.profileImageView)
        saveButton = view.findViewById(R.id.saveButton)
        deleteAccountButton = view.findViewById(R.id.deleteAccountButton)


        editUsernameIcon = view.findViewById(R.id.editUsernameIcon)

        editPasswordIcon = view.findViewById(R.id.editPasswordIcon)


        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSIONS
            )
        }


        loadUserData()


        profileImageView.setOnClickListener {
            pickImageFromGallery()
        }


        saveButton.setOnClickListener {
            updateUserData()
        }


        deleteAccountButton.setOnClickListener {
            confirmDeleteAccount()
        }

        editUsernameIcon.setOnClickListener {
            usernameEditText.isEnabled = true
        }



        editPasswordIcon.setOnClickListener {
            passwordEditText.isEnabled = true
        }

        return view
    }

    private fun loadUserData() {
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)


        val savedName = sharedPreferences.getString("USER_NAME", null)
        val savedPassword = sharedPreferences.getString("USER_PASSWORD", null)


        usernameEditText.setText(savedName ?: "")
        passwordEditText.setText(savedPassword ?: "")

        val profileImageUri = sharedPreferences.getString("profileImageUri", null)
        profileImageUri?.let {
            val uri = Uri.parse(it)
            profileImageView.setImageURI(uri)
        }
    }

    private fun updateUserData() {
        val updatedName = usernameEditText.text.toString()
        val updatedPassword = passwordEditText.text.toString()


        if (updatedName.isBlank() || updatedPassword.isBlank()) {
            showErrorDialog("Username or Password cannot be empty!")
            return
        }


        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("USER_NAME", updatedName)
        editor.putString("USER_PASSWORD", updatedPassword)
        editor.apply() // Apply changes


        Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                uploadProfilePicture(uri)
            }
        }
    }

    private fun uploadProfilePicture(uri: Uri) {
        lifecycleScope.launch {
            try {
                val inputStream: InputStream =
                    requireContext().contentResolver.openInputStream(uri)!!
                val bitmap = BitmapFactory.decodeStream(inputStream)
                profileImageView.setImageBitmap(bitmap)

                // Save profile picture URI to SharedPreferences
                val sharedPreferences =
                    requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("profileImageUri", uri.toString())
                editor.apply()

                Toast.makeText(
                    requireContext(),
                    "Profile picture updated successfully",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Failed to upload profile picture",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleNotificationSwitchChange(isChecked: Boolean) {

        val sharedPreferences =
            requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("notificationsEnabled", isChecked)
        editor.apply()
    }

    private fun confirmDeleteAccount() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Account")
            .setMessage("Are you sure you want to delete your account? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                deleteAccount()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteAccount() {

        val sharedPreferences =
            requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        usernameEditText.text.clear()

        passwordEditText.text.clear()


        profileImageView.setImageResource(R.drawable.baseline_account_circle_24)

        Toast.makeText(requireContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show()

    }

    private fun saveUserDataToSharedPreferences(username: String, email: String, password: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSuccessDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Success")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission denied to read external storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}