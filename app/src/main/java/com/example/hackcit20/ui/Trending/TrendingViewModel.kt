package com.example.hackcit20.ui.Trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackcit20.dataclass.YoutbeData
import com.google.firebase.firestore.FirebaseFirestore

class TrendingViewModel : ViewModel() {

    lateinit var firestore: FirebaseFirestore

    var obj = mutableListOf<YoutbeData>()
    var live_obj = MutableLiveData<List<YoutbeData>>()


    init {
        firestore = FirebaseFirestore.getInstance()
        LiveExecute()
    }
    private fun LiveExecute() {
        firestore.collection("Trending")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                querySnapshot?.let {
                    obj.clear()
                    var i = 0
                    for (document in it) {
                        val temp: YoutbeData = document.toObject(YoutbeData::class.java)
                        obj.add(i++, temp)
                    }
                    live_obj.value = obj
                }
            }
    }
}