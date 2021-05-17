package com.example.hackcit20.Room.Entity.DownloadTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DownloadTable(
    @PrimaryKey(autoGenerate = true)
    val SNo:Int,
    val ProductName:String,
    val desc:String,
    val DownloadDate:String
)