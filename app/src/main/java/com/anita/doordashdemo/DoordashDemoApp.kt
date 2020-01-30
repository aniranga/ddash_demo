package com.anita.doordashdemo

import android.app.Application
import com.anita.doordashdemo.data.provider.DataAccessor
import com.anita.doordashdemo.data.provider.DataAccessorImpl
import com.anita.doordashdemo.data.provider.DataAccessorProvider

class DoordashDemoApp: Application(), DataAccessorProvider {

    private var dataAccessor: DataAccessor?= null

    override fun getDataAccessor(): DataAccessor {
        if (dataAccessor == null) {
            dataAccessor = DataAccessorImpl(this)
        }
        return dataAccessor!!
    }
}