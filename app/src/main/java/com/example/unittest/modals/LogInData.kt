package com.example.unittest.modals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by SARATH on 29-03-2021
 */

@Entity(
    tableName ="UserLoginData"
)
data class LogInData(
    @SerializedName("userName")
    var userName : String,
    @SerializedName("password")
    var password : String
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}