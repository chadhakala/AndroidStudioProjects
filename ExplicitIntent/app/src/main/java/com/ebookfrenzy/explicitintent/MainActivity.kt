package com.ebookfrenzy.explicitintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.button1)) { v, insets ->


        }
    }


        fun sendText(view: View) {
            val i = Intent(this, SecondActivity::class.java)

            val myString = binding.editText.text.toString()
            i.putExtra("qString", myString)
            startActivity(i)

        }
    }
//sendText() method follows the techniques outlined in the previous section.