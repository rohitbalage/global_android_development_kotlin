package com.rrbofficial.androiduipracticekotlin.ExternalUILibraries

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityExternUiLibrariesBinding
import com.shashank.sony.fancytoastlib.FancyToast
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class ExternalUILibrariesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternUiLibrariesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_extern_ui_libraries)

        // Setting up click listener using data binding
        binding.tastyToastBtn.setOnClickListener {
            // Show a toast message when the button is clicked
            // fancy toast
        FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
        }


        binding.showBottmSheet.setOnClickListener{
            val bottomSheetFragment = ExampleBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        binding.motionToast.setOnClickListener{
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
    }

    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
