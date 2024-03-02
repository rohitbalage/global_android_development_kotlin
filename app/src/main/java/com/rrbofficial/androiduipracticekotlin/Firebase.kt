package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore
import org.w3c.dom.Document

class Firebase : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        val textView: TextView = findViewById(R.id.fireTextView)


        val firestoretextView: TextView = findViewById(R.id.firestoretext)
        // Reference to our database
        database = Firebase.database.reference

        // Writing custom objects to firebase
        val user = User("Rohit", "123456")
        database.child("Users").setValue(user)

        // Reading custom objects from Firebase

        val pe = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val u1  = snapshot.getValue<User>()

                textView.text = u1.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        database.child("Users").addValueEventListener(pe)



        /********************FIREBASE FIRESTORE****************************/

        // Inititalize firiestore
        val db = Firebase.firestore

        // Creating a collection of "Users"

        val users_collection =  db.collection("Users")


        // Creating documents

        val user1 = hashMapOf(
            "name" to "jack",
            "lname" to "reacher",
            "born"  to "1992"
        )

        val user2 = hashMapOf(
            "name" to "rohit",
            "lname" to "balage",
            "born"  to "1992"
        )



        // Adding Documents to Collection

        users_collection.document("user1").set(user1)
        users_collection.document("user2").set(user2)


        // Receive documents from firestore
//        val docReference = db.collection("Users").document("user1")
//
//       // Getting specific data from Document
//        docReference.get().addOnSuccessListener { document ->
//            if(document !=null)
//            {
//            firestoretextView.text = "${document.data?.get("name")}"
//
//            }
//
//        }


        // Getting all documents from collection:

      var allDocuments :  String = ""

        db.collection("Users").get().addOnSuccessListener {
            result ->

            for(document in result)
            {
                Log.i("TAGY", "${document.data}")
                allDocuments += "${document.data} \n"
            }
            firestoretextView.text = ""+allDocuments
        }




        // Update and delete documents

        db.collection("Users").
        document("user1")
            .update("born", "1965")


        // for deleting the document:
//        db.collection("Users").
//        document("user1")
//            .delete()

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