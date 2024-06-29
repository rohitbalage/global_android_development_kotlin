package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.GridLayoutWithRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rrbofficial.androiduipracticekotlin.R

class AdapterRecycler(private val context: Context, private val arrayList: ArrayList<GridPojo>) :
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_recycler_grid, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = arrayList[position].name
        holder.image.setImageResource(arrayList[position].image)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${arrayList[position].name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.image)
    }
}
