package com.ebookfrenzy.weatherappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ebookfrenzy.weatherappfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
setupUI()
    }
    private fun fetchWeatherData(city: String) {
        //Replace with API Key (OpenWeatherMap)
        val apiKey = "your_api_key_here"
    }
    private fun setupUI() {
        binding.searchButton.setOnClickListener {
            val city = binding.cityInput.text.toString()
            if (city.isNotEmpty()) {
                // For testing purposes
                updateWeatherUI("23°C", "Partly Cloudy")
            }
        }
    }

    private fun updateWeatherUI(temperature: String, description: String) {
        binding.temperatureText.text = temperature
        binding.weatherDescription.text = description
    }
}
