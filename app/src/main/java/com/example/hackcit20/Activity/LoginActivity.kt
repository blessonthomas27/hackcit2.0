package com.example.hackcit20.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hackcit20.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class LoginActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    var storageRef: StorageReference = FirebaseStorage.getInstance().getReference()
    private lateinit var filepathsmall: Uri
    private lateinit var filepathbig: Uri
    lateinit var smallimage: ImageView
    lateinit var bigimg: ImageView
    lateinit var moviename: EditText
    lateinit var moviedescription: EditText
    lateinit var price: EditText
    lateinit var review: EditText
    lateinit var grade: EditText
    lateinit var duration: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        smallimage = findViewById(R.id.smallimg)
        bigimg = findViewById(R.id.Bigimg)
        moviename = findViewById(R.id.moviename)
        moviedescription = findViewById<EditText>(R.id.MovieDescpritionn)
        price = findViewById(R.id.price)
        review = findViewById(R.id.Review)
        grade = findViewById(R.id.grade)
        duration = findViewById(R.id.duration)
        val upload = findViewById<Button>(R.id.upload)

        upload.setOnLongClickListener {
            update()
            true
        }
        smallimage.setOnLongClickListener {
            filechooser()
            true
        }
        bigimg.setOnLongClickListener {
            filechooser1()
            true
        }


    }


    private fun databasegetter(){

    }


    private fun filechooser() {
        val i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Select an image"), 1)
    }

    private fun filechooser1() {
        val i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Select an image"), 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            filepathsmall = data.data!!

            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepathsmall)
            smallimage.setImageBitmap(bitmap)
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.data != null) {
            filepathbig = data.data!!

            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepathbig)
            bigimg.setImageBitmap(bitmap)
        }
    }


    private fun update() {


        if (moviename.text != null) {
            val refsmall = storageRef.child("images/small/" + moviename.text + ".jpg")
            Toast.makeText(this, "goingsmall" + refsmall, Toast.LENGTH_SHORT).show()
            refsmall.putFile(filepathsmall).addOnSuccessListener {
                Toast.makeText(this, "Sucessfully added small", Toast.LENGTH_SHORT).show()
                val refbig = storageRef.child("images/big/" + moviename.text + ".jpg")
                refbig.putFile(filepathbig).addOnSuccessListener {
                    refbig.downloadUrl.addOnSuccessListener {
                        aa(it.toString(),refsmall)
                    }
                    Toast.makeText(this, "Sucessfully added big", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "failed : " + it, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "failed : " + it, Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun aa(bigimgg: String, refsmall: StorageReference) {
        refsmall.downloadUrl.addOnSuccessListener {
            aaa(bigimgg,it.toString())
        }
    }
    private fun clear(){
        moviename.text=null
        moviedescription.text=null
        price.text=null
        review.text=null
        grade.text=null
        duration.text=null
    }
    private fun aaa(bigimgg: String, smll: String) {

        val user = hashMapOf(
            "MovieName" to moviename.text.toString(),
            "MovieDescription" to moviedescription.text.toString(),
            "Price" to price.text.toString(),
            "review" to review.text.toString(),
            "grade" to "PG",
            "duration" to duration.text.toString(),
            "ImageProfile" to smll,
            "ImageBanner" to bigimgg
        )
        try {
            db.collection("ProductImage").document("Top Grossing").collection("list").document().set(user).addOnSuccessListener {
                clear()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "failed : " + e, Toast.LENGTH_LONG).show()
        }
    }


}