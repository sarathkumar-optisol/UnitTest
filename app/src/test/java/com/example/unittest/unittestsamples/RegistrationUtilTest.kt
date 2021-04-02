package com.example.unittest.unittestsamples

import com.example.unittest.modals.UserProfile
import com.example.unittest.testing.LoginUtil
import com.example.unittest.testing.RegistrationUtil
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Created by SARATH on 02-04-2021
 */
class RegistrationUtilTest {




    @Test
    fun `empty email and password returns false`(){

        val result = RegistrationUtil.validateLoginInput(
            "",
            ""
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `empty email returns false`(){

        val result = RegistrationUtil.validateLoginInput(
            "",
            "12345678"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){

        val result = RegistrationUtil.validateLoginInput(
            "androidtest@gmail.com",
            ""
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `invalid email and password returns false`(){

        val result = RegistrationUtil.validateLoginInput(
            "AndroidTest@Gmail.com",
            "123456789"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `valid email and password returns true`(){

        val result = RegistrationUtil.validateLoginInput(
            "androidtest@gmail.com",
            "123456789"
        )
        Truth.assertThat(result).isTrue()
    }


}