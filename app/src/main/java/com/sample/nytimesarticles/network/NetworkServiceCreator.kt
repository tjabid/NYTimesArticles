package com.sample.nytimesarticles.network

import com.sample.nytimesarticles.network.service.NetworkService
import com.sample.nytimesarticles.util.Urls.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit client.
 */
object NetworkServiceCreator {

    val networkService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}