package com.example.unittest.main

import com.example.unittest.db.UserDatabase
import com.example.unittest.modals.LogInData
import javax.inject.Inject

/**
 * Created by SARATH on 29-03-2021
 */
class DefaultMainRepository @Inject constructor(
    private val db : UserDatabase
) : MainRepository {

    override suspend fun insertUserData(logInData: LogInData) {
        db.getUserDao().insert(logInData)
    }

}