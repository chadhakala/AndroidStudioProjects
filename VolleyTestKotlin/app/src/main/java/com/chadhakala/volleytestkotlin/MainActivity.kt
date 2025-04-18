package com.chadhakala.volleytestkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.chadhakala.volleytestkotlin.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGetCatData.setOnClickListener {
            printCatData()
        }
    }

    //method to interact with API
    fun printCatData() {

        var catUrl =
            "https://api.thecatapi.com/v1/breeds" + "?api_key=live_zJPpqfZ78XVrHe3ATOmT3njzBWV4FdEl175Mwarzkjz2HMcKmwMkpmM2eox5OmPO"

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            Response.Listener<String> { response ->
                var catsArray: JSONArray = JSONArray(response)
//indices from 0 through catsArray.length()-1
                for (i in 0 until catsArray.length()) {
//${} is to interpolate the string /
// uses a string template
                    var theCat: JSONObject = catsArray.getJSONObject(i)
//now get the properties we want: name and description
                    Log.i("MainActivity", "Cat name:${theCat.getString("name")}")
                    Log.i("MainActivity",
                        "Cat description:${theCat.getString("description")}")
                }//end for
            },
            Response.ErrorListener {
                Log.i("MainActivity", "That didn't work!")
            })
// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCatData
}


