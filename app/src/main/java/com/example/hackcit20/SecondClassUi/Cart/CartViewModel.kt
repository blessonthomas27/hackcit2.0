package com.example.hackcit20.SecondClassUi.Cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackcit20.dataclass.ProductDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CartViewModel:ViewModel() {
    var firestore: FirebaseFirestore
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val UID=mAuth.currentUser?.uid
    var cart = mutableListOf<ProductDetail>()
    var live_obj = MutableLiveData<MutableList<ProductDetail>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        LiveGetValue()
    }
    private fun LiveGetValue(){
        firestore.collection(UID.toString()).document("Cart")
            .collection("list").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                querySnapshot?.let {
                    cart.clear()
                    var i = 0
                    for (document in it){
                        val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                        cart.add(i++, temp)
                    }
                    live_obj.value = cart
                }
            }
    }
}