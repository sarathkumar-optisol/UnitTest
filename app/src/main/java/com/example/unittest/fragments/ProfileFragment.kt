package com.example.unittest.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.unittest.R
import com.example.unittest.adapter.UserListAdapter
import com.example.unittest.adapter.UserProfileListAdapter
import com.example.unittest.databinding.FragmentHomeBinding
import com.example.unittest.databinding.FragmentProfileBinding
import com.example.unittest.main.MainViewModel

/**
 * Created by SARATH on 31-03-2021
 */
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding : FragmentProfileBinding

    private lateinit var viewModel: MainViewModel

    lateinit var userListAdapter: UserListAdapter

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        sharedPreferences = activity!!.getSharedPreferences("token" , Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentProfileBinding.bind(view)



    loadSharedPreference()


    }

    fun loadSharedPreference(){
        val token = sharedPreferences.getString("token","kk")
        Log.d("profileToken","$token")
        binding.tvUserEmail.text = token
    }


}