package com.rrbofficial.androiduipracticekotlin

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class LocationDialogFragment : DialogFragment(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_dialog, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val countryNameTextView = view.findViewById<TextView>(R.id.country_name)
        val addressTextView = view.findViewById<TextView>(R.id.address_text)
        val dismissButton = view.findViewById<Button>(R.id.dismiss_button)

        // Correctly find and initialize MapView
        mapView = view.findViewById<MapView>(R.id.map_fragment_container)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        dismissButton.setOnClickListener {
            dismiss()
        }

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permissions if not granted
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
            return view
        }

        // Fetch last known location and update UI
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val addresses: MutableList<Address>? = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val address = addresses[0]
                        val countryName = address.countryName
                        val fullAddress = address.getAddressLine(0)
                        val continent = getContinentFromCountryCode(address.countryCode)

                        // Format the message based on continent and country
                        val formattedMessage = when (continent) {
                            "North America" -> "You are in North America \n $countryName, $fullAddress"
                            "South America" -> "You are in South America \n $countryName, $fullAddress"
                            "Europe" -> "You are in Europe \n, $countryName, $fullAddress"
                            "Asia" -> "You are in Asia \n $countryName, $fullAddress"
                            "Australia" -> "You are in Australia \n $countryName, $fullAddress"
                            "Antarctica" -> "You are in Antarctica \n $countryName, $fullAddress"
                            "Africa" -> "AFRICA, $countryName, $fullAddress"
                            else -> "$countryName, $fullAddress"
                        }

                        countryNameTextView.text = formattedMessage
                        addressTextView.text = fullAddress

                        val locationLatLng = LatLng(location.latitude, location.longitude)
                        googleMap.addMarker(MarkerOptions().position(locationLatLng).title("You are here"))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 15f))
                    }
                }
            }
        }

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun getContinentFromCountryCode(countryCode: String): String {
        return when (countryCode) {
            "US", "CA", "MX" -> "North America"
            "BR", "AR", "CO", "CL", "PE", "VE" -> "South America"
            "FR", "DE", "GB", "IT", "ES", "RU" -> "Europe"
            "CN", "IN", "JP", "KR", "SA", "IR" -> "Asia"
            "AU", "NZ" -> "Australia"
            "ZA", "EG", "NG", "DZ", "MA" -> "Africa"
            "AQ" -> "Antarctica"
            else -> "Unknown"
        }
    }
}
