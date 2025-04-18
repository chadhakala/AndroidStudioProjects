package com.ebookfrenzy.cryptocurrency

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class fragment1 : Fragment() {


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
val view = inflater.inflate(R.layout.fragment_fragment1, container, false)

            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonSend = view.findViewById<Button>(R.id.buttonSend) //Button in fragment1
        buttonSend.setOnClickListener {
            //Create an explicit intent with data to send to fragment2
            val intent = Intent(activity, Fragment2::class.java)
            intent.putExtra("data", editText.text.toString())
            startActivity(intent)
        }
    }
}
