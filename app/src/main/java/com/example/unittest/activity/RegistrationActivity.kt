package com.example.unittest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.unittest.R
import com.example.unittest.databinding.ActivityRegistrationBinding
import com.example.unittest.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationBinding

    private val viewModel : MainViewModel by viewModels()

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
        /**
         * UserRegistration
         */
        binding.btnSignUp.setOnClickListener {

            val userName = binding.etNewUserNAme.text.toString()
            val password = binding.etNewUserPassword.text.toString()
            hideKeyboard(binding.btnSignUp)
            if(binding.etNewUserNAme.text?.isEmpty()!! || binding.etNewUserPassword.text?.isEmpty()!! || ! Patterns.EMAIL_ADDRESS.matcher(binding.etNewUserNAme.text).matches() || binding.etNewUserPassword.text.length < 6){
                Toast.makeText(this,"Enter Valid email or password", Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
            }else{
                viewModel.registerUser(userName, password
                )
            }
        }

        lifecycleScope.launchWhenStarted {
                viewModel.registerData.collect{ event->
                    when(event){
                        is MainViewModel.RegisterEvent.Success ->{
                            binding.progressBar.isVisible = false
                            val intent = Intent(this@RegistrationActivity,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        is MainViewModel.RegisterEvent.Failure ->{
                            /**
                             * code for unsuccessful register
                             */
                        }
                        is MainViewModel.RegisterEvent.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        else -> Unit
                    }

                }
        }
    }
    private fun hideKeyboard(view : View){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}