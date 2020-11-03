package com.sample.nytimesarticles.network.service

import com.sample.nytimesarticles.network.mapper.ArticlesResponse
import com.sample.nytimesarticles.util.Params
import com.sample.nytimesarticles.util.Urls.GET_MOST_VIEWED_ARTICLES
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for web services
 */
interface NetworkService {
    /**
     * @GET declares an HTTP GET request
     * @Path(Params.duration) annotation on the parameter(1, 7, 30 days duration) marks it as a
     * @Query(Params.api_key) unique key registered at NY times as String
     * replacement for the {duration} placeholder in the @GET path
     */
    @GET(GET_MOST_VIEWED_ARTICLES)
    fun getMostViewedArticles(
        @Path(Params.duration) duration: String,
        @Query(Params.api_key) apiKey: String
    ): Call<ArticlesResponse>
}