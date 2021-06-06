package com.example.hackcit20.ui.ProductDetail

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hackcit20.Activity.StreamingActivity
import com.example.hackcit20.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class ProductDetatil : Fragment(), View.OnClickListener {

    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.blackimage)
        .error(R.drawable.blackimage)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()
    var db = FirebaseFirestore.getInstance()
    var isDownloded=false
    val mAuth = FirebaseAuth.getInstance()
    val currentuser = mAuth.currentUser
    lateinit var ImageBanner: ImageView
    lateinit var ImageProfile: ImageView
    lateinit var MovieName: TextView
    lateinit var MovieDescription: TextView
    lateinit var Price: Button
    lateinit var Cart: Button
    lateinit var grade: TextView
    lateinit var review: TextView
    lateinit var duration: TextView
    lateinit var smll: String
    lateinit var bigimgg: String
    lateinit var money: String
    lateinit var StreamNow: Button
    lateinit var Download: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_detatil_fragment, container, false)


        ImageBanner = view.findViewById(R.id.ImageBanner)
        ImageProfile = view.findViewById(R.id.ImageProfile)
        MovieName = view.findViewById(R.id.MovieName)
        MovieDescription = view.findViewById(R.id.MovieDescription)
        grade = view.findViewById(R.id.grade)
        Price = view.findViewById(R.id.buy)
        Cart = view.findViewById(R.id.cart)
        StreamNow = view.findViewById(R.id.stream_now)
        Download = view.findViewById(R.id.Download)
        review = view.findViewById(R.id.review)
        duration = view.findViewById(R.id.duration)


        Price.visibility = View.GONE
        Cart.visibility = View.GONE



        arguments?.getString("MovieName").let { a ->
            MovieName.text = a
        }
        arguments?.getString("MovieDescription").let { a ->
            MovieDescription.text = a
        }
        arguments?.getString("Price").let { a ->
            if (a != null) {
                money = a
            }
            Price.text = "₹$a/- Buy"
        }
        arguments?.getString("grade").let { a ->
            grade.text = a
        }
        arguments?.getString("duration").let { a ->
            duration.text = a
        }
        arguments?.getString("review").let { a ->
            review.text = a
        }
        arguments?.getString("ImageBanner").let { bannerimage ->
            if (bannerimage != null) {
                bigimgg = bannerimage
            }
            Glide.with(this).load(bannerimage)
                .apply(options)
                .into(ImageBanner)
        }
        arguments?.getString("ImageProfile").let { bannerimage ->
            if (bannerimage != null) {
                smll = bannerimage
            }
            Glide.with(this).load(bannerimage)
                .apply(options)
                .into(ImageProfile)
        }
        val toolbar: Toolbar = view.findViewById(R.id.ProducDetailFragmentToolbar)
        toolbar.inflateMenu(R.menu.trendingmenu)
        return view
    }


    private fun declare() {
        if (currentuser != null) {
            db.collection(currentuser.uid)
                .document("Purchase").collection("list").document(MovieName.text.toString())
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val doc: DocumentSnapshot? = it.result
                        if (doc != null && doc.exists()) {
                            secondaryVisible()
                            return@addOnCompleteListener
                        } else {
                            primaryVisible()
                        }
                    } else {
                        primaryVisible()
                    }
                }
            db.collection(currentuser.uid)
                .document(MovieName.text.toString())
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val doc: DocumentSnapshot? = it.result
                        if (doc != null && doc.exists()) {
                            isDownloded=true
                            return@addOnCompleteListener
                        }
                    }
                }
        }
    }
    fun BuyOnClick() {
        RemoveFromCart(MovieName.text.toString())
        AddToPurchase(MovieName.text.toString())
    }

    fun primaryVisible() {
        StreamNow.visibility = View.GONE
        Download.visibility = View.GONE
        Price.visibility = View.VISIBLE
        Cart.visibility = View.VISIBLE
        Price.setOnClickListener(this)
        Cart.setOnClickListener(this)
    }

    fun secondaryVisible() {
        Price.visibility = View.GONE
        Cart.visibility = View.GONE
        StreamNow.visibility = View.VISIBLE
        Download.visibility = View.VISIBLE
        StreamNow.setOnClickListener(this)
        Download.setOnClickListener(this)
    }


    fun AddToCart(dockname: String) {
        val cart = hashMapOf(
            "MovieName" to MovieName.text.toString(),
            "MovieDescription" to MovieDescription.text.toString(),
            "Price" to money,
            "review" to review.text.toString(),
            "grade" to grade.text.toString(),
            "duration" to duration.text.toString(),
            "ImageProfile" to smll,
            "ImageBanner" to bigimgg
        )
        db.collection(currentuser?.uid.toString()).document("Cart").collection("list")
            .document(dockname)
            .set(cart)
        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
    }

    fun RemoveFromCart(dockname: String) {
        db.collection(currentuser?.uid.toString()).document("Cart").collection("list")
            .document(dockname).delete()
    }

    fun AddToPurchase(dockname: String) {
        val download = hashMapOf(
            "MovieName" to MovieName.text.toString(),
            "MovieDescription" to MovieDescription.text.toString(),
            "Price" to Price.text.toString(),
            "review" to review.text.toString(),
            "grade" to grade.text.toString(),
            "duration" to duration.text.toString(),
            "ImageProfile" to smll,
            "ImageBanner" to bigimgg
        )
        val orderdetails = hashMapOf(
            "Uid" to currentuser?.uid.toString(),
            "UserName" to currentuser?.displayName.toString(),
            "MovieName" to MovieName.text.toString(),
            "Price" to money
        )
        try {

        }catch (e : Exception){}
        if (currentuser != null) {
            db.collection(currentuser.uid)
                .document("Purchase").collection("list").document(dockname)
                .set(download)
            db.collection("Orders").document(currentuser.uid + MovieName.text.toString())
                .set(orderdetails)
                onStart()
        }
    }


    //OnclickMethods
    fun PriceOnClick() {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Payment (TEST MODE!)")
            setMessage("Proceed to pay ")
            setPositiveButton(
                "" + Price.text,
                { dialogInterface: DialogInterface, i: Int ->
                    BuyOnClick()
                })
            setNegativeButton("cancel", { dialogInterface: DialogInterface, i: Int -> })
            show()
        }
    }


    fun CartOnClick() {
        AddToCart(dockname = MovieName.text.toString())
    }

    fun StreamOnClick() {
        val indent = Intent(context, StreamingActivity::class.java)
        startActivity(indent)
    }

    fun DownloadOnClick() {
        val permisioncode = 1000
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
                == PackageManager.PERMISSION_DENIED) {
                val permisidon = (android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(arrayOf(permisidon), permisioncode);
            } else {
                startdownloding()
                setDownload()
            }
        } else {
            startdownloding()
            setDownload()
        }

    }
    fun setDownload(){
        val user = hashMapOf(
            "isDownloded" to true
        )
        if (currentuser != null) {
            db.collection(currentuser.uid)
                .document(MovieName.text.toString())
                .set(user)
            onStart()
        }
    }

    private fun startdownloding() {
        val downloadurl =
            "https://firebasestorage.googleapis.com/v0/b/hackcit.appspot.com/o/movietesting%2Fminions.mp4?alt=media&token=fb1416f9-f68a-493d-b641-db92f2b9b27f"

        val req = DownloadManager.Request(Uri.parse(downloadurl))
        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        req.apply {
            setTitle("Download")
            setDescription("Downloading file...")
            allowScanningByMediaScanner()
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "" + System.currentTimeMillis()
            )
        }
        val manager: DownloadManager =
            context?.getSystemService(android.content.Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(req)
    }

    override fun onClick(v: View) {
        when (v) {
            Price ->
                if (Price.text != "Buy") {
                    PriceOnClick()
                } else {
                    Toast.makeText(
                        context,
                        "Somthing Went Wrong!!" + Price.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            Cart -> if (Price.text != "Buy") {
                CartOnClick()
            } else {
                Toast.makeText(context, "Somthing Went Wrong!!" + Price.text, Toast.LENGTH_SHORT)
                    .show()
            }
            StreamNow -> StreamOnClick()
            Download -> if(isDownloded!=true){
                DownloadOnClick()
            }else{
                Toast.makeText(
                    context,
                    "Alredy Downloded" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        declare()
    }
}