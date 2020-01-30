package com.anita.doordashdemo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anita.doordashdemo.R
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*

class DiscoverDetail: Fragment() {

    companion object {
        val RESTAURANT_NAME_KEY = "NAME_KEY"
        val RESTAURANT_DESC_KEY = "DESC_KEY"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var name = arguments?.getString(RESTAURANT_NAME_KEY) // get title & desc from bundle
        var desc = arguments?.getString(RESTAURANT_DESC_KEY)
        rest_name.text = name
        rest_desc.text = desc
    }
}