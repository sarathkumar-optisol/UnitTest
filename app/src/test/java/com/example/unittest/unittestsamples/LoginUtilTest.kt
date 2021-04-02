package com.example.unittest.unittestsamples

import com.example.unittest.modals.UserProfile
import com.example.unittest.testing.LoginUtil
import com.example.unittest.testing.RegistrationUtil
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by SARATH on 02-04-2021
 */
class LoginUtilTest {

    var users = arrayListOf<UserProfile>(
            UserProfile("sarath", "sarath1@gmail.com", 23, "sarath123"),
            UserProfile("kumar", "abc54@gmail.com", 24, "sarath35"),
            UserProfile("Oliver", "Olive1r@gmail.com", 28, "Oliver"))
    @Test
    fun `valid email and valid password returns true`(){

        val result = LoginUtil.validateLoginInput(
                users[0].email,
                users[0].password
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `Invalid email or Invalid password returns false`(){

        val result = LoginUtil.validateLoginInput(
                users[0].email,
                users[1].password
        )
        assertThat(result).isFalse()
    }
}