package com.example.unittest.main

import com.example.unittest.db.UserDatabase
import com.example.unittest.modals.LogInData
import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource
import javax.inject.Inject

/**
 * Created by SARATH on 29-03-2021
 */
class DefaultMainRepository @Inject constructor(
    private val db : UserDatabase
) : MainRepository {

    override suspend fun insertUserData(userName : String , password : String) : Resource<Long> {
        var userData = LogInData(userName,password)
        db.getUserDao().insert(userData)
        return Resource.Success(1)
    }

//    override fun getUserData(userName: String): Resource<LogInData> {
//        val data = db.getUserDao().getData(userName)
//        return Resource.Success(data)
//    }
//
//    override fun getUserList(): Resource<List<LogInData>> {
//        val userList = db.getUserDao().getUserList()
//        return Resource.Success(userList)
//    }

    override fun getUserProfileList(): Resource<List<UserProfile>> {
        val userProfile=db.getUserDao().getUserProfileList()
        return Resource.Success(userProfile)
    }

    override fun getUserLoginDetail(userName: String): Resource<UserProfile> {
        val userLoginDetail = db.getUserDao().getUserLoginDetail(userName)
        return Resource.Success(userLoginDetail)
    }


    override suspend fun insertUserProfile(userProfile: UserProfile): Resource<Long> {
        val userProfile = db.getUserDao().insertUserProfile(userProfile)
        return Resource.Success(1)
    }

}