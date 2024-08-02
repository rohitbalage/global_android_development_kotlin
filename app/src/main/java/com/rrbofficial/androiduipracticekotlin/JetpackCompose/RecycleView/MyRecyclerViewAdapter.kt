package com.rrbofficial.androiduipracticekotlin.JetpackCompose.RecycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rrbofficial.androiduipracticekotlin.R

class MyRecyclerViewAdapter (private val fruitsList: List<Fruit>, private val clickListener: (Fruit) -> Unit): RecyclerView.Adapter<MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_items_recylerview,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList[position]
//        holder.myTextView.text = "Hello World $position" //set the holder with position
//        holder.myTextView.text = fruit.name   // set the holder to the fruit name
        holder.bind(fruit, clickListener )

    }

    override fun getItemCount(): Int {
//      return 9  // No of rows
        return fruitsList.size
    }


}

class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val myTextView: TextView = itemView.findViewById(R.id.tvName)

    fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit) {
        myTextView.text = fruit.name


        itemView.setOnClickListener {
          clickListener(fruit)
        }
    }
}
