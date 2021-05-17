package com.example.hackcit20.Room.Entity.ProductTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductTable(
    @PrimaryKey (autoGenerate = false)
    val ProductId: String,
    val ProductName: String,
    val ImageProfile: String,
    val ImageBanner: String,
    val Title: String,
    val Description: String,
    val cast1Image: String,
    val cast2Image: String,
    val cast3Image: String,
    val cast4Image: String,
    val cast5Image: String,
    val cast1Name: String,
    val cast2Name: String,
    val cast3Name: String,
    val cast4Name: String,
    val cast5Name: String,
    val ProductReview: String,
    val ProductContent: String,
    val Duration: String
)