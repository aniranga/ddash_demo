package com.anita.doordashdemo.home

import com.anita.doordashdemo.data.model.Restaurant
import com.anita.doordashdemo.data.provider.DataAccessor
import com.anita.doordashdemo.data.provider.DataAccessorProvider
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DiscoverPresenterImpl(private val discoverView: DiscoverView, dataAccessorProvider: DataAccessorProvider) : DiscoverPresenter {

    private val dataAccessor: DataAccessor = dataAccessorProvider.getDataAccessor()
    private var disposables: CompositeDisposable = CompositeDisposable()
    private var itemsList: ArrayList<Restaurant> = ArrayList()
    var viewResList = ArrayList<DiscoverPageListItem>()
    var canLoadMore: Boolean = true

    override fun fetchItems() {
        discoverView.showProgress()
        dataAccessor.getRestaurantList(RestaurantListSubscriber())
    }

    override fun destroy() {
        disposables.clear()
    }

    private inner class RestaurantListSubscriber : Observer<List<Restaurant>> {
        override fun onNext(list: List<Restaurant>) {
            discoverView.hideProgress()
            for (res in list) {
                itemsList.add(res)
                viewResList.add(RestaurantListItem(DiscoverPageListItem.ListItemViewType.OBJ_LIST_ITEM, res))
            }
            //resList.add(LoadMoreProgressListItem(DiscoverPageListItem.ListItemViewType.LOAD_MORE_PROGRESS_ITEM))
            discoverView.onFetchedAllItems(viewResList)
        }

        override fun onError(e: Throwable) {
            discoverView.hideProgress()
            discoverView.showError()
        }

        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {
            disposables.add(d)
        }
    }

    override fun canLoadMore(): Boolean {
        return canLoadMore
    }

    override fun loadMore() {
        dataAccessor.fetchItemsPage(itemsList.size, 20, LoadMoreSubscriber())
    }

    private inner class LoadMoreSubscriber : Observer<List<Restaurant>> {
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: List<Restaurant>) {
            if (t != null && t.isEmpty()) {
                canLoadMore = false
                discoverView.loadMoreCompleted(viewResList)
                return
            }
            for (res in t) {
                itemsList.add(res)
                viewResList.add(RestaurantListItem(DiscoverPageListItem.ListItemViewType.OBJ_LIST_ITEM, res))
            }
            //resList.add(LoadMoreProgressListItem(DiscoverPageListItem.ListItemViewType.LOAD_MORE_PROGRESS_ITEM))
            discoverView.loadMoreCompleted(viewResList)
        }

        override fun onError(e: Throwable) {

        }

    }
}