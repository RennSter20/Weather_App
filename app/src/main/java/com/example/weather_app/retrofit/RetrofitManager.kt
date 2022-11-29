package com.example.weather_app.retrofit

import android.R
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.weather_app.database.CityDao
import com.example.weather_app.database.CityModel
import com.example.weather_app.info.City
import com.example.weather_app.recyclerview.CityAdapter
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class RetrofitManager {

    val retrofit: Retrofit = Retrofit.Builder() //Retrofit.Builder class uses the Builder API to allow defining the URL end point for the HTTP operations and finally build a new Retrofit instance.
        //http://api.openweathermap.org/data/2.5/weather?q=London&APPID=76a35a17f3e1bce771a09f3555b614a8
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun setIcon(cityToReturn:City, context: Context){
        var iconString:String = cityToReturn!!.cityIcon.toString()
        //var image: ImageView = (context as Activity).findViewById<View>(com.example.weather_app.R.id.imageView) as ImageView
        //val image = context.findViewById<View>(R.id.imageView) as ImageView
        val res: Resources = context.resources
        val mDrawableName = "_" + iconString.substring(1)
        val resID: Int = res.getIdentifier(mDrawableName, "drawable", context.packageName)
        val drawable: Drawable = res.getDrawable(resID)
        //image.setImageDrawable(drawable)
    }

    fun getCity(cityName:String,cityText:TextView, tempText:TextView, descriptionText:TextView, userDao: CityDao, adapter: CityAdapter): City? {

        var apiInterface:ApiInterface = retrofit.create(ApiInterface::class.java)
        var call: Call<Object> = apiInterface.getWeatherData(cityName)

        var cityToReturn: City? = null

        call?.enqueue(object : Callback<Object> {
            override fun onResponse(call: Call<Object>?, response: Response<Object>) {
                Log.i("TAG", Gson().toJson(response.body()))

                val jsonObject = JSONObject(Gson().toJson(response.body()))
                cityToReturn = ObjectMaker().makeObject(jsonObject)

                Log.i("CITY NAME", cityToReturn!!.cityName.toString())
                cityText.text = cityToReturn!!.cityName
                tempText.text = cityToReturn!!.temperature
                descriptionText.text = cityToReturn!!.cityMainDescription

                //DATABASE
                var id: Int = Random(System.currentTimeMillis()).nextInt()
                var cityModel = CityModel(id, cityToReturn?.cityName, cityToReturn?.temperature)
                userDao.insertAll(cityModel)
                adapter.cities = userDao.getAll() as ArrayList<CityModel>
                adapter.notifyDataSetChanged()

                //setIcon(cityToReturn!!, context)
            }

            override fun onFailure(call: Call<Object>?, t: Throwable?) {
                //Toast.makeText(context, "No data recieved!", Toast.LENGTH_SHORT).show()
            }})
        return cityToReturn

    }

    fun getCityWithCo(lat: String, lon: String, cityText:TextView, tempText:TextView, descriptionText:TextView, adapter: CityAdapter): City? {
        var apiInterface:ApiInterface = retrofit.create(ApiInterface::class.java)
        var call: Call<Object> = apiInterface.getWeatherDataWithCo(lat, lon)

        var cityToReturn: City? = null

        call?.enqueue(object : Callback<Object> {
            override fun onResponse(call: Call<Object>?, response: Response<Object>) {
                Log.i("TAG", Gson().toJson(response.body()))

                val jsonObject: JSONObject = JSONObject(Gson().toJson(response.body()))
                cityToReturn = ObjectMaker().makeObject(jsonObject)

                Log.i("CITY NAME", cityToReturn!!.cityName.toString())
                cityText.text = cityToReturn!!.cityName
                tempText.text = cityToReturn!!.temperature
                descriptionText.text = cityToReturn!!.cityMainDescription

                //setIcon(cityToReturn!!, context)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Object>?, t: Throwable?) {
                //Toast.makeText(context, "No data recieved!", Toast.LENGTH_SHORT).show()
            }})
        return cityToReturn
    }

}