package com.anita.doordashdemo.data.provider

import android.app.Application
import com.anita.doordashdemo.BuildConfig
import com.anita.doordashdemo.data.model.Restaurant
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class DataAccessorImpl(val app: Application) : DataAccessor {

    val SERVER_URL = "https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=50"
    private var apiService: ApiService? = null

    init {
        apiService = createApiService()
    }

    override fun getRestaurantList(callback: Observer<List<Restaurant>>) {
        apiService?.getRestaurantListData(SERVER_URL)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(callback)
    }

    private fun createApiService(): ApiService {

        val httpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpBuilder.addInterceptor(loggingInterceptor)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpBuilder.build())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    /**
     * retrofit interface
     */
    private interface ApiService {

        @GET
        fun getRestaurantListData(@Url url: String): Observable<List<Restaurant>>
    }
}