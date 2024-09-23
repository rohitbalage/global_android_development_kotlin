package com.rrbofficial.androiduipracticekotlin.Firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore
import com.rrbofficial.androiduipracticekotlin.Firebase.Admob.AdMobWithFirebaseActivity
import com.rrbofficial.androiduipracticekotlin.Firebase.Analytics.FirebaseAnalyticsActivity
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.FirebaseAuthenticationOptions
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithEmailAndPassword.Firebase_signup
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithPhoneNumber.SignInwithPhoneNumberFirebase
import com.rrbofficial.androiduipracticekotlin.Firebase.CloudMessaging.CloudMessagingFirebaseActivity
import com.rrbofficial.androiduipracticekotlin.Firebase.CrashAnalytics.FirebaseCrashAnalytics
import com.rrbofficial.androiduipracticekotlin.Firebase.DynamicLink.DynamicLinkWithFirebaseActivity
import com.rrbofficial.androiduipracticekotlin.Firebase.InAppMessagin.InAppMessagingFirebaseActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.User

class Firebase : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private  lateinit var  goToAuth : Button
    private  lateinit var  goToCloudMessaging : Button
    private  lateinit var  goToCrashAnalytics : Button
    private  lateinit var  goToInappMessaging : Button
    private  lateinit var  goToAnalyticsApp : Button
    private  lateinit var  goToAdmobWithFirebase : Button
    private  lateinit var  goToDynamicLink : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        val textView: TextView = findViewById(R.id.fireTextView)

        // Go to Auth
       goToAuth = findViewById(R.id.goToFirebaseAuth)
        goToCrashAnalytics = findViewById(R.id.buttonCrashanalytics)
        goToAdmobWithFirebase = findViewById(R.id.buttonAdmobwithFirebase)
        goToCloudMessaging = findViewById(R.id.buttonFirebaseCloudMessaging)
        goToInappMessaging = findViewById(R.id.buttonInappMessaging)
        goToAnalyticsApp = findViewById(R.id.buttonFirebaseAnalticsApp)
        goToDynamicLink = findViewById(R.id.buttonDynamicLink)



       /*** Go to AUTH SECTION**/

       goToAuth.setOnClickListener()
       {
           // Create an intent to start the new activity
           val intent = Intent(this, FirebaseAuthenticationOptions::class.java)
           // Start the new activity
           startActivity(intent)
       }

        goToCrashAnalytics.setOnClickListener()
        {
            // Create an intent to start the new activity
            val intent = Intent(this, FirebaseCrashAnalytics::class.java)
            // Start the new activity
            startActivity(intent)
        }

        goToAdmobWithFirebase.setOnClickListener()
        {
            // Create an intent to start the new activity
            val intent = Intent(this, AdMobWithFirebaseActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }

        goToCloudMessaging.setOnClickListener(){
            // Create an intent to start the new activity
            val intent = Intent(this, CloudMessagingFirebaseActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }


        goToDynamicLink.setOnClickListener(){
            // Create an intent to start the new activity
            val intent = Intent(this, DynamicLinkWithFirebaseActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }

        goToInappMessaging.setOnClickListener(){
            val intent = Intent(this, InAppMessagingFirebaseActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }

        goToAnalyticsApp.setOnClickListener(){
            val intent = Intent(this, FirebaseAnalyticsActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }




       /****/
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

//      var allDocuments :  String = ""
//
//        db.collection("Users").get().addOnSuccessListener {
//            result ->
//
//            for(document in result)
//            {
//                Log.i("TAGY", "${document.data}")
//                allDocuments += "${document.data} \n"
//            }
//            firestoretextView.text = ""+allDocuments
//        }




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