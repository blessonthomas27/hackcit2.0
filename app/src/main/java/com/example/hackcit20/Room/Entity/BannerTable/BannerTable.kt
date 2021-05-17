package com.example.hackcit20.Room.Entity.BannerTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BannerTable(
    @PrimaryKey(autoGenerate = true)
    val SNo:Int,
    val Title:String,
    val Image:String
)