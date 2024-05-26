package com.rrbofficial.androiduipracticekotlin

import Shapes
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCircleClickListener
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGoogleMapsBinding
import com.rrbofficial.androiduipracticekotlin.misc.CameraAndViewport
import com.rrbofficial.androiduipracticekotlin.misc.TypeAndStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback,   GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener, OnPolygonClickListener,OnPolylineClickListener, OnCircleClickListener {

    private lateinit var map: GoogleMap
    // Add a marker in Cleveland and move the camera
    val cleveland = LatLng(41.49878830382801, -81.67565041048111)

    val akron = LatLng(41.0844671,-81.5953782,)

    val washingtondc = LatLng(38.8938592, -77.0969764)


    val columbus = LatLng(39.9830978,-83.1556356)

    val detroit = LatLng(42.3528084,-83.1816053)


    val pittsburg = LatLng(40.4314699,-80.0629009)


    val losangeles = LatLng(34.0206085,-118.7413725)

    val sanfransico = LatLng(37.7577607,-122.4787995)


    val toronto = LatLng(43.718371,-79.542864)

    val losvegas = LatLng(36.1251645,-115.3398072)

    val newyork = LatLng(40.6976312,-74.1444874)

    private val riodeJanero = LatLng(-22.9137295,-43.61079)

    val atlanta = LatLng(33.7674828,-84.5025311)

    val raleigh = LatLng(35.8439338,-78.8098631)

    val miami = LatLng(25.7825389,-80.3118593)

    val austin = LatLng(30.3079541,-97.9205504)

    val madrid = LatLng(40.4380986,-3.8443431)

    val colarado = LatLng(38.9724786,-108.1904446)

    val oklahoma = LatLng(35.2848492,-101.3565999)

    val dallas = LatLng(32.8208451,-96.8963602)

    val minneapolis = LatLng(44.9707888,-93.3438787)

    val saltlake = LatLng(40.7767086,-112.0028854)

    val stlouis = LatLng(38.6532056,-90.3258624)

    val chicago = LatLng(41.833871,-87.8967702)

    val kansas = LatLng(38.4727673,-100.9597019)

    val seattle = LatLng(47.6088285,-122.5046032)

    val boston = LatLng(42.3144474,-71.0526843)

    val mexico = LatLng (18.1166199,-78.5246324)

    val rome = LatLng(41.9102088,12.3711917)
    val milan = LatLng(45.4628246,9.0953321)
    val turin = LatLng(45.4628246,9.0953321)
    val bari = LatLng(41.1114772,16.7996798)
    val florence = LatLng(43.7800127,11.1997685)
    val padova = LatLng(45.4065566,11.850046)

    private val typeAndStyle by lazy { TypeAndStyle() }

    private val shapes by lazy { Shapes() }

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

        /* By this: Customized marker color to yellowgreen  .icon(BitmapDescriptorFactory.defaultMarker(134f)) hsl color link:: https://www.w3schools.com/colors/colors_hsl.asp
         or color from BitmapDescriptorFactory ::   .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        */

        /*
        *  How to create a drawable marker  with vector asset::
        val clevelandMarker = map.addMarker(
            MarkerOptions()
                .position(cleveland)
                .title("Marker in Cleveland")
                .icon(fromVectorToBitmap(R.drawable.baseline_accessibility_24, Color.parseColor("#07E4F2"), 200, 200)) // Adjust width and height as needed
        )
        *
        *
        *
        *  Image marker in Map::
         val clevelandMarker = map.addMarker(
            MarkerOptions()
                .position(cleveland)
                .title("Marker in Cleveland")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carpixelicon)
                )) // Adjust width and height as needed
        *
        *
        * // Add rotation, visibility, flatness to the map
        *
        *  val clevelandMarker = map.addMarker(
            MarkerOptions()
                .position(cleveland)
                .title("Marker in Cleveland")
                .alpha(0.5f)  // Adjust visibility of marker
                .rotation(90f)// Adjust rotation of marker
                .flat(true) // Adjust flatness of marker | means marker is rotated as soon as user try to rotate map
        )
        *
        *
        * */


        val clevelandMarker = map.addMarker(
            MarkerOptions()
                .position(cleveland)
                .title("Marker in Cleveland")
                .snippet("Some text")
                .zIndex(1f)
        )

        val sanfranciscoMarker = map.addMarker(
            MarkerOptions()
                .position(sanfransico)
                .title("Marker in San Francisco")
                .snippet("Some text"))


        val losangelesMarker = map.addMarker(
            MarkerOptions()
                .position(losangeles)
                .title("Marker in Los Angeles")
                .snippet("Some text"))

        val riodejaneroMarker = map.addMarker(
            MarkerOptions()
                .position(riodeJanero)
                .title("Marker in Rio de Janeiro")
                .snippet("Some text"))

        val losvegasMarker = map.addMarker(
            MarkerOptions()
                .position(losvegas)
                .title("Marker in Los Vegas")
                .snippet("Some text"))


        val seattleMarker = map.addMarker(
            MarkerOptions()
                .position(seattle)
                .title("Marker in Seattle")
                .snippet("Some text")
                .zIndex(1f)
        )
        val bostonMarker = map.addMarker(
            MarkerOptions()
                .position(boston)
                .title("Marker in Boston")
                .snippet("Some text"))

        val atlantaMarker = map.addMarker(
            MarkerOptions()
                .position(atlanta)
                .title("Marker in Atlanta")
                .snippet("Some text"))

        val chicagoMarker = map.addMarker(
            MarkerOptions()
                .position(chicago)
                .title("Marker in Chicago")
                .snippet("Some text"))

        val newyorkMarker = map.addMarker(
            MarkerOptions()
                .position(newyork)
                .title("Marker in New York")
                .snippet("Some text"))

        val dallasMarker = map.addMarker(
            MarkerOptions()
                .position(dallas)
                .title("Marker in Dallas")
                .snippet("Some text"))

        val akronMarker = map.addMarker(
            MarkerOptions()
                .position(akron)
                .snippet("Some text")
                .title("Marker in Akron")
        )

        val columbusMarker = map.addMarker(
            MarkerOptions()
                .position(columbus)
                .snippet("Some text")
                .title("Marker in Columbus"))

        val minneapolisMarker = map.addMarker(
            MarkerOptions()
                .position(minneapolis)
                .snippet("Some text")
                .title("Marker in Minneapolis"))


        val kansasMarker = map.addMarker(
            MarkerOptions()
                .position(kansas)
                .snippet("Some text")
                .title("Marker in Kansas"))

        val stlouisMarker = map.addMarker(
            MarkerOptions()
                .position(stlouis)
                .snippet("Some text")
                .title("Marker in St. Louis"))



        val madridMarker = map.addMarker(
            MarkerOptions()
                .position(madrid)
                .snippet("Some text")
                .title("Marker in Madrid"))

        val raleighMarker = map.addMarker(
            MarkerOptions()
                .position(raleigh)
                .snippet("Some text")
                .title("Marker in Raleigh"))

        val miamiMarker = map.addMarker(
            MarkerOptions()
                .position(miami)
                .snippet("Some text")
                .title("Marker in Miami"))

        val oklahomaMarker = map.addMarker(
            MarkerOptions()
                .position(oklahoma)
                .snippet("Some text")
                .title("Marker in Oklahoma"))

        val saltlakeMarker = map.addMarker(
            MarkerOptions()
                .position(saltlake)
                .snippet("Some text")
                .title("Marker in Salt Lake City"))


        val austinMarker = map.addMarker(
            MarkerOptions()
                .position(austin)
                .snippet("Some text")
                .title("Marker in Austin"))

        val colaradoMarker = map.addMarker(
            MarkerOptions()
                .position(colarado)
                .snippet("Some text")
                .title("Marker in Colarado"))


        val detroitMarker = map.addMarker(
            MarkerOptions()
                .position(detroit)
                .snippet("Some text")
                .title("Marker in Detroit"))

        val washingtonMarker = map.addMarker(
            MarkerOptions()
                .position(washingtondc)
                .snippet("Some text")
                .title("Marker in Washington")
        )

        val pittsburgMarker = map.addMarker(
            MarkerOptions()
                .position(pittsburg)
                .snippet("Some text")
                .title("Marker in Pittsburg"))

        val romeMarker = map.addMarker(
            MarkerOptions()
                .position(rome)
                .snippet("Some text")
                .title("Marker in Rome"))

        val mexicoMarker = map.addMarker(
            MarkerOptions()
                .position(mexico)
                .snippet("Some text")
                .title("Marker in Mexico"))

        val turinMarker = map.addMarker(
            MarkerOptions()
                .position(turin)
                .snippet("Some text")
                .title("Marker in Turin"))

        val bariMarker = map.addMarker(
            MarkerOptions()
                .position(bari)
                .snippet("Some text")
                .title("Marker in Bari"))

        val florenceMarker = map.addMarker(
            MarkerOptions()
                .position(florence)
                .snippet("Some text")
                .title("Marker in Florence"))

        val padovaMarker = map.addMarker(
            MarkerOptions()
                .position(padova)
                .snippet("Some text")
                .title("Marker in Padova"))

        val torontoMarker = map.addMarker(
            MarkerOptions()
                .position(toronto)
                .snippet("Some text")
                .title("Marker in Toronto"))

        val milanMarker = map.addMarker(
            MarkerOptions()
                .position(milan)
                .snippet("Some text")
                .title("Marker in Milan"))



        // set customInfo marker adapter
        map.setInfoWindowAdapter(CustomInfoAdapter(this))


        // set tag for marker
//        clevelandMarker?.tag = "Cleveland city"


        // set the camera position by animating the camera to the location
//        map.animateCamera(CameraUpdateFactory.newLatLng(cleveland),4000,null)

        // Set camera position and zoom when map is loaded
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cleveland, 10f))

        // set the location by moving the camera to boundary
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition))

        // set on marker click listener here first
        map.setOnMarkerClickListener(this)

        // set on marker drag listener here first
        map.setOnMarkerDragListener(this)

        // set polyline function between cities in map without delay
