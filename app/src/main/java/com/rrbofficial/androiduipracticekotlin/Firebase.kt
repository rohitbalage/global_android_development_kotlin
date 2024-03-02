package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class Firebase : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        val textView: TextView = findViewById(R.id.fireTextView)





// SAMPLE CODE  TO TEST FIREBASE CONNECTIVITY
//        // Real time database reference
//    // this the reference to our database https://androiduipracticekotlin-default-rtdb.firebaseio.com/
//        database = Firebase.database.reference
//
//        // Write data to Firebase
//        database.child("price").setValue("1200 $")
//
//        // Reading data from firebase
//
//        val postListener = object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//            val gold_price = snapshot.value
//
//                textView.text= gold_price.toString()
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }
//        database.child("price").addValueEventListener(postListener)
//    }
    }
}