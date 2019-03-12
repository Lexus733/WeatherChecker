package com.example.dmitry.weatherchecker.presentation.configfragment.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.CityIdModel
import kotlinx.android.synthetic.main.config_item.view.*

class ConfigRVAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter<ConfigRVAdapter.ViewHolder>() {
    private var cityIdModel = ArrayList<CityIdModel>()

    interface OnItemClickListener {
        fun onItemClick(item: CityIdModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.config_item, parent, false))
    }

    override fun getItemCount(): Int = cityIdModel.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val data: CityIdModel = cityIdModel[p1]
        p0.country.text = data.country.trim()
        p0.name.text = data.name.trim()
        p0.bind(data, listener)
    }

    fun setData(cityIdModel: ArrayList<CityIdModel>) {
        this.cityIdModel = cityIdModel
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.config_item_city_name
        val country: TextView = view.config_item_city_country

        fun bind(cityIdModel: CityIdModel, listener: OnItemClickListener) {
            name.setOnClickListener { listener.onItemClick(cityIdModel) }
        }
    }
}