package com.rrbofficial.androiduipracticekotlin.JetpackCompose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.NavigationComponent.NavigationMainActivity
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.RecycleView.RecycleViewActivity
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleLiveData.SimpleViewModelWithLiveDataActivity
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel.SimpleViewModelActivity
import com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA.KotlinDSAAndFundamentals
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityJetpackComposeBinding

class JetpackCompose : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityJetpackComposeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jetpack_compose)

        binding.kotlinCoroutinesBtn.setOnClickListener(this)
        binding.simpleViewModelBtn.setOnClickListener(this)
        binding.LiveDataBtn.setOnClickListener(this)
        binding.viewModelLiveDataDataBindingBtn.setOnClickListener(this)
        binding.NavigationArchitectureComponentBtn.setOnClickListener(this)
        binding.recycleViewFuncdamentalsbtn.setOnClickListener(this)

    }

    override fun onBackPressed() {
            val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out)
        super.onBackPressed()
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.kotlinCoroutinesBtn -> {
                val intent = Intent(this, KotlinDSAAndFundamentals::class.java)
                startActivity(intent)
                finish()
            }
            R.id.simpleViewModelBtn -> {
                val intent = Intent(this, SimpleViewModelActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.LiveDataBtn -> {
                val intent = Intent(this, SimpleViewModelWithLiveDataActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.viewModelLiveDataDataBindingBtn -> {
//                val intent = Intent(this, ViewModelAndLiveDataWithDataBindingActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            R.id.NavigationArchitectureComponentBtn -> {
                val intent = Intent(this, NavigationMainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.recycleViewFuncdamentalsbtn -> {
                val intent = Intent(this, RecycleViewActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}