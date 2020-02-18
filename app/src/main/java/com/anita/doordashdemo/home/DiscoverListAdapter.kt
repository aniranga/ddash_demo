package com.anita.doordashdemo.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anita.doordashdemo.R
import com.anita.doordashdemo.data.model.Restaurant
import com.anita.doordashdemo.home.DiscoverPageListItem.ListItemViewType.LOAD_MORE_PROGRESS_ITEM
import com.anita.doordashdemo.home.DiscoverPageListItem.ListItemViewType.OBJ_LIST_ITEM
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class DiscoverListAdapter(val items : List<DiscoverPageListItem>, private val clickCallback: ClickCallback, private val context: Context) : RecyclerView.Adapter<DiscoverListAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val type = DiscoverPageListItem.ListItemViewType.fromInt(viewType)

        val viewHolder: BaseViewHolder

        when(type) {
            OBJ_LIST_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_item, parent, false)
                viewHolder = RestaurantItemViewHolder(view)
            }
            LOAD_MORE_PROGRESS_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.loadmore_progress_list_item, parent, false)
                viewHolder = LoadingItemViewHolder(view)
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        if (holder is RestaurantItemViewHolder) {
            holder.bindDataToView((items[position] as RestaurantListItem).obj, clickCallback)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    open inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class RestaurantItemViewHolder (itemView: View) : BaseViewHolder(itemView) {
        fun bindDataToView(restaurant: Restaurant, clickCallback: ClickCallback) {
            Glide.with(itemView).load(restaurant.coverImgUrl).placeholder(android.R.drawable.ic_menu_help).into(itemView.image)
            itemView.name.text = restaurant.name
            itemView.description.text = restaurant.description
            itemView.status.text = restaurant.status
            itemView.setOnClickListener{clickCallback.onRestaurantItemClicked(restaurant)}
        }
    }

    inner class LoadingItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
    }

    interface ClickCallback {
        fun onRestaurantItemClicked(restaurant: Restaurant)
    }

}