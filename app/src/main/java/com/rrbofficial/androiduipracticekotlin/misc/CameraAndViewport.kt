package com.rrbofficial.androiduipracticekotlin.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds


// Camera and Viewport for Clevleland with 45 degree tilt
class CameraAndViewport {
   val clevelandposition : CameraPosition = CameraPosition.Builder()
           .target(LatLng(41.49878830382801, -81.67565041048111))
            .zoom(15f)
    .bearing(0f)
    .tilt(45f)
    .build()


    // Bounds for Melbourne
    val melbourneBounds = LatLngBounds(LatLng(-37.8136, 144.9631),  // SW Corner
        LatLng(-37.8038, 144.9772))  // NE Corner
}
