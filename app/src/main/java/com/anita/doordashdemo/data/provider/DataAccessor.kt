package com.anita.doordashdemo.data.provider

import com.anita.doordashdemo.data.model.Restaurant
import io.reactivex.Observer

interface DataAccessor {

    fun getRestaurantList(callback: Observer<List<Restaurant>>)

    fun fetchItemsPage(offset: Int, numItems: Int, callback: Observer<List<Restaurant>>)

}