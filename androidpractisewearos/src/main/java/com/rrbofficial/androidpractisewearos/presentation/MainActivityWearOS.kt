package com.rrbofficial.androidpractisewearos.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivityWearOS : ComponentActivity() {

    private var answer: Int = 0

    // BroadcastReceiver to listen for updates from the WearDataListenerService
    private val answerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            answer = intent?.getIntExtra("answer_key", 0) ?: 0
            // Trigger recomposition to update the UI
            setContent { WearApp(answer) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WearApp(answer) }

        // Register BroadcastReceiver to listen for updates from the service
        registerReceiver(answerReceiver, IntentFilter("ANSWER_UPDATE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(answerReceiver)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WearApp(answer: Int) {
    val pagerState = rememberPagerState()

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)  // Keep the black background
        ) {
            // HorizontalPager to display pages
            HorizontalPager(
                state = pagerState,
                count = 3,  // Set the number of pages directly here
                modifier = Modifier
                    .weight(1f)  // Make pager take most of the screen
                    .fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> AnswerScreen(answer)  // First screen to show the answer
                    1 -> PageTwoScreen()
                    2 -> PageThreeScreen()
                }
            }

            // Pager indicator dots at the bottom
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.White,  // Active dot color
                inactiveColor = Color.Gray  // Inactive dot color
            )
        }
    }
}

@Composable
fun AnswerScreen(answer: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Answer: $answer",  // Display the received answer
            fontSize = 20.sp,
            color = Color.White,  // White text on black background
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PageTwoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Page Two",
            fontSize = 20.sp,
            color = Color.White,  // White text on black background
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PageThreeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Page Three",
            fontSize = 20.sp,
            color = Color.White,  // White text on black background
            textAlign = TextAlign.Center
        )
    }
}
