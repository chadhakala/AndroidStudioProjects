package com.ebookfrenzy.weatherappfinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ebookfrenzy.weatherappfinal.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)\

        binding = ActivityMainBinding.inflate(layoutInflater)


    }
}