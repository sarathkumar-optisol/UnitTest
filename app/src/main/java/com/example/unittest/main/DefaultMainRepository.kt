package com.example.unittest.main

import com.example.unittest.db.UserDatabase
import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource
import javax.inject.Inject

/**
 * Created by SARATH on 29-03-2021
 */
class DefaultMainRepository @Inject constructor(
    private val db : UserDatabase
) : MainRepository {


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