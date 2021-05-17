package com.example.hackcit20.ui.Library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.hackcit20.R

class LibraryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)



        val DownloadCardView=view.findViewById<CardView>(R.id.DownloadCardView)
        val WatchlistCardView=view.findViewById<CardView>(R.id.WatchlistCardView)
        val FavoritesCardView=view.findViewById<CardView>(R.id.FavoritesCardView)
        DownloadCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_library_to_downloads)
        }
        WatchlistCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_library_to_watchList)
        }
        FavoritesCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_library_to_favourites)

        }
        return view
    }
}