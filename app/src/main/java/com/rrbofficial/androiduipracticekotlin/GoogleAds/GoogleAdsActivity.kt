package com.rrbofficial.androiduipracticekotlin.GoogleAds

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.LoadAdError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.rrbofficial.androiduipracticekotlin.R

class GoogleAdsActivity : AppCompatActivity() {

    private lateinit var adView: AdView
    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_google_ads)

        // Initialize the Google Mobile Ads SDK on a background thread.
        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(this@GoogleAdsActivity) {}
        }

        // Setup Banner Ad
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Setup buttons
        val bannerAdButton: Button = findViewById(R.id.btn_show_banner_ad)
        val interstitialAdButton: Button = findViewById(R.id.btn_show_interstitial_ad)
        val rewardedVideoAdButton: Button = findViewById(R.id.btn_show_rewarded_video_ad)

        // Load and show Interstitial Ad
        loadInterstitialAd()
        interstitialAdButton.setOnClickListener {
            if (interstitialAd != null) {
                interstitialAd?.show(this)
            } else {
                loadInterstitialAd() // Reload if not ready
            }
        }

        // Load and show Rewarded Video Ad
        loadRewardedAd()
        rewardedVideoAdButton.setOnClickListener {
            if (rewardedAd != null) {
                rewardedAd?.show(this) { rewardItem ->
                    // Handle user reward
                    val rewardAmount = rewardItem.amount
                    val rewardType = rewardItem.type
                    // TODO: Handle the reward (e.g., give virtual currency or unlock a feature)
                }
            } else {
                loadRewardedAd() // Reload if not ready
            }
        }
    }

    // Load Interstitial Ad
    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }
            })
    }

    // Load Rewarded Video Ad
    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this, "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx", adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    rewardedAd = null
                }
            })
    }

    // Optional: Clean up resources on destroy
    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
    }
}
