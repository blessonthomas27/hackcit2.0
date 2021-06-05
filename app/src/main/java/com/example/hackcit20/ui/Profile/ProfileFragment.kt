package com.example.hackcit20.ui.Profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hackcit20.Activity.SplashActivity
import com.example.hackcit20.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.exoplayer2.util.ColorParser
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var pieChart: PieChart
    val arr = listOf(Color.CYAN, ColorParser.parseTtmlColor("#FFA184"), Color.GREEN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.ProfileFragmentToolbar)
        pieChart = view.findViewById(R.id.piechart)
        val xvalue = listOf(
            PieEntry(100f, ""),
            PieEntry(90f, ""),
            PieEntry(30f, "")
        )
        val PieDataSet = PieDataSet(xvalue, "")
        PieDataSet.apply {
            setColors(arr)
            valueTextSize = 6f
            valueTextColor = Color.WHITE
        }
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
        mAuth = FirebaseAuth.getInstance()
        val CurrentUser = mAuth.currentUser
        val Email = view.findViewById<TextView>(R.id.email)
        val UserName = view.findViewById<TextView>(R.id.UserName)
        val ProfileImage = view.findViewById<ImageView>(R.id.profile_image)
        Email.text = CurrentUser?.email
        UserName.text = CurrentUser?.displayName
        Glide.with(this).load(CurrentUser?.photoUrl)
            .apply(options)
            .into(ProfileImage)

        val CartDetailCardView = view.findViewById<CardView>(R.id.CartDetailCardView)
        val DownloadsCardView = view.findViewById<CardView>(R.id.DownloadsCardView)
        val AppSettingCardView = view.findViewById<CardView>(R.id.AppSettingCardView)

        CartDetailCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_cardDetails)
        }
        DownloadsCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_downloads)
        }

        val pieData = PieData(PieDataSet)
        pieChart.apply {
            data=pieData
            isDrawHoleEnabled = false
            setDrawEntryLabels(false)
            setUsePercentValues(false)
            setEntryLabelColor(Color.WHITE)
            description.text = "Your Activity"
            description.textSize = 1f
            description.textColor = Color.WHITE
            setDrawRoundedSlices(true)
            animateX(1000)
            needsHighlight(1)
        }
        toolbar.inflateMenu(R.menu.profilemenu)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.signout) {
                FirebaseAuth.getInstance().signOut();
                onStart()
            }
            true
        }
     return view
    }

    private fun SiginOut() {
        val intent = Intent(context, SplashActivity::class.java)
        Toast.makeText(context, "Siging Out...", Toast.LENGTH_LONG).show()
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser == null) {
            SiginOut()
        }
    }

}