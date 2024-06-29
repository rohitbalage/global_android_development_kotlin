package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.AndroidSysComponents.AndroidUIWidgets
import com.rrbofficial.androiduipracticekotlin.ExplicitIntent
import com.rrbofficial.androiduipracticekotlin.ImplicitIntent
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.BottomNavigationTabs.BottomNavigationTabs
import com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.ExpandableListView.ExpandableListViewScreen
import com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.TabLayout.TabLayoutWithViewPager
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMaterialUiScreenComponentsBinding
import kotlin.random.Random

class MaterialUIComponents : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialUiScreenComponentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_material_ui_screen_components)

        // Set click listeners using data binding
        binding.implicitBtn.setOnClickListener {
            handleImplicitIntent()
        }

        binding.explicitBtn.setOnClickListener {
            handleExplicitIntent()
        }

        binding.googleImplicit.setOnClickListener {
            handleGoogleImplicitIntent()
        }

        binding.randomNumberBtn.setOnClickListener {
            handleGenerateRandomNumber()
        }

        binding.shareBtn.setOnClickListener {
            handleShare()
        }

        binding.expandableListViewBtn.setOnClickListener {
            goToExpandableListView()
        }

        binding.androidUIWidgets.setOnClickListener {
            goToAndroidUIWidgets()
        }
        binding.expandableListViewBtn.setOnClickListener()
        {
            goToExpandableListView()
        }
        binding.TabLayoutViewBtn.setOnClickListener()
        {
            goToTabLayout()
        }

        binding.BottomTabLayoutViewBtn.setOnClickListener()
        {
            goToBottomTabLayout()
        }
    }

    private fun goToBottomTabLayout() {
        val intent = Intent(this,BottomNavigationTabs::class.java)
        startActivity(intent)
    }

    private fun goToTabLayout() {
        val intent = Intent(this,TabLayoutWithViewPager::class.java)
        startActivity(intent)
    }

    private fun handleImplicitIntent() {
        val str = binding.implicitIntentText.text.toString()
        val intent = Intent(applicationContext, ImplicitIntent::class.java)
        intent.putExtra("value", str)
        startActivity(intent)
    }

    private fun handleExplicitIntent() {
        val str = binding.implicitIntentText.text.toString()
        val intent = Intent(this, ExplicitIntent::class.java)
        intent.putExtra("value", str)
        startActivity(intent)
    }

    private fun handleGoogleImplicitIntent() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://google.com")
        startActivity(intent)
    }

    private fun handleGenerateRandomNumber() {
        val random = generateRandomNum()
        binding.randomNumber.text = random.toString()
    }

    private fun handleShare() {
        val str = binding.implicitIntentText.text.toString()
        val random = generateRandomNum()
        shareData(str, random)
    }

    private fun generateRandomNum(): Int {
        return Random.nextInt(1000)
    }

    private fun shareData(name: String, randomnum: Int) {
        val i = Intent(Intent.ACTION_SEND)
        i.putExtra(Intent.EXTRA_SUBJECT, "$name is luck today!")
        i.putExtra(Intent.EXTRA_TEXT, "His random no is $randomnum")
        startActivity(i)
    }

    private fun goToExpandableListView() {
        val intent = Intent(this, ExpandableListViewScreen::class.java)
        startActivity(intent)
    }

    private fun goToAndroidUIWidgets() {
        val intent = Intent(this, AndroidUIWidgets::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
