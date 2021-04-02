package com.example.unittest.testing

/**
 * Created by SARATH on 02-04-2021
 */
object LoginUtil {

    fun validateLoginInput(
        email : String,
        password : String
    ) : Boolean{

        if (email.isEmpty() || password.isEmpty()){
            return false
        }
        if (email=="sarath1@gmail.com" && password!="sarath123"){
            return false
        }
        if (email!="sarath1@gmail.com" && password!="sarath123"){
            return false
        }
        return true
    }
}