package com.example.unittest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.unittest.R
import com.example.unittest.databinding.ActivityMainBinding
import com.example.unittest.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * navigate to Registration Screen
         */
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        /**
         * hide keyboard on click outside views
         */
        binding.layoutMainActivity.setOnClickListener {
            hideKeyboard(binding.layoutMainActivity)
        }
        /**
         * check user data and Login
         */
        binding.btnSignIn.setOnClickListener {
            val userName : String = binding.etUserName.text.toString()
            viewModel.getData(userName)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.registerData.collect{ event->
                when(event){
                    is MainViewModel.RegisterEvent.Success ->{
                        binding.progressBarMainActivity.isVisible = false
                        val password = event.result
                        if (password == binding.etPassword.text.toString()){
                            val intent = Intent(this@MainActivity,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@MainActivity,R.string.invalid_credential,Toast.LENGTH_LONG).show()
                        }
                    }
                    is MainViewModel.RegisterEvent.Failure ->{
                        Toast.makeText(this@MainActivity,R.string.invalid_credential,Toast.LENGTH_LONG).show()
                        /**
                         * code for unsuccessful login
                         */
                    }
                    is MainViewModel.RegisterEvent.Loading -> {
                        binding.progressBarMainActivity.isVisible = true
                    }
                    else -> Unit
                }

            }
        }
    }
    /**
     * hide keyboard on user touches outside
     */
    private fun hideKeyboard(view : View){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}