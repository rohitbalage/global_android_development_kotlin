package com.rrbofficial.androiduipracticekotlin.MachineLearningAndroid

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rrbofficial.androiduipracticekotlin.R
import java.util.Locale

class TextToSpeechSpeechToTextActivity : AppCompatActivity() {
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var btnText: Button
    private lateinit var editText: EditText
    private lateinit var ivMic: ImageView
    private lateinit var tvSpeechToText: TextView

    companion object {
        private const val REQUEST_CODE_SPEECH_INPUT = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech_speech_to_text)

        setContentView(R.layout.activity_text_to_speech_speech_to_text)

        // Initialize views
        editText = findViewById(R.id.Text)
        btnText = findViewById(R.id.btnText)
        ivMic = findViewById(R.id.iv_mic)
        tvSpeechToText = findViewById(R.id.tv_speech_to_text)

        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                // Set language for TTS
                textToSpeech.language = Locale.UK
            }
        }

        // Text-to-Speech button click event
        btnText.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                Toast.makeText(this, "Please enter text to speak", Toast.LENGTH_SHORT).show()
            }
        }

        // Speech-to-Text microphone click event
        ivMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now")
            }

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Speech recognition is not supported on your device",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            results?.let {
                if (it.isNotEmpty()) {
                    val spokenText = it[0]
                    tvSpeechToText.text = spokenText // Update the TextView with the recognized text
                }
            }
        }
    }

    override fun onDestroy() {
        // Shutdown TextToSpeech on activity destroy
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}