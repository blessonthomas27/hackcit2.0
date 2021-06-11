package com.example.hackcit20.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Api_key
import com.example.hackcit20.ui.ProductDetail.ProductDetatil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.PaymentResultListener


class MainActivity2 : AppCompatActivity(), PaymentResultListener {

    val firestore = FirebaseFirestore.getInstance()
    var Ykey: String = ""
    var Rkey: String = ""

    override fun onStart() {
        super.onStart()
        getKey()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if (Ykey==""&&Rkey==""){
            getKey()
        }

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    fun getKey() {
        firestore.collection("API_KEY").document("Key")
            .get().addOnSuccessListener { document ->
                val temp: Api_key? = document.toObject(Api_key::class.java)
                if (temp != null) {
                    Ykey = temp.YOUTUBE_API_KEY
                    Rkey=temp.RAZORPAY_API_KEY
                }
            }
    }
    fun passKey(): Api_key? {
        if(Ykey!=""&&Rkey!==""){
            return Api_key(Ykey,Rkey)
        }else{
            getKey()
            return null
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed!", Toast.LENGTH_LONG).show()
    }

}