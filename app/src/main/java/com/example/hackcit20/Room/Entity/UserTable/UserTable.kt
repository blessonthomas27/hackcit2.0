package com.example.hackcit20.Room.Entity.UserTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserTable(
    @PrimaryKey (autoGenerate = false)
    val UID:String,
    val Name:String,
    val email:String,
    val Age:Int,
    val Gender:String,
    val PhoneNo:String
)