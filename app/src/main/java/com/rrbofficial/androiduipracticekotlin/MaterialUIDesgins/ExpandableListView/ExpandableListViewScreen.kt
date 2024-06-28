package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.ExpandableListView

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R

class ExpandableListViewScreen : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var myExpandableListViewAdapter: MyExpandableListViewAdapter
    private lateinit var category: List<String>
    private lateinit var names: Map<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expandable_list_view)

        expandableListView = findViewById(R.id.expandable_listview)

        getAllData()

        myExpandableListViewAdapter = MyExpandableListViewAdapter(this, category, names)
        expandableListView.setAdapter(myExpandableListViewAdapter)

        // Listview on child click listener
        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            Toast.makeText(
                applicationContext,
                "${category[groupPosition]} : ${names[category[groupPosition]]?.get(childPosition)}",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun getAllData() {
        category = listOf("Fruits", "Flowers", "Animals")
        names = mapOf(
            "Fruits" to listOf("Mango", "Orange", "Banana", "Apple"),
            "Flowers" to listOf("Rose", "Lotus", "Jasmine", "Sunflower"),
            "Animals" to listOf("Lion", "Tiger", "Elephant", "Bear")
        )
    }
}
