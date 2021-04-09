package com.example.unittest.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.unittest.getOrAwaitValue
import com.example.unittest.modals.UserProfile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import org.junit.Rule


/**
 * Created by SARATH on 09-04-2021
 */

/**
 * @property RunWith used for
 * tests are with android components
 * they run on Android Emulator
 */
/**
 * @property SmallTest
 * comes under Unit Test
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    /**
     * @property Before used to setup the required things for the testcase,
     * here it creates a database for each testcase
     */
    /**
     *@see setup , it setups the database
     */
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getUserDao()
    }

    /**
     * @see After used after executing all testcases ,
     * here used to clear database
     */
    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun insertUserProfile() = runBlockingTest {
        val userProfile = UserProfile("sarath","sarath@gmail.com",23,"sarath123")
        dao.insertUserProfile(userProfile)

      // val allUserProfile = dao.getUserProfileList().getOrAwaitValue()

        assertThat(userProfile)
    }

}