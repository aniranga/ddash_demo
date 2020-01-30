package com.anita.doordashdemo.home

import com.anita.doordashdemo.data.model.Restaurant

interface DiscoverView {

    fun onFetchedAllItems(resultList: List<Restaurant>)

    fun showProgress()

    fun hideProgress()

    fun showError()
}