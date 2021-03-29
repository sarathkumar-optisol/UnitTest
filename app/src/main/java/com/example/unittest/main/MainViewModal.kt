package com.example.unittest.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.unittest.db.UserDatabase

/**
 * Created by SARATH on 29-03-2021
 */
class MainViewModal @ViewModelInject constructor(
        private val repository: MainRepository,
        private val db : UserDatabase
)  : ViewModel() {


}