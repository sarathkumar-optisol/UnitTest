package com.example.unittest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    lateinit var homeFragament : HomeFragament

    lateinit var profileFragment: ProfileFragment

    lateinit var settingsFragment: SettingsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_home)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)


      replaceFragment(HomeFragament(),"Home")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFrg ->{
                Toast.makeText(this,"home",Toast.LENGTH_SHORT).show()
                replaceFragment(HomeFragament(),"HomeFragment")
            }
            R.id.profile ->{
                Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show()
                replaceFragment(ProfileFragment(),"HomeFragment")

            }
            R.id.settings ->{
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show()
                replaceFragment(SettingsFragment(),"HomeFragment")

            }
            else -> true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment, tag).commit()
    }
}