package com.example.weather_app

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var textView:TextView = findViewById(R.id.text)

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=43.73&lon=15.89&appid=a0c9131636ddbd513b2be78ca26b3a24"

        var responseJSON:JSONObject? = null

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> Log.i("TAG", response.toString()) }
        ) { error -> Log.i("TAG", error.toString()) }



        queue.add(jsonObjectRequest)

    }



}