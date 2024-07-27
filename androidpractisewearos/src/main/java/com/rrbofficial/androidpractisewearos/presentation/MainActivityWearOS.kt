package com.rrbofficial.androidpractisewearos.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.*
import com.rrbofficial.androidpractisewearos.R
import com.rrbofficial.androidpractisewearos.presentation.theme.AndroidUiPracticeKotlinTheme

class MainActivityWearOS : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    AndroidUiPracticeKotlinTheme {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TimeText(modifier = Modifier.padding(bottom = 16.dp))
            Greeting(greetingName = "Android")
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton("Button 1") {
                Toast.makeText(context, "Button 1 clicked", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton("Button 2") {
                Toast.makeText(context, "Button 2 clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName),
        fontSize = 16.sp
    )
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    val buttonColor = Color(0xFFEE82EE) // Violet color defined directly
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(48.dp)
    ) {
        Text(text = text, fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
