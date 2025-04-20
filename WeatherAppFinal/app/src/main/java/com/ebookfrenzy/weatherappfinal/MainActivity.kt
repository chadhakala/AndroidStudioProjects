package com.ebookfrenzy.weatherappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ebookfrenzy.weatherappfinal.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Locale


class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
setupUI()
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
    private fun fetchWeatherData(city: String) {
        // (OpenWeatherMap)
        val apiKey = "key=bbbbc4610657434896784210251904"
        val url = "http://api.weatherapi.com/v1/weather?q=$city&appid=$apiKey"

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                val jsonObj = JSONObject(response)
                val main = jsonObj.getJSONObject("main")
                    val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val temp = main.getString("temp")
                val description = weather.getString("description")

                    updateWeatherUI("${temp}°C",
                        description.replaceFirstChar {
                            if  (it.isLowerCase())
                                 it.titlecase(Locale.getDefault())
                            else it.toString() })
                } catch (e: Exception) {
                    // Show error to user
                    binding.weatherDescription.text = "Error: ${e.message}"
                }
            },
            { error ->
                // Show error to user
                binding.weatherDescription.text = "Error: ${error.message}"
            }
        )

        // Add request to queue
        Volley.newRequestQueue(this).add(request)
    }

    private fun updateWeatherUI(temperature: String, description: String) {
        binding.temperatureText.text = temperature
        binding.weatherDescription.text = description
    }
}
