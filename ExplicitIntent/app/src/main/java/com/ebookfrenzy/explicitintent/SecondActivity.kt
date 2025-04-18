package com.ebookfrenzy.explicitintent

import android.os.Bundle
import
android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.View
import android.content.Intent
import com.ebookfrenzy.explicitintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_second2)

        binding = ActivitySecondBinding.inflate(layoutInflater)

    setContentView(binding.root)

    val extras = intent.extras ?: return

    val qString = extras.getString("qStriing")

        binding.textView2.text = qString


    } }
