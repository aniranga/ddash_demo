package com.anita.doordashdemo.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anita.doordashdemo.R
import com.anita.doordashdemo.data.model.Restaurant
import com.anita.doordashdemo.data.provider.DataAccessorProvider
import com.anita.doordashdemo.home.DiscoverDetail.Companion.RESTAURANT_DESC_KEY
import com.anita.doordashdemo.home.DiscoverDetail.Companion.RESTAURANT_NAME_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_discover.view.*

class DiscoverFragment: Fragment(), DiscoverView, DiscoverListAdapter.ClickCallback {

    private var presenter: DiscoverPresenter?= null
    private var navController : NavController?= null
    private var snackBar: Snackbar?= null
    private var recyclerView: RecyclerView?= null
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView = view.recycler_list
        layoutManager = LinearLayoutManager(view.context)
        recyclerView?.layoutManager = layoutManager
        val spacing = resources.getDimensionPixelSize(R.dimen.recycler_item_spacing)
        val spaceDecoration = SpacesItemDecoration(spacing)
        recyclerView?.addItemDecoration(spaceDecoration)
        presenter = DiscoverPresenterImpl(this, activity?.application as DataAccessorProvider)
        presenter?.fetchItems()
    }

    override fun onDestroy() {
        Log.e("DiscoverFragment", "onDestroy")
        if (presenter != null) {
            presenter?.destroy()
        }
        super.onDestroy()
    }

    override fun onFetchedAllItems(resultList: List<DiscoverPageListItem>) {
        if (activity != null) {
            recyclerView?.visibility = View.VISIBLE
            recyclerView?.adapter = DiscoverListAdapter(resultList,this, activity as Context)
        }
    }

    override fun showProgress() {
        progress_circular?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_circular?.visibility = View.GONE
    }

    override fun showError() {
        //TODO: show snackbar here
    }

    override fun onRestaurantItemClicked(restaurant: Restaurant) {
        var restInfoBundle = Bundle()
        restInfoBundle.putString(RESTAURANT_NAME_KEY, restaurant.name)
        restInfoBundle.putString(RESTAURANT_DESC_KEY, restaurant.description)
        navController?.navigate(R.id.action_discoverFragment_to_discoverDetail, restInfoBundle)
    }

    inner class SpacesItemDecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = mSpace;
        }
    }

}