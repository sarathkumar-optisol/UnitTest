package com.example.unittest.main

import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource

/**
 * Created by SARATH on 29-03-2021
 */
interface MainRepository {

    fun getUserProfileList() : Resource<List<UserProfile>>

    fun getUserLoginDetail(userName: String) : Resource<UserProfile>

    suspend fun insertUserProfile(userProfile: UserProfile) : Resource<Long>

}