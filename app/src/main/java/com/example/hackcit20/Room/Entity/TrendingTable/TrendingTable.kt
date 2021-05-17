package com.example.hackcit20.Room.Entity.TrendingTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrendingTable(
    @PrimaryKey(autoGenerate = false)
    val VideoId:String,
    val VideoTitle:String,
    val VideoString:String
)