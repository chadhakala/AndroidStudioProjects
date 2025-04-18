package com.ebookfrenzy.cryptocurrency

import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.Volley
import com.ebookfrenzy.cryptocurrency.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

    var binding = ActivityMainBinding.inflate(layoutInflater)




    val queue = Volley.newRequestQueue(this)
    val url =
        "rest.coincap.io/v3/assets?apiKey=dd5949bde5962879f0cce38d46b93483f30ffdcf405cf74c865a9ddc1d66705a"

    val stringRequest = StringRequest(
    new JsonObjectRequest(
        Request.Method.GET,   //the request method
        "https://jsonplaceholder.typicode.com/todos/1",
        null,
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        },
      new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {

          }

}
    queue.add(stringRequest)
}
})


