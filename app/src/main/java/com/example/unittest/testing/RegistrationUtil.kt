package com.example.unittest.testing

/**
 * Created by SARATH on 02-04-2021
 */
object RegistrationUtil {

    fun validateLoginInput(
        email : String,
        password : String
    ) : Boolean{

        if (email.isEmpty() || password.isEmpty()){
            return false
        }
        if (password.count()<=8){
            return false
        }
        if (! (email.contains('@') && email.contains('.'))){
            return false
        }
        if (haveCapital(email)){
            return false
        }
        return true
    }

    private fun haveCapital(email: String): Boolean {

        for ( i in email){
            if (i in 'A'..'Z'){
                return true
            }
        }
        return false
    }
}