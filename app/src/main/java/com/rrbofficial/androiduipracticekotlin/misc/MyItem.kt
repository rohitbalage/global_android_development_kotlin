package com.rrbofficial.androiduipracticekotlin.misc

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MyItem(
    private val position: LatLng, // Corrected variable name
    private val title: String,
    private val snippet: String,
    private val zIndex: Float
): ClusterItem {
    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String? {
        return title
    }

    override fun getSnippet(): String? {
        return snippet
    }

    override fun getZIndex(): Float? {
        return zIndex
    }
}
