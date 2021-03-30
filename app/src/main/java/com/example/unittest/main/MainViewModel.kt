package com.example.unittest.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittest.db.UserDatabase
import com.example.unittest.modals.LogInData
import com.example.unittest.modals.UserProfile
import com.example.unittest.utils.Resource
import com.example.unittest.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by SARATH on 29-03-2021
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val db : UserDatabase,
    private val dispatchers : DispatcherProvider
) : ViewModel() {

    sealed class RegisterEvent {
        class Success(val result: String) : RegisterEvent()
        class Failure(val error: String) : RegisterEvent()
        object Loading : RegisterEvent()
        object Empty : RegisterEvent()
    }
    sealed class ProfileListEvent {
        class Success(val result: List<UserProfile>) : ProfileListEvent()
        class Failure(val error: String) : ProfileListEvent()
        object Loading : ProfileListEvent()
        object Empty : ProfileListEvent()
    }

    sealed class LoginEvent {
        class Success(val result: String) : LoginEvent()
        class Failure(val error: String) : LoginEvent()
        object Loading : LoginEvent()
        object Empty : LoginEvent()
    }
    sealed class UserListEvent {
        class Success(val result: List<LogInData>) : UserListEvent()
        class Failure(val error: String) : UserListEvent()
        object Loading : UserListEvent()
        object Empty : UserListEvent()
    }

    sealed class UserProfileEvent {
        class Success(val result: Long) : UserProfileEvent()
        class Failure(val error: String) : UserProfileEvent()
        object Loading : UserProfileEvent()
        object Empty : UserProfileEvent()
    }

    private val _registerData = MutableStateFlow<RegisterEvent>(RegisterEvent.Empty)
    val registerData: StateFlow<RegisterEvent> = _registerData

    private val _loginData = MutableStateFlow<LoginEvent>(LoginEvent.Empty)
    val loginData: StateFlow<LoginEvent> = _loginData

    private val _userListData = MutableStateFlow<UserListEvent>(UserListEvent.Empty)
    val userListData: StateFlow<UserListEvent> = _userListData

    private val _userProfile = MutableStateFlow<UserProfileEvent>(UserProfileEvent.Empty)
    val userProfile: StateFlow<UserProfileEvent> = _userProfile

    private val _userProfileList = MutableStateFlow<ProfileListEvent>(ProfileListEvent.Empty)
    val userProfileList: StateFlow<ProfileListEvent> = _userProfileList

   fun registerUser(
       userName : String,
       password : String
   ){
       viewModelScope.launch(dispatchers.io) {
           _registerData.value = RegisterEvent.Loading
           when (val registrationResponse = repository.insertUserData(userName, password)) {
               is Resource.Error -> {
                   _registerData.value = RegisterEvent.Failure("Incorrect")
                   Log.d("MVIEWMODEL", "Incorrect")
               }
               is Resource.Success -> {
                   val data = registrationResponse.data
                   Log.d("$data", "registrationResponse")
                   if (data == null) {
                       _registerData.value = RegisterEvent.Failure("UnExpected Error")
                   } else {
                       _registerData.value = RegisterEvent.Success(data.toString())
                       Log.d("MVIEWMODEL", "success")
                   }
               }
           }

       }
   }

    fun getData(userName: String){
        viewModelScope.launch(dispatchers.io) {
            _loginData.value = LoginEvent.Loading
            when (val loginResponse = repository.getUserData(userName)) {
                is Resource.Error -> {
                    _registerData.value = RegisterEvent.Failure("Incorrect")
                    Log.d("MVIEWMODEL", "Incorrect")
                }
                is Resource.Success -> {
                    val data = loginResponse.data
                    Log.d("$data", "registrationResponse")
                    if (data == null) {
                        _registerData.value = RegisterEvent.Failure("UnExpected Error")
                    } else {
                        _registerData.value = RegisterEvent.Success(data.password)

                        Log.d("$data", "success")
                    }
                }
            }

        }
    }

    fun getUserList(){
        viewModelScope.launch(dispatchers.io) {
            _userListData.value = UserListEvent.Loading
            when (val userListResponse = repository.getUserList()) {
                is Resource.Error -> {
                    _userListData.value = UserListEvent.Failure("Incorrect")
                    Log.d("MVIEWMODEL", "Incorrect")
                }
                is Resource.Success -> {
                    val data = userListResponse.data
                    Log.d("$data", "ALLUSERDATA")
                    if (data == null) {
                        _userListData.value = UserListEvent.Failure("UnExpected Error")
                    } else {
                        _userListData.value = UserListEvent.Success(data)
                        Log.d("$data", "success")
                    }
                }
            }

        }
    }


    fun getUserProfileList(){
        viewModelScope.launch(dispatchers.io) {
            _userProfileList.value = ProfileListEvent.Loading
            when (val userprofileListResponse = repository.getUserProfileList()) {
                is Resource.Error -> {
                    _userProfileList.value = ProfileListEvent.Failure("Incorrect")
                    Log.d("MVIEWMODEL", "Incorrect")
                }
                is Resource.Success -> {
                    val data = userprofileListResponse.data
                    Log.d("$data", "ALLUSERDATA")
                    if (data == null) {
                        _userProfileList.value = ProfileListEvent.Failure("UnExpected Error")
                    } else {
                        _userProfileList.value = ProfileListEvent.Success(data)
                        Log.d("$data", "success")
                    }
                }
            }

        }
    }


    fun insertUserProfile(userProfile: UserProfile){
        viewModelScope.launch(dispatchers.io) {
            _userProfile.value = UserProfileEvent.Loading
            db.clearAllTables()
            when (val userProfileResponse = repository.insertUserProfile(userProfile)) {
                is Resource.Error -> {
                    _userProfile.value = UserProfileEvent.Failure("Incorrect")
                    Log.d("MVIEWMODEL", "Incorrect")
                }
                is Resource.Success -> {
                    val data = userProfileResponse.data
                    Log.d("$data", "ALLUSERDATA")
                    if (data == null) {
                        _userProfile.value = UserProfileEvent.Failure("UnExpected Error")
                    } else {
                        _userProfile.value = UserProfileEvent.Success(data)
                        Log.d("$data", "success")
                    }
                }
            }

        }
    }
}