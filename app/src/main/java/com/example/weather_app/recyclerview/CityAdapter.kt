package com.example.weather_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.MainActivity
import com.example.weather_app.R
import com.example.weather_app.database.CityModel

class CityAdapter(var cities: ArrayList<CityModel>) : RecyclerView.Adapter<CityAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cityName = itemView.findViewById<TextView>(R.id.city_name)
        val cityTemperature = itemView.findViewById<TextView>(R.id.city_temperature)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var context: Context = parent.context
        var inflater:LayoutInflater = LayoutInflater.from(context)

        var contactView:View = inflater.inflate(R.layout.city_layout, parent, false)

        var viewHolder = ViewHolder(contactView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact = cities.get(position)

        var cityName:TextView = holder.cityName
        cityName.setText(contact.cityName)
        var cityTemp:TextView = holder.cityTemperature
        cityTemp.setText(contact.temperature)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun addCity(city:CityModel){
        cities.add(city)
    }

    fun updateAll(newCities: ArrayList<CityModel>){
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }

}