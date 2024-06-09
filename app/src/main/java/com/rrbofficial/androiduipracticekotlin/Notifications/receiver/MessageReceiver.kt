package com.rrbofficial.androiduipracticekotlin.Notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.RemoteInput
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppMessage
import com.rrbofficial.androiduipracticekotlin.Notifications.util.NotificationHelper

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        remoteInput?.let {
            val replyText = it.getCharSequence("KEY_TEXT_REPLY") // Use "KEY_TEXT_REPLY" here
            replyText?.let { text ->
                Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                NotificationHelper.messageList.add(
                    AppMessage(
                        text.toString(),
                        System.currentTimeMillis(),
                        null
                    )
                )
            }

            NotificationHelper.messagingStyleNotification(context)
        }
    }
}
