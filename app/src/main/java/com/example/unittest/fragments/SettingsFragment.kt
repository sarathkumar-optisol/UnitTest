package com.example.unittest.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.unittest.R
import com.example.unittest.activity.MainActivity
import com.example.unittest.databinding.FragmentSettingsBinding

/**
 * Created by SARATH on 31-03-2021
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding : FragmentSettingsBinding
    private lateinit var sharedPreference : SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)


        binding.btnLogOut.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }


    }

}