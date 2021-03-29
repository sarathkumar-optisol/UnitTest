package com.example.unittest.modals

/**
 * Created by SARATH on 29-03-2021
 */
sealed class Resource<T>(val data : T? , val message : String?) {

    class Success<T>(data: T?) :Resource<T>(data,null)
    class Error<T>(message : String) :Resource<T>(null, message)


}