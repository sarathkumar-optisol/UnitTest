package com.example.unittest.modals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by SARATH on 30-03-2021
 */
@Entity(
        tableName ="UserProfileData"
)
data class UserProfile(

        @SerializedName("name")
        var name : String,
        @SerializedName("email")
        var email : String,
        @SerializedName("age")
        var age : Int,
        @SerializedName("password")
        var password : String
){
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        var id: Int?=null

}