//        addPolyline()


        // set polyline function between cities in map with delay [from Shapes.kt class]
        lifecycleScope.launch {
            //delay seconds for the polyline function
            delay(4000)
            // set polyline from shapes class
            shapes.addPolyline(map)
            shapes.addPolyline2(map)
        }

        // set on polyline click listener here first
        map.setOnPolylineClickListener(this)

        // set Polygon in map by calling shapes class
        shapes.addPolygon(map)


        // set on circle click listener here first
        map.setOnCircleClickListener(this)

        //set polygon click listener
        map.setOnPolygonClickListener(this)


        // add circle in map
        lifecycleScope.launch {
            shapes.addCircle(map)
        }






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
//             Show map icon
            isMapToolbarEnabled = true
        }

        // Set padding for plus minus buttons
//        map.setPadding(0, 0, 300, 0)

//        // Set map style
//        typeAndStyle.setMapStyle(map, this)

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
        map.animateCamera(
            CameraUpdateFactory.newCameraPosition(cameraAndViewport.clevelandposition),
            2000,
            null
        )
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
//            map.animateCamera(CameraUpdateFactory.zoomTo(15f),2000,null)
//
//
//            // Animate camera near cleveland  by scrolling to right
////            map.animateCamera(CameraUpdateFactory.scrollBy(200f,0f),2000,null)
//
//        }

        // Restrict map movement
//        map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

//
//         Set click listeners
//        onMapClicked()
        onMapLongClicked()
    }

    // single and long click listener

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this, " Single Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            Toast.makeText(
                this,
                "The coordinate is ${it.latitude} ${it.longitude}",
                Toast.LENGTH_SHORT
            ).show()
            map.addMarker(MarkerOptions().position(it).title(" New Marker"))
        }

    }


    //     Marker click listener :: NEED TO Override Main function: GoogleMap.OnMarkerClickListener of GoogleMap
    override fun onMarkerClick(marker: Marker): Boolean {


        // show animate camera to zoom in when click marker
        map.animateCamera(CameraUpdateFactory.zoomTo(17f),2000,null)

        marker.showInfoWindow()

        // show info window

        // to add a toast message on marker clicked
//        Toast.makeText(
//            this,
//            "Cleveland clicked",
//            Toast.LENGTH_SHORT
//        ).show()
        return true
    }


    // functions for marker drag listener:: NEED TO Override Main function: GoogleMap.OnMarkerDragListener of GoogleMap
    override fun onMarkerDrag(p0: Marker) {
        Log.d("MarkerDrag", "Dragged")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        Log.d("MarkerDrag", "Drag here start ${p0.position}")
    }

    override fun onMarkerDragStart(p0: Marker) {
        Log.d("MarkerDrag", "Drag ends here ${p0.position}")
    }
    override fun onPolylineClick(p0: Polyline) {
        Toast.makeText(this,"Polyline clicked", Toast.LENGTH_SHORT).show()
    }


    // On circle shape  click listener
    override fun onCircleClick(p0: Circle) {
        Toast.makeText(this,"Circle is clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onPolygonClick(p0: Polygon) {
        Toast.makeText(this,"Polygon is clicked", Toast.LENGTH_SHORT).show()
    }


}