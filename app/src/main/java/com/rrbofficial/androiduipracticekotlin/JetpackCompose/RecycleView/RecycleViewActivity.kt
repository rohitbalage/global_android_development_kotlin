package com.rrbofficial.androiduipracticekotlin.JetpackCompose.RecycleView

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrbofficial.androiduipracticekotlin.R

class RecycleViewActivity : AppCompatActivity() {
    val fruitsList = listOf<Fruit>(
        Fruit("mango", "Green delight"), Fruit("Apple", "Red Corp"), Fruit("Banana", "Golder factory"),
        Fruit("Guava", "Green delight"), Fruit("Lemon", "Red Corp"), Fruit("Pear", "Golder factory"),
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(
            fruitsList,

            ) { selectedItem: Fruit ->
            listItemClicked(selectedItem)
        }
    }

    private fun listItemClicked(fruit: Fruit) {
        Toast.makeText(
            this@RecycleViewActivity,
            "Supplier is : ${fruit.supplier}",
            Toast.LENGTH_LONG
        ).show()
    }
    }