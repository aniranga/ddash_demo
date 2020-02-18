package com.anita.doordashdemo.home

interface DiscoverView {

    fun onFetchedAllItems(resultList: List<DiscoverPageListItem>)

    fun showProgress()

    fun hideProgress()

    fun showError()
}