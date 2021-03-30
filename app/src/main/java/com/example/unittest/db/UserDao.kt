package com.example.unittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unittest.modals.LogInData
import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource

/**
 * Created by SARATH on 29-03-2021
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(logInData: LogInData) : Long

    @Query("SELECT * FROM userlogindata WHERE userName = :key")
    fun getData(key : String) : LogInData

    @Query("SELECT * FROM userlogindata")
    fun getUserList() : List<LogInData>

    @Query("SELECT * FROM userprofiledata")
    fun getUserProfileList() : List<UserProfile>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserProfile(userProfile: UserProfile) : Long

}