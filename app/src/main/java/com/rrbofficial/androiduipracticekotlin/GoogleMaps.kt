package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGoogleMapsBinding
import com.rrbofficial.androiduipracticekotlin.misc.CameraAndViewport
import com.rrbofficial.androiduipracticekotlin.misc.TypeAndStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private lateinit var map: GoogleMap

    private val typeAndStyle by lazy { TypeAndStyle() }

    private val cameraAndViewport by lazy { CameraAndViewport() }

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
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Cleveland and move the camera
        val cleveland = LatLng(41.49878830382801, -81.67565041048111)

        val newYork = LatLng(40.7128, -74.0060)

        val clevelandMarker =
            map.addMarker(MarkerOptions().position(cleveland).title("Marker in Cleveland").draggable(true))

        clevelandMarker?.tag = "Cleveland city"


        // set the camera position by animating the camera to the location
//        map.animateCamera(CameraUpdateFactory.newLatLng(cleveland),4000,null)

        // Set camera position and zoom when map is loaded
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cleveland, 10f))

        // set the location by moving the camera to boundary
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition))

        // set on marker click listener
//        map.setOnMarkerClickListener(this)

        // set on marker drag listener
        map.setOnMarkerDragListener(this)


        // Apply map settings
        map.uiSettings.apply {
            // Zoom controls enabled
            isZoomControlsEnabled = true
            // My location button enabled
//            isMyLocationButtonEnabled = false
//            // Scroll gestures enabled
//            isScrollGesturesEnabled = true
//            // Rotation enabled
//            isRotateGesturesEnabled = false
            // Show map icon
            isMapToolbarEnabled = true
        }

        // Set padding for plus minus buttons
//        map.setPadding(0, 0, 300, 0)

//        // Set map style
      typeAndStyle.setMapStyle(map, this)

        // Mim - Max zoom preference
//        map.setMinZoomPreference(10f)
//        map.setMaxZoomPreference(17f)


//        lifecycleScope.launch {
//            //delay seconds for the following operations
//            delay(4000)
//
        //  marker remove after 4 seconds

        //  clevelandMarker?.remove()


//            // set the location by animating the camera to cleveland inside all bounds
////            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition),2000,null)
//
//            // adding callback
//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition),2000,object : GoogleMap.CancelableCallback{
//                override fun onFinish() {
//                   Toast.makeText(this@GoogleMaps,"onFinish",Toast.LENGTH_SHORT).show()
//                }
//                override fun onCancel() {
//                    Toast.makeText(this@GoogleMaps,"onCancle",Toast.LENGTH_SHORT).show()
//                }
//            })
//
//            // Move Camera after few seconds
////              map.moveCamera(CameraUpdateFactory.newLatLng(newYork))
//
//            // Move Camera by scrolling after few seconds
//            // map.moveCamera(CameraUpdateFactory.scrollBy(100f,0f))
//
//
//            // use center of the bounds:
//          //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center,10f))
//
//            // zoom in by 3f values after the map launches and after 2 seconds
//            // map.moveCamera(CameraUpdateFactory.zoomBy(3f))
//
//            //  load map with exact boundaries  of the city  (in bounds of melbourne)
////            map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds,100))
//
//            // Animate camera to melbourne
////            map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds,100),2000,null)
//
//            // Animate camera to zoom inside cleveland
////            map.animateCamera(CameraUpdateFactory.zoomTo(15f),2000,null)
//
//
//            // Animate camera near cleveland  by scrolling to right
////            map.animateCamera(CameraUpdateFactory.scrollBy(200f,0f),2000,null)
//
//        }

        // Restrict map movement
//        map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)


        // Set click listeners
//        onMapClicked()
//        onMapLongClicked()
    }

    // single and long click listener

//    private fun onMapClicked() {
//        map.setOnMapClickListener {
//            Toast.makeText(this, " Single Clicked", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun onMapLongClicked() {
//        map.setOnMapLongClickListener {
//            Toast.makeText(
//                this,
//                "The coordinate is ${it.latitude} ${it.longitude}",
//                Toast.LENGTH_SHORT
//            ).show()
//            map.addMarker(MarkerOptions().position(it).title(" New Marker"))
//        }
//
//    }



    // Marker click listener :: NEED TO Override Main function: GoogleMap.OnMarkerClickListener of GoogleMap
//    override fun onMarkerClick(marker: Marker): Boolean {
//        Toast.makeText(
//            this,
//            "Cleveland clicked",
//            Toast.LENGTH_SHORT
//        ).show()
//        return true
//    }

    override fun onMarkerDrag(p0: Marker) {
      Log.d("MarkerDrag","Dragged")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        Log.d("MarkerDrag","Drag here start ${p0.position}")
    }

    override fun onMarkerDragStart(p0: Marker) {
        Log.d("MarkerDrag","Drag ends here ${p0.position}")
    }

}
