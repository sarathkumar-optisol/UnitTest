package com.example.unittest.main

import com.example.unittest.modals.LogInData
import com.example.unittest.utils.Resource

/**
 * Created by SARATH on 29-03-2021
 */
interface MainRepository {

    suspend fun insertUserData(userName : String , password : String) : Resource<Long>

    fun getUserData(userName: String) : Resource<LogInData>
}