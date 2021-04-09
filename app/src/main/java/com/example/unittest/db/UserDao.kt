package com.example.unittest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by SARATH on 29-03-2021
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM userprofiledata")
    fun getUserProfileList() : LiveData<List<UserProfile>>

    @Query("SELECT * FROM userprofiledata WHERE email = :key")
    fun getUserLoginDetail(key: String) : UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile) : Long

}