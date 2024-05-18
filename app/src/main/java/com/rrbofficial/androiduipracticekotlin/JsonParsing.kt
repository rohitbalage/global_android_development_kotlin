package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityJsonParsingBinding
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class JsonParsing : AppCompatActivity() {

   private  lateinit var binding : ActivityJsonParsingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJsonParsingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchCurrencyData().start()

    }
    private  fun fetchCurrencyData(): Thread
    {
        return Thread{
            var url = URL("https://open.er-api.com/v6/latest/aud")
            val connection = url.openConnection() as HttpURLConnection

            if(connection.responseCode ==200)
            {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Request::class.java)
                updateUI(request)
                inputStreamReader.close()
                inputSystem.close()
                println(inputSystem.toString())
            }
            else
            {
                binding.baseCurrency.text = "Failed Connected"
            }

        }
    }

    private fun updateUI(request: Request) {
        runOnUiThread{
            kotlin.run {
                binding.lastupdated.text = request.time_last_update_utc
                binding.nzd.text = String.format("NZD: %2f" , request.rates.NZD)
                binding.usd.text = String.format("USD: %.2f", request.rates.USD)
                binding.gbp.text = String.format("GBP: %.2f", request.rates.GBP)
            }

        }
    }


}