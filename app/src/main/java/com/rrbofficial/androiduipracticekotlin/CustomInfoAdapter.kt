package com.rrbofficial.androiduipracticekotlin

import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class CustomInfoAdapter (context: Context) : InfoWindowAdapter{


    private val contentView: View = View.inflate(context, R.layout.custom_info_window , null)
    override fun getInfoContents(marker: Marker): View? {
       renderViews(marker, contentView)
        return contentView
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView
    }

    private fun renderViews(marker: Marker?, contents: View){
        val title = marker?.title
        val description = marker?.snippet

        val titleTextView = contents.findViewById<TextView>(R.id.title_textView)

        titleTextView.text = title

        val descriptionTextView = contents.findViewById<TextView>(R.id.description_textView)

        descriptionTextView.text = description



    }
}