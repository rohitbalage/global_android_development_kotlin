package com.rrbofficial.androiduipracticekotlin.ExternalUILibraries

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ecandrea.library.tooltipopwordtv.listeners.SelectableWordListeners
import com.ecandrea.library.tooltipopwordtv.tooltipopupWindows.ToolPopupWindows
import com.ecandrea.library.tooltipopwordtv.wordTextView.SelectableWordTextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.rrbofficial.androiduipracticekotlin.ExternalUILibraries.MPCharts.MPChartsActivity
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityExternUiLibrariesBinding
import com.shashank.sony.fancytoastlib.FancyToast
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import jp.wasabeef.blurry.Blurry
import org.json.JSONObject
import timber.log.Timber
import top.defaults.colorpicker.ColorPickerPopup
import top.defaults.colorpicker.ColorPickerPopup.Builder
import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class ExternalUILibrariesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternUiLibrariesBinding
    var clickCount = 0
    var clickCountTasty = 0
    var clickCountAesthetic = 0
    var clickCountAestheticDark = 0
    private lateinit var gfgTextView: TextView
    private lateinit var mSetColorButton: Button
    private lateinit var mPickColorButton: Button
    private lateinit var mColorPreview: View
    val toopop = "ToolPop"
    private var mDefaultColor = Color.BLACK // Use Color.BLACK instead of 0

    private lateinit var btnverifyCaptcha: Button
    private val SITE_KEY = "6LfeqV8qAAAAAHKG_vbJv4YPa25k7iJqmglKaYcP"
    private val SECRET_KEY = "6LfeqV8qAAAAAA6E1fGVQ07_rQG5JTlyk_aG4oZI"
    private lateinit var queue: RequestQueue


    // A map to store word-description pairs
    private val wordDescriptionMap = mapOf(
        "example" to "An instance of something.",
        "Select" to "Choose something from a set of options.",
        "word" to "A single distinct meaningful element of speech or writing."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_extern_ui_libraries)

        // Reference to the view you want to blur
        val imageView = findViewById<ImageView>(R.id.blurryimage)
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)

        // Set the default color to 0 (black)
        gfgTextView = findViewById(R.id.colortextView)
        mPickColorButton = findViewById(R.id.pick_color_button)
        mSetColorButton = findViewById(R.id.set_color_button)
        mColorPreview = findViewById(R.id.preview_selected_color)


        //     // Initialize Volley request queue
        queue = Volley.newRequestQueue(applicationContext)

        // Initialize button and set click listener
        btnverifyCaptcha = findViewById(R.id.CAPTCHAbutton)
        btnverifyCaptcha.setOnClickListener {
            verifyGoogleReCAPTCHA()
        }

        //for wordView
        val wordTextView = findViewById<SelectableWordTextView>(R.id.word)
        wordTextView.apply {
            text = "Select a word from this example."

            setToolTipListener(object : SelectableWordListeners {
                override fun onWordSelected(anchorView: TextView, wordSelected: String, lineNumber: Int, width: Int) {
                    // Get the description for the selected word from the map
                    val description = wordDescriptionMap[wordSelected] ?: "No description available."

                    // Define the tooltip appearance and behavior
                    val toolPopupWindows = ToolPopupWindows.ToolTipBuilder(this@ExternalUILibrariesActivity)
                        .setToolTipListener {
                            Timber.tag(toopop).d("Toolpop has started")
                        }
                        .setTitleTextColor(ContextCompat.getColor(this@ExternalUILibrariesActivity, R.color.white))
                        .setTitleTextSize(20f)
                        .setBackgroundColor(ContextCompat.getColor(this@ExternalUILibrariesActivity, R.color.violetlight))
                        .setToolTipDescription(description)
                        .build()

                    // Show the tooltip with the description
                    showToolTipWindow(anchorView, wordSelected, lineNumber, width, toolPopupWindows)
                }
            })

            // Optionally set a background color for the highlighted word
            setBackgroundWordColor(ContextCompat.getColor(this@ExternalUILibrariesActivity, R.color.black))
        }


        // Apply blur effect to the ImageView
        // Blurring a single ImageView
        Blurry.with(this)
            .radius(15)
            .sampling(3)
            .async()
            .capture(imageView)  // Apply blur to a specific ImageView
            .into(imageView)  // Render it back to the same ImageView

        binding.goToMpChart.setOnClickListener{
            val intent = Intent(this, MPChartsActivity::class.java)
            startActivity(intent)
        }

        // Setting up click listener using data binding
        binding.tastyToastBtn.setOnClickListener {
            clickCountTasty++

            when (clickCountTasty) {
                1 -> {
                    // First click: Show INFO toast
                    FancyToast.makeText(
                        this,
                        "Info: Hello World!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.INFO,
                        true
                    ).show()
                }

                2 -> {
                    // Second click: Show SUCCESS toast
                    FancyToast.makeText(
                        this,
                        "Success: Operation completed!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                }

                3 -> {
                    // Third click: Show ERROR toast
                    FancyToast.makeText(
                        this,
                        "Error: Something went wrong!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        true
                    ).show()

                    // Reset click count after 3rd click to loop back to the first toast
                    clickCountTasty = 0
                }
            }
        }

            binding.showBottmSheet.setOnClickListener {
                val bottomSheetFragment = ExampleBottomSheetFragment()
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            binding.motionToast.setOnClickListener {
                clickCount++

                when (clickCount) {
                    1 -> {
                        // First click: Show success toast
                        MotionToast.createToast(
                            this,
                            "Success",
                            "MotionToast successfully displayed!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            null
                        )
                    }

                    2 -> {
                        // Second click: Show error toast
                        MotionToast.createToast(
                            this,
                            "Error",
                            "Oops! Something went wrong.",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            null
                        )
                    }

                    3 -> {
                        // Third click: Show warning toast
                        MotionToast.createToast(
                            this,
                            "Warning",
                            "Be careful! This is a warning.",
                            MotionToastStyle.WARNING,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            null
                        )

                    }

                    4 -> {
                        MotionToast.createToast(
                            this,
                            "Info",
                            "Be careful! This is a warning.",
                            MotionToastStyle.INFO,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            null
                        )
                        // Reset click count after 3rd click to loop back to the first toast
                        clickCount = 0

                    }
                }
            }






        /* About aesthetic dialog animations
        * setCancelable()
setDarkMode()
setDuration()
setGravity()
setAnimation()
Constants

DIALOG STYLE	DIALOG TYPE	DIALOG ANIMATION
RAINBOW
FLAT
CONNECTIFY
TOASTER
DRAKE
EMOJI
EMOTION
SUCCESS
ERROR
WARNING
INFO	DEFAULT
SLIDE_UP, SLIDE_DOWN
SLIDE_LEFT, SLIDE_RIGHT
SWIPE_LEFT, SWIPE_RIGHT
IN_OUT
CARD
SHRINK
SPLIT
DIAGONAL
SPIN
WINDMILL
FADE
ZOOM
        *
        *
        *
        *
        *
        *
        * */

            binding.aestheticDialogBtn.setOnClickListener {
                clickCountAesthetic++

                when (clickCountAesthetic) {
                    1 -> {
                        // Connectify Dark Dialog (Success)
                        AestheticDialog.Builder(this, DialogStyle.CONNECTIFY, DialogType.SUCCESS)
                            .setTitle("Success")
                            .setMessage("Operation completed successfully!")
                            .setCancelable(true)
                            .setAnimation(DialogAnimation.SPLIT)
                            .show()
                    }

                    2 -> {
                        // Toaster Dark Dialog (Info)
                        AestheticDialog.Builder(this, DialogStyle.TOASTER, DialogType.INFO)
                            .setTitle("Information")
                            .setMessage("This is an informational message.")
                            .setCancelable(true)
                            .setAnimation(DialogAnimation.SLIDE_RIGHT)
                            .show()
                    }

                    3 -> {
                        // Emoji Dark Dialog (Warning)
                        AestheticDialog.Builder(this, DialogStyle.EMOJI, DialogType.WARNING)
                            .setTitle("Warning")
                            .setMessage("Be cautious!")
                            .setCancelable(true)
                            .setAnimation(DialogAnimation.FADE)
                            .show()
                    }

                    4 -> {
                        // Flat Dark Dialog (Error)
                        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.ERROR)
                            .setTitle("Error")
                            .setMessage("Something went wrong!")
                            .setCancelable(true)
                            .setAnimation(DialogAnimation.SHRINK)
                            .show()
                    }

                    5 -> {
                        // Flat Dark Dialog (Custom Example with Success type)
                        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.SUCCESS)
                            .setTitle("Custom Dialog")
                            .setMessage("This is a custom dialog example.")
                            .setCancelable(true)
                            .setAnimation(DialogAnimation.WINDMILL)
                            .show()

                        // Reset the click count after all options are shown
                        clickCountAesthetic = 0
                    }
                }
            }

        binding.aestheticDialogDarkBtn.setOnClickListener {
            clickCountAestheticDark++  // Increment the dark counter on each button click

            when (clickCountAestheticDark) {
                1 -> {
                    // Connectify Dark Dialog (Success)
                    AestheticDialog.Builder(this, DialogStyle.CONNECTIFY, DialogType.SUCCESS)
                        .setTitle("Success")
                        .setMessage("Operation completed successfully!")
                        .setCancelable(true)
                        .setAnimation(DialogAnimation.SPLIT)
                        .setDarkMode(true)
                        .show()
                }
                2 -> {
                    // Toaster Dark Dialog (Info)
                    AestheticDialog.Builder(this, DialogStyle.TOASTER, DialogType.INFO)
                        .setTitle("Information")
                        .setMessage("This is an informational message.")
                        .setCancelable(true)
                        .setAnimation(DialogAnimation.SLIDE_LEFT)
                        .setDarkMode(true)
                        .show()
                }
                3 -> {
                    // Emoji Dark Dialog (Warning)
                    AestheticDialog.Builder(this, DialogStyle.EMOJI, DialogType.WARNING)
                        .setTitle("Warning")
                        .setMessage("Be cautious!")
                        .setCancelable(true)
                        .setAnimation(DialogAnimation.FADE)
                        .setDarkMode(true)
                        .show()
                }
                4 -> {
                    // Flat Dark Dialog (Error)
                    AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.ERROR)
                        .setTitle("Error")
                        .setMessage("Something went wrong!")
                        .setCancelable(true)
                        .setAnimation(DialogAnimation.SHRINK)
                        .setDarkMode(true)
                        .show()
                }
                5 -> {
                    // Flat Dark Dialog (Custom Example with Success type)
                    AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.SUCCESS)
                        .setTitle("Custom Dialog")
                        .setMessage("This is a custom dialog example.")
                        .setCancelable(true)
                        .setAnimation(DialogAnimation.WINDMILL)
                        .setDarkMode(true)
                        .show()

                    // Reset the click count after all options are shown
                    clickCountAestheticDark = 0  // Reset counter to loop through the dialog types again
                }
            }
        }

        mPickColorButton.setOnClickListener { v ->
            ColorPickerPopup.Builder(this)
                .initialColor(Color.RED)
                .enableBrightness(true)
                .enableAlpha(true)
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(v, object : ColorPickerObserver() {
                    override fun onColorPicked(color: Int) {
                        mDefaultColor = color
                        mColorPreview.setBackgroundColor(mDefaultColor)
                    }
                })
        }

        mSetColorButton.setOnClickListener {
            gfgTextView.setTextColor(mDefaultColor)
        }
    }
    private fun verifyGoogleReCAPTCHA() {
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
            .addOnSuccessListener(this, OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse> { response ->
                val tokenResult = response.tokenResult
                if (!tokenResult.isNullOrEmpty()) {
                    handleVerification(tokenResult)
                }
            })
            .addOnFailureListener(this, OnFailureListener { e ->
                if (e is ApiException) {
                    Log.d("TAG", "Error message: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
                } else {
                    Toast.makeText(this@ExternalUILibrariesActivity, "Error found: $e", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun handleVerification(responseToken: String) {
        val url = "https://www.google.com/recaptcha/api/siteverify"

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean("success")) {
                        Toast.makeText(this@ExternalUILibrariesActivity, "User verified with reCAPTCHA", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, jsonObject.getString("error-codes"), Toast.LENGTH_LONG).show()
                    }
                } catch (ex: Exception) {
                    Log.d("TAG", "JSON exception: ${ex.message}")
                }
            },
            Response.ErrorListener { error ->
                Log.d("TAG", "Error message: ${error.message}")
            }) {

            override fun getParams(): Map<String, String> {
                return hashMapOf(
                    "secret" to SECRET_KEY,
                    "response" to responseToken
                )
            }
        }

        request.retryPolicy = DefaultRetryPolicy(
            50000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
    }
}





