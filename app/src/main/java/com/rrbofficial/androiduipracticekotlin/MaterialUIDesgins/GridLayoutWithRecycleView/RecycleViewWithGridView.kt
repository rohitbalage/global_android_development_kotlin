package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.GridLayoutWithRecycleView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrbofficial.androiduipracticekotlin.R

class RecycleViewWithGridView : AppCompatActivity() {

    private lateinit var arrayList: ArrayList<GridPojo>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterRecycler: AdapterRecycler
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view_with_grid_view)

        // Toolbar setup
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Set initial title
        supportActionBar?.title = ""

        gridListData()

        recyclerView = findViewById(R.id.recyclerViewLinear)
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = gridLayoutManager

        adapterRecycler = AdapterRecycler(this, arrayList)
        recyclerView.adapter = adapterRecycler
    }

    private fun gridListData() {
        arrayList = ArrayList()

        arrayList.add(GridPojo("Mobile", R.drawable.homeicon))
        arrayList.add(GridPojo("Internet", R.drawable.homeicon))
        arrayList.add(GridPojo("WiFi", R.drawable.homeicon))
        arrayList.add(GridPojo("Database",  R.drawable.homeicon))
        arrayList.add(GridPojo("Battery",  R.drawable.homeicon))
        arrayList.add(GridPojo("Alarm",  R.drawable.homeicon))
        arrayList.add(GridPojo("Signal",  R.drawable.homeicon))
        arrayList.add(GridPojo("Bluetooth",  R.drawable.homeicon))
        arrayList.add(GridPojo("Monitor",  R.drawable.homeicon))
        arrayList.add(GridPojo("Wallpaper",  R.drawable.homeicon))
    }
}
