package com.rrbofficial.androiduipracticekotlin

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class Markers {
    // Converting Customized Vector to Bitmap:

//    private fun fromVectorToBitmap(id: Int, color: Int, width: Int, height: Int): BitmapDescriptor {
//        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
//        if (vectorDrawable == null) {
//            Log.d("MapsActivity", "Resource not found")
//            return BitmapDescriptorFactory.defaultMarker()
//        }
//        val bitmap = Bitmap.createBitmap(
//            vectorDrawable.intrinsicWidth,
//            vectorDrawable.intrinsicHeight,
//            Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(bitmap)
//        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
//        DrawableCompat.setTint(vectorDrawable, color)
//        vectorDrawable.draw(canvas)
//
//        // Resize the bitmap to the desired width and height
//        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
//        return BitmapDescriptorFactory.fromBitmap(resizedBitmap)
//    }
}