package com.example.ry_itto.qiitaclient.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ry_itto.qiitaclient.R

class HomeViewAdapter(private val context: Context, var itemList: List<String>): RecyclerView.Adapter<HomeViewHolder>() {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.let {
            it.titleView.text = itemList.get(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.home_row2, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}