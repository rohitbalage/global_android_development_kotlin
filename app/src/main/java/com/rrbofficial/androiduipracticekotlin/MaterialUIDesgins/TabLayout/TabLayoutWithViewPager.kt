package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.TabLayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rrbofficial.androiduipracticekotlin.R

class TabLayoutWithViewPager : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tab_layout_with_view_pager)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        tabLayout = findViewById(R.id.tab_layout)
        // Set a custom title or remove the title
        supportActionBar?.title = "" // To remove the title

        viewPager = findViewById(R.id.pager)

        myAdapter = MyAdapter(supportFragmentManager)
        myAdapter.addData()
        viewPager.adapter = myAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}
