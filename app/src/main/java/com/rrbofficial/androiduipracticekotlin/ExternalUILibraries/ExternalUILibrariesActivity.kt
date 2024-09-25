package com.rrbofficial.androiduipracticekotlin.ExternalUILibraries

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
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
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class ExternalUILibrariesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternUiLibrariesBinding
    var clickCount = 0
    var clickCountTasty = 0
    var clickCountAesthetic = 0
    var clickCountAestheticDark = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_extern_ui_libraries)

        // Reference to the view you want to blur
        val imageView = findViewById<ImageView>(R.id.blurryimage)
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)

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

    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    }



