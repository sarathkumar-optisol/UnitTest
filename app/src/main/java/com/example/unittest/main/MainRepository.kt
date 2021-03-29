package com.example.unittest.main

import com.example.unittest.modals.LogInData

/**
 * Created by SARATH on 29-03-2021
 */
interface MainRepository {

    suspend fun insertUserData(logInData: LogInData)
}