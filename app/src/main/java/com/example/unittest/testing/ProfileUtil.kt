package com.example.unittest.testing

/**
 * Created by SARATH on 05-04-2021
 */
object ProfileUtil {

    fun validateUserProfile(
            email : String,
            password : String,
            age : Int
    ) : Boolean{

        if (email.isEmpty() || password.isEmpty() || age==0){
            return false
        }
        if (email=="sarath1@gmail.com" && password!="sarath123" && age==0){
            return false
        }
        if (email=="" && password!="sarath123" && age==0){
            return false
        }
        if (email=="sarath1@gmail.com" && password!="" && age==0){
            return false
        }
        return true
    }
}