package com.rrbofficial.androiduipracticekotlin

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng

class Overlays {

    val cleveland = LatLng(41.49878830382801, -81.67565041048111)


    fun addGroundOverlay(map: GoogleMap) {
        val groundOverlayOptions = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(cleveland, 1000f, 1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.homeicon))
            }


        )
    }
}