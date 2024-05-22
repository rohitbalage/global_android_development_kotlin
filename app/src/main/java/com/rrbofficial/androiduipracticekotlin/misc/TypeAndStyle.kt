package com.rrbofficial.androiduipracticekotlin.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import com.rrbofficial.androiduipracticekotlin.R

class TypeAndStyle {
    fun setMapStyle(googleMap: GoogleMap, context: Context) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.dark_style
                )
            )
            if (!success) {
                Log.d("Maps", "failed to set map style")
            }
        } catch (e: Exception) {
            Log.d("Maps", e.toString())
        }
    }

    fun setMapType(item: MenuItem, map: GoogleMap) {
        when (item.itemId) {
            R.id.normal_map -> {
                map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.hybrid_map -> {
                map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID
            }

            R.id.satellite_map -> {
                map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.terrain_map -> {
                map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN
            }

            R.id.none_map -> {
                map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE
            }
        }
    }
}