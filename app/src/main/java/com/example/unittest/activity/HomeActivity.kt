package com.example.unittest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unittest.R
import com.example.unittest.adapter.UserListAdapter
import com.example.unittest.adapter.UserProfileListAdapter
import com.example.unittest.adapter.ViewPagerAdapter
import com.example.unittest.databinding.ActivityHomeBinding
import com.example.unittest.fragments.HomeFragament
import com.example.unittest.fragments.ProfileFragment
import com.example.unittest.fragments.SettingsFragment
import com.example.unittest.main.MainViewModel
import com.example.unittest.modals.UserProfile
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


     private lateinit var binding : ActivityHomeBinding

     private val viewModel : MainViewModel by viewModels()

    lateinit var userListAdapter: UserProfileListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        viewModel.getUserList()
//        viewModel.getUserProfileList()

//        setupRecyclerView()

        val fragments : ArrayList<Fragment> = arrayListOf(

                HomeFragament(),
                ProfileFragment(),
                SettingsFragment()
        )

        val adapter = ViewPagerAdapter(fragments , this)
        binding.vpHome.adapter = adapter


        TabLayoutMediator(binding.tabLayout , binding.vpHome) { tab , position ->
            if (position == 0){
                tab.text = "UserList"
            }else if (position == 1){
                tab.text = "UserProfile"

            }else{
                tab.text = "Settings"
            }
        }.attach()



//        lifecycleScope.launchWhenStarted {
//            viewModel.userListData.collect{ event->
//                when(event){
//                    is MainViewModel.ProfileListEvent.Success ->{
//                        //binding.progressBarMainActivity.isVisible = false
//                        val data = event.result
//
//                        userListAdapter.userListAdapter = event.result.toMutableList()
//                        for (i in data){
//                            Log.d("DATA","$i")
//
//                        }
//
//                    }
//                    is MainViewModel.ProfileListEvent.Failure ->{
//                        Toast.makeText(this@HomeActivity,R.string.invalid_credential, Toast.LENGTH_LONG).show()
//                        /**
//                         * code for failed to retrieve data
//                         */
//                    }
//                    is MainViewModel.ProfileListEvent.Loading -> {
//                       // binding.progressBarMainActivity.isVisible = true
//                    }
//                    else -> Unit
//                }
//
//            }
//        }

    }


    override fun onBackPressed() {
        if (binding.vpHome.currentItem == 0){
            super.onBackPressed()
        }else{
            binding.vpHome.currentItem = binding.vpHome.currentItem - 1
        }
    }

//    private fun setupRecyclerView() = binding.rvAllUserList.apply{
//
//        userListAdapter = UserProfileListAdapter(context)
//        adapter = userListAdapter
//        userListAdapter.notifyDataSetChanged()
//        layoutManager = LinearLayoutManager(context)
//
//    }
}