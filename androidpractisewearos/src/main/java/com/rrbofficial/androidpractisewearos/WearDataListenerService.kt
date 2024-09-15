package com.rrbofficial.androidpractisewearos

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService

class WearDataListenerService : WearableListenerService() {

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        Log.d("WearDataListenerService", "onDataChanged triggered")

        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val dataItem = event.dataItem
                if (dataItem.uri.path == "/add_value") {
                    // Extract the data from the DataItem
                    val dataMap = DataMapItem.fromDataItem(dataItem).dataMap
                    val receivedAnswer = dataMap.getInt("answer_key")

                    Log.d("WearDataListenerService", "Received answer: $receivedAnswer")

                    // Broadcast or update MainActivity with the new answer
                    broadcastAnswer(receivedAnswer)
                }
            }
        }
    }

    // Use a Broadcast to send the received data to MainActivity
    private fun broadcastAnswer(answer: Int) {
        val intent = Intent("ANSWER_UPDATE")
        intent.putExtra("answer_key", answer)
        sendBroadcast(intent)
    }
}
