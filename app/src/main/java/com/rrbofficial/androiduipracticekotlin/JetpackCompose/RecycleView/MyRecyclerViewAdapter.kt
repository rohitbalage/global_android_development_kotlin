package com.rrbofficial.androiduipracticekotlin.JetpackCompose.RecycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rrbofficial.androiduipracticekotlin.R

class MyRecyclerViewAdapter (val fruitsList: List<String>): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_items_recylerview,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList[position]
//        holder.myTextView.text = "Hello World $position" //set the holder with position
        holder.myTextView.text = fruit   // set the holder to the fruit name

    }

    override fun getItemCount(): Int {
//      return 9  // No of rows
        return fruitsList.size
    }


}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val myTextView = view.findViewById<TextView>(R.id.tvName)


}