package com.example.unittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.unittest.modals.LogInData

/**
 * Created by SARATH on 29-03-2021
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(logInData: LogInData)
}