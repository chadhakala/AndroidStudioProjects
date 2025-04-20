package com.ebookfrenzy.weatherappfinal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherFragment : Fragment() {




    // List to store city names and weather data
    private val cities = ArrayList<String>()
    private val weathers = ArrayList<Weather>()

    private var _binding: FragmentWeatherBinding? = null

    // Data class for weather information
    data class Weather(
        val cityName: String,
        val temperature: Double,
        val humidity: Int,
        val windSpeed: Double,
        val weatherCode: Int
    )

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        // City names and their coordinates
        val cityNames = listOf("London", "New York", "Tokyo", "Sydney")
        val latitudes = listOf(51.51, 40.71, 35.68, 33.87)
        val longitudes = listOf(-0.13, -74.01, 139.76, 151.21)

        // Create a request queue for API calls
        val queue = Volley.newRequestQueue(this.context)

        // Loop through each city
        for (i in cityNames.indices) {
            val cityName = cityNames[i]
            val lat = latitudes[i]
            val lon = longitudes[i]

            // Build the API URL for this city
            val weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current=temperature_2m,wind_speed_10m,relative_humidity_2m,weather_code&timezone=auto"

            // Create a string request
            val stringRequest = StringRequest(
                Request.Method.GET, weatherUrl,
                { response ->
                    // Parse JSON response
                    val obj = JSONObject(response)
                    val current = obj.getJSONObject("current")

                    // Get weather values
                    val temperature = current.getDouble("temperature_2m")
                    val humidity = current.getInt("relative_humidity_2m")
                    val windSpeed = current.getDouble("wind_speed_10m")
                    val weatherCode = current.getInt("weather_code")

                    // Create Weather object and add to list
                    val weather = Weather(
                        cityName,
                        temperature,
                        humidity,
                        windSpeed,
                        weatherCode
                    )

                    weathers.add(weather)
                    cities.add(cityName)

                    // If we have all cities, set up the spinner
                    if (cities.size == cityNames.size) {
                        setupCitySpinner()
                    }
                },
                {
                    // Error handling
                    Log.i("WeatherFragment", "API request failed: ${it.message}")
                })

            // Add request to queue
            queue.add(stringRequest)
        }
    }

    fun setupCitySpinner() {
        // Create an adapter for the spinner with our city list
        val adapter = ArrayAdapter(this.requireContext(),
            android.R.layout.simple_selectable_list_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set up the spinner with the adapter
        binding.citySpinner.adapter = adapter
        binding.citySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Display info for selected city
                displayWeatherInfo(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    fun displayWeatherInfo(position: Int) {
        // Get the weather for the selected city
        val weather = weathers[position]

        // Update UI elements with weather data
        binding.cityName.text = weather.cityName
        binding.temperature.text = "Temperature: ${weather.temperature}°C"
        binding.humidity.text = "Humidity: ${weather.humidity}%"
        binding.windSpeed.text = "Wind: ${weather.windSpeed} m/s"

        // Get a description based on weather code
        val weatherDescription = when (weather.weatherCode) {
            0 -> "Clear sky"
            1, 2, 3 -> "Partly cloudy"
            45, 48 -> "Fog"
            51, 53, 55 -> "Drizzle"
            61, 63, 65 -> "Rain"
            71, 73, 75 -> "Snow"
            95, 96, 99 -> "Thunderstorm"
            else -> "Unknown"
        }

        binding.weatherDescription.text = "Weather: $weatherDescription"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
