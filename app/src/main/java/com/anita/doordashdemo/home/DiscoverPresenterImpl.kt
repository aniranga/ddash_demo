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
            var resList = ArrayList<DiscoverPageListItem>()
            for (res in list) {
                resList.add(RestaurantListItem(DiscoverPageListItem.ListItemViewType.OBJ_LIST_ITEM, res))
            }
            //resList.add(LoadMoreProgressListItem(DiscoverPageListItem.ListItemViewType.LOAD_MORE_PROGRESS_ITEM))
            discoverView.onFetchedAllItems(resList)
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
}