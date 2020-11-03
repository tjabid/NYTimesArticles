package com.sample.nytimesarticles.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.nytimesarticles.model.Article
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
    fun getMostViewedArticles(duration: String): LiveData<List<Article>> {

        job = Job()

        val data = MutableLiveData<List<Article>>()

        webservice.getMostViewedArticles(duration, API_KEY)
            .enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.let {
                        data.value = ArticleMapper().map(it)
                    } //?: error
                }
                job?.complete()
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                job?.complete()
            }
        })
        return data
    }

    fun cancelJobs(){
        job?.cancel()
    }
}