package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGoogleMapsBinding

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // Enable menu item in map
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    // Enable menu click events
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }

            R.id.none_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Cleveland and move the camera
        val cleveland = LatLng(41.49878830382801, -81.67565041048111)
        map.addMarker(MarkerOptions().position(cleveland).title("Marker in Cleveland"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cleveland, 10f))
        map.uiSettings.apply {
            // Zoom controls enabled
            isZoomControlsEnabled = true
            // My location button enabled
            isMyLocationButtonEnabled = false
            // Scroll gestures enabled
            isScrollGesturesEnabled = true
            // Rotation enabled
            isRotateGesturesEnabled = false
            // Show map icon
            isMapToolbarEnabled = true
        }
        // Set padding
//        map.setPadding(0, 0, 300, 0)

        // Set map style
        setMapStyle(map)
    }

    // style of map

    private fun setMapStyle(googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.aubergine_style
                )
            )
            if (!success) {
                Log.d("Maps", "failed to set map style")
            }
        }
            catch (e: Exception) {
                Log.d("Maps", e.toString())
            }
        }

    }
