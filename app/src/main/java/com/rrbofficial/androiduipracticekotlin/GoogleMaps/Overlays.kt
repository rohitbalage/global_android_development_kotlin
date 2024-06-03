package com.rrbofficial.androiduipracticekotlin.GoogleMaps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlay
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.rrbofficial.androiduipracticekotlin.R

class Overlays {

    val cleveland = LatLng(41.49878830382801, -81.67565041048111)
    private val clevelandBounds = LatLngBounds(
        LatLng(41.13771174880029, -82.00516088670567),
        LatLng(41.75350247617428, -81.46719179554155)
    )

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {
        return map.addGroundOverlay(
            GroundOverlayOptions().apply {
//                position(cleveland, 1000f, 1000f)
                // position from bounds:: the ground overlay will be centered on the bounds
                positionFromBounds(clevelandBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.homeicon))
            }
        )
    }


    fun addGroundOverlayWithTag(map: GoogleMap): GroundOverlay? {
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
//                position(cleveland, 1000f, 1000f)
                // position from bounds:: the ground overlay will be centered on the bounds
                positionFromBounds(clevelandBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.homeicon))
            }
        )
        groundOverlay?.tag = "Home"
        return groundOverlay
    }
}