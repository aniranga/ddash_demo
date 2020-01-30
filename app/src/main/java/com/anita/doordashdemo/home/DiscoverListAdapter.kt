package com.anita.doordashdemo.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anita.doordashdemo.R
import com.anita.doordashdemo.data.model.Restaurant
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class DiscoverListAdapter(val items : List<Restaurant>, private val clickCallback: ClickCallback, private val context: Context) : RecyclerView.Adapter<DiscoverListAdapter.RestaurantItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_item, parent, false)
        return RestaurantItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        holder.bindDataToView(items[position], clickCallback)
    }

    inner class RestaurantItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataToView(restaurant: Restaurant, clickCallback: ClickCallback) {
            Glide.with(itemView).load(restaurant.coverImgUrl).placeholder(android.R.drawable.ic_menu_help).into(itemView.image)
            itemView.name.text = restaurant.name
            itemView.description.text = restaurant.description
            itemView.status.text = restaurant.status
            itemView.setOnClickListener{clickCallback.onRestaurantItemClicked(restaurant)}
        }
    }

    interface ClickCallback {
        fun onRestaurantItemClicked(restaurant: Restaurant)
    }

}