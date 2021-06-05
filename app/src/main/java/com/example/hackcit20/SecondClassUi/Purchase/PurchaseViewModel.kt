package com.example.hackcit20.SecondClassUi.Purchase

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackcit20.dataclass.ProductDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class PurchaseViewModel : ViewModel() {
    var firestore: FirebaseFirestore
    val mAuth: FirebaseAuth= FirebaseAuth.getInstance()
    val UID=mAuth.currentUser?.uid
    var cart = mutableListOf<ProductDetail>()
    var live_obj = MutableLiveData<MutableList<ProductDetail>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        getValue()
    }

    private fun getValue(){
        firestore.collection(UID.toString()).document("Purchase").collection("list")
            .get()
            .addOnSuccessListener { documents->
                var i = 0
                for (document in documents){
                    val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                    cart.add(i++, temp)
                }
                live_obj.value = cart
            }
    }
}