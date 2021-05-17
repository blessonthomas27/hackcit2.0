package com.example.hackcit20.Room.Entity.FavoritesTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritesTable(
    @PrimaryKey(autoGenerate = true)
    val SNo:Int,
    val ProductName:String,
    val desc:String,
    val isPurchase:Boolean,
    val isDownloaded:Boolean
)