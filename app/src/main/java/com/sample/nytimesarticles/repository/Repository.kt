package com.sample.nytimesarticles.repository

import androidx.lifecycle.MutableLiveData
import com.sample.nytimesarticles.model.MostPopularArticles
import com.sample.nytimesarticles.network.NetworkServiceCreator
import com.sample.nytimesarticles.network.mapper.ArticleMapper
import com.sample.nytimesarticles.network.mapper.ArticlesResponse
import com.sample.nytimesarticles.network.service.NetworkService
import com.sample.nytimesarticles.util.Constants.API_KEY
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 */

object Repository {

    private val webservice: NetworkService by lazy {
        NetworkServiceCreator.networkService
    }

    var job: CompletableJob? = null


    /**
     * Fetch most viewed articles from NY Times with unique key registered api key
     * @param duration the time limit of articles as String
     * @return a list of articles that match our duration
     */
    fun getMostViewedArticles(duration: String): MutableLiveData<MostPopularArticles> {

        job = Job()

        val data = MutableLiveData<MostPopularArticles>()

        webservice.getMostViewedArticles(duration, API_KEY)
            .enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                data.value = ArticleMapper().map(response, null)
                job?.complete()
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                data.value = ArticleMapper().map(
                    null,
                    "Cannot load articles, please check your internet connection and retry."
                )
                job?.complete()
            }
        })
        return data
    }

    fun cancelJobs(){
        job?.cancel()
    }
}