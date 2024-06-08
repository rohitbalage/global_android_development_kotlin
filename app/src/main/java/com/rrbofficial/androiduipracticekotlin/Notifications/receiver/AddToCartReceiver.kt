package com.rrbofficial.androiduipracticekotlin.Notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AddToCartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
       Toast.makeText(context, "Item added to cart Successfully", Toast.LENGTH_LONG).show()
    }
}