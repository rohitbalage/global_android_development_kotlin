package com.rrbofficial.androiduipracticekotlin.AndroidAnimations.NativeBasicAnimations

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R

class NativeAndroidAnimationsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var blink: Button
    private lateinit var slideUp: Button
    private lateinit var slideDown: Button
    private lateinit var rotate: Button
    private lateinit var zoomIn: Button
    private lateinit var zoomOut: Button
    private lateinit var crossFade: Button
    private lateinit var fadeIn: Button
    private lateinit var fadeOut: Button
    private lateinit var move: Button
    private lateinit var bounce: Button
    private lateinit var sequential: Button
    private lateinit var stop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_android_animations)

        imageView = findViewById(R.id.imageview)
        blink = findViewById(R.id.blink)
        slideUp = findViewById(R.id.slide_up)
        slideDown = findViewById(R.id.slide_down)
        rotate = findViewById(R.id.rotate)
        zoomIn = findViewById(R.id.zoom_in)
        zoomOut = findViewById(R.id.zoom_out)
        crossFade = findViewById(R.id.cross_fade)
        fadeIn = findViewById(R.id.fade_in)
        fadeOut = findViewById(R.id.fade_out)
        move = findViewById(R.id.move)
        bounce = findViewById(R.id.bounce)
        sequential = findViewById(R.id.sequential)
        stop = findViewById(R.id.stop)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        blink.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink_animation)
            imageView.startAnimation(animation)
        }

        slideUp.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slideup_animation)
            imageView.startAnimation(animation)
        }

        slideDown.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slidedown_animation)
            imageView.startAnimation(animation)
        }

        rotate.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_animation)
            imageView.startAnimation(animation)
        }

        zoomIn.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoomin_animation)
            imageView.startAnimation(animation)
        }

        zoomOut.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoomout_animation)
            imageView.startAnimation(animation)
        }

        crossFade.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.crossfade_animation)
            imageView.startAnimation(animation)
        }

        fadeIn.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein_animation)
            imageView.startAnimation(animation)
        }

        fadeOut.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadeout_animation)
            imageView.startAnimation(animation)
        }

        move.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move_animation)
            imageView.startAnimation(animation)
        }

        bounce.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.bounce_animation)
            imageView.startAnimation(animation)
        }

        sequential.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.sequential_animation)
            imageView.startAnimation(animation)
        }

        stop.setOnClickListener {
            imageView.clearAnimation()
        }
    }
}
