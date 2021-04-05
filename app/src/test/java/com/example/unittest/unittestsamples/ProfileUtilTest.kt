package com.example.unittest.unittestsamples

import com.example.unittest.modals.UserProfile
import com.example.unittest.testing.LoginUtil
import com.example.unittest.testing.ProfileUtil
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Created by SARATH on 05-04-2021
 */
class ProfileUtilTest {


    var users = arrayListOf<UserProfile>(
            UserProfile("sarath", "sarath1@gmail.com", 23, "sarath123"),
    UserProfile("", "sarath1@gmail.com", 23, "sarath123"))

    @Test
    fun `valid userProfile details return true`(){

        val result =  ProfileUtil.validateUserProfile(users[0].email,users[0].name,users[0].age)

        Truth.assertThat(result).isTrue()
    }


    @Test
    fun `Invalid userProfile details return false`(){


        val result =  ProfileUtil.validateUserProfile(users[1].email,users[1].name,users[1].age)

        Truth.assertThat(result).isFalse()
    }

}