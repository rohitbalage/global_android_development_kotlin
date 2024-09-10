package com.rrbofficial.androiduipracticekotlin.GoogleAds

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.AdListener
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

        // Load Native Ad
        loadNativeAd()
    }

    // Load Interstitial Ad
    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-2112678444983361/3369972866", adRequest,
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
        RewardedAd.load(this, "ca-app-pub-2112678444983361/6481515071", adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    rewardedAd = null
                }
            })
    }

    // Load Native Advanced Ad
    private fun loadNativeAd() {
        val adLoader = com.google.android.gms.ads.AdLoader.Builder(this, "ca-app-pub-2112678444983361/7991744151")
            .forNativeAd { nativeAd ->
                populateNativeAdView(nativeAd)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the error
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    // Populate Native Ad View
    private fun populateNativeAdView(nativeAd: NativeAd) {
        val nativeAdView: NativeAdView = findViewById(R.id.nativeAdView)

        // Set the headline (Title of the ad)
        nativeAdView.headlineView = nativeAdView.findViewById(R.id.native_ad_headline)
        (nativeAdView.headlineView as TextView).text = nativeAd.headline

        // Set the media view (For video content)
        nativeAdView.mediaView = nativeAdView.findViewById(R.id.mediaView)
        nativeAdView.mediaView?.setMediaContent(nativeAd.mediaContent)

        // If there is an icon
        if (nativeAd.icon != null) {
            nativeAdView.iconView = nativeAdView.findViewById(R.id.native_ad_icon)
            (nativeAdView.iconView as ImageView).setImageDrawable(nativeAd.icon?.drawable)
        }

        // Assign NativeAd to NativeAdView
        nativeAdView.setNativeAd(nativeAd)
    }

    // Optional: Clean up resources on destroy
    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
    }
}
