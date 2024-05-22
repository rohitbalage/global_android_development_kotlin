package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGoogleMapsBinding
import com.rrbofficial.androiduipracticekotlin.misc.CameraAndViewport
import com.rrbofficial.androiduipracticekotlin.misc.TypeAndStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private  val typeAndStyle by lazy {TypeAndStyle()}

    private   val cameraAndViewport by lazy { CameraAndViewport() }

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
        typeAndStyle.setMapType(item,map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Cleveland and move the camera
        val cleveland = LatLng(41.49878830382801, -81.67565041048111)

        val newYork = LatLng(40.7128, -74.0060)

        map.addMarker(MarkerOptions().position(cleveland).title("Marker in Cleveland"))

        // Set camera position and zoom when map is loaded

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cleveland, 10f))
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition))


       // Apply map settings
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

        // Set padding for plus minus buttons
//        map.setPadding(0, 0, 300, 0)

//        // Set map style
//      typeAndStyle.setMapStyle(map, this)

       // Mim - Max zoom preference
//        map.setMinZoomPreference(15f)
//        map.setMaxZoomPreference(17f)


        // Move Camera after few seconds

//       lifecycleScope.launch {
//           delay(4000)
//           map.moveCamera(CameraUpdateFactory.newLatLng(newYork)) }



        // Move Camera by scrolling after few seconds
//        lifecycleScope.launch {
//            delay(4000)
//            map.moveCamera(CameraUpdateFactory.scrollBy(100f,0f)) }

//         load map with exact boundries  of the city  (in bounds of melbourne)
        lifecycleScope.launch {
            delay(4000)
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds,100)) }


        // use center of the bounds:
//        lifecycleScope.launch {
//            delay(4000)
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center,10f)) }



        // zoom in by 3f values after the map launches and after 2 seconds
//    lifecycleScope.launch {
//        delay(2000)
//        map.moveCamera(CameraUpdateFactory.zoomBy(3f))
//    }


        // Restrict move map

        map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

    }
}
