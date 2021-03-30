package com.example.unittest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unittest.modals.LogInData
import com.example.unittest.modals.UserProfile

/**
 * Created by SARATH on 29-03-2021
 */
@Database(
    entities = [LogInData ::class,UserProfile::class],

    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

}