package com.example.hackcit20.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackcit20.dataclass.*
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    var firestore: FirebaseFirestore
    var tamil = mutableListOf<ProductDetail>()
    var hollywood = mutableListOf<ProductDetail>()
    var bollywood = mutableListOf<ProductDetail>()
    var animate = mutableListOf<ProductDetail>()
    var live_obj_Tamil = MutableLiveData<MutableList<ProductDetail>>()
    var live_obj_Bollywood = MutableLiveData<MutableList<ProductDetail>>()
    var live_obj_Hollywood = MutableLiveData<MutableList<ProductDetail>>()
    var live_obj_Animate = MutableLiveData<MutableList<ProductDetail>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        BollywoodExecute()
    }

    private fun BollywoodExecute() {
        firestore.collection("ProductImage").document("Animated").collection("list")
            .get()
            .addOnSuccessListener { documents ->
                var i = 0
                for (document in documents) {
                    val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                    animate.add(i++, temp)
                }
                live_obj_Animate.value = animate
            }

        firestore.collection("ProductImage").document("Hollywood").collection("list")
            .get()
            .addOnSuccessListener { documents ->
                var i = 0
                for (document in documents) {
                    val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                    hollywood.add(i++, temp)
                }
                live_obj_Hollywood.value = hollywood
            }
        firestore.collection("ProductImage").document("Bollywood").collection("list")
            .get()
            .addOnSuccessListener { documents ->
                var i = 0
                for (document in documents) {
                    val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                    bollywood.add(i++, temp)
                }
                live_obj_Bollywood.value = bollywood
            }
        firestore.collection("ProductImage").document("Tamil").collection("list")
            .get()
            .addOnSuccessListener { documents ->
                var i = 0
                for (document in documents) {
                    val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                    tamil.add(i++, temp)
                }
                live_obj_Tamil.value = tamil
            }
    }
}