package com.example.unittest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unittest.R
import com.example.unittest.adapter.UserListAdapter
import com.example.unittest.databinding.ActivityHomeBinding
import com.example.unittest.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

     private lateinit var binding : ActivityHomeBinding

     private val viewModel : MainViewModel by viewModels()

    lateinit var userListAdapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserList()

        setupRecyclerView()



        lifecycleScope.launchWhenStarted {
            viewModel.userListData.collect{ event->
                when(event){
                    is MainViewModel.UserListEvent.Success ->{
                        //binding.progressBarMainActivity.isVisible = false
                        val data = event.result

                        userListAdapter.userListAdapter = event.result.toMutableList()
                        for (i in data){
                            Log.d("DATA","$i")

                        }

                    }
                    is MainViewModel.UserListEvent.Failure ->{
                        Toast.makeText(this@HomeActivity,R.string.invalid_credential, Toast.LENGTH_LONG).show()
                        /**
                         * code for failed to retrieve data
                         */
                    }
                    is MainViewModel.UserListEvent.Loading -> {
                       // binding.progressBarMainActivity.isVisible = true
                    }
                    else -> Unit
                }

            }
        }
    }

    private fun setupRecyclerView() = binding.rvAllUserList.apply{

        userListAdapter = UserListAdapter(context)
        adapter = userListAdapter
        userListAdapter.notifyDataSetChanged()
        layoutManager = LinearLayoutManager(context)

    }
}