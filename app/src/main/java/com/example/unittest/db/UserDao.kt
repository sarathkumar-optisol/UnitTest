package com.example.unittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unittest.modals.LogInData

/**
 * Created by SARATH on 29-03-2021
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(logInData: LogInData) : Long

    @Query("SELECT * FROM userlogindata WHERE userName = :key")
    fun getData(key : String) : LogInData

}