package com.rrbofficial.androiduipracticekotlin.MapsAndServices.TomTomMaps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.MapFragment
import com.rrbofficial.androiduipracticekotlin.R

class TomTomMapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tom_tom_maps)


    }
}