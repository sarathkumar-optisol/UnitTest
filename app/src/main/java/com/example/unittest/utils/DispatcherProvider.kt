package com.example.unittest.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by SARATH on 29-03-2021
 */
interface DispatcherProvider {
        val main : CoroutineDispatcher
        val io : CoroutineDispatcher
        val default : CoroutineDispatcher
        val unconfined : CoroutineDispatcher
}