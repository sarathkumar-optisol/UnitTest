package com.example.unittest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.unittest.databinding.ActivityRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * navigate to Login Screen
         */
        binding.tvSignIn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }
        /**
         * hide keyboard on click outside views
         */
        binding.layoutRegistrationActivity.setOnClickListener {
            hideKeyboard(binding.layoutRegistrationActivity)
        }
    }
    private fun hideKeyboard(view : View){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}