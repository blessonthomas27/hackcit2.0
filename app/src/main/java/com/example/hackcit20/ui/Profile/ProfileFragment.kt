package com.example.hackcit20.ui.Profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.utils.Easing
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hackcit20.R
import com.github.mikephil.charting.animation.Easing.EaseInOutQuad
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.exoplayer2.util.ColorParser

class ProfileFragment : Fragment() {
    lateinit var pieChart:PieChart
    val arr= listOf(Color.CYAN,ColorParser.parseTtmlColor("#FFA184"),Color.GREEN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        var toolbar: Toolbar =view.findViewById(R.id.ProfileFragmentToolbar)
        pieChart=view.findViewById(R.id.piechart)

       val xvalue= listOf(
           PieEntry(100f,"Purchased"),
           PieEntry(90f,"Watched"),
           PieEntry(30f,"To expire")
       )
        val PieDataSet=PieDataSet(xvalue,"")
        PieDataSet.apply {
            setColors(arr)
            valueTextSize=6f
            valueTextColor=Color.WHITE
        }

        val CardDetailCardView=view.findViewById<CardView>(R.id.CardDetailCardView)
        val NotificationCardView=view.findViewById<CardView>(R.id.NotificationCardView)
        val AppSettingCardView=view.findViewById<CardView>(R.id.AppSettingCardView)

        CardDetailCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_cardDetails)
        }
        NotificationCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_notification)
        }
        AppSettingCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_settings)
        }



        val pieData= PieData(PieDataSet)
        pieChart.apply {
            data=pieData
        isDrawHoleEnabled=false
        setDrawEntryLabels(false)
            setUsePercentValues(false)
            description.text="Your Activity"
            description.textSize=1f
            setDrawRoundedSlices(true)
            animateX(1000) }





        toolbar.inflateMenu(R.menu.profilemenu)
        toolbar.setOnMenuItemClickListener {
            if(it.itemId== R.id.editprofile){
                Toast.makeText(context,"clicked", Toast.LENGTH_LONG).show()
            }
            true
        }


        return view
    }
}