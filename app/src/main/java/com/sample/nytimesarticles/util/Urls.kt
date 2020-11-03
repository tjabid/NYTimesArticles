package com.sample.nytimesarticles.util

import com.sample.nytimesarticles.util.Params.duration

/**
 * Purpose: Contains all URLs of app
 */
object Urls {

    const val BASE_URL = "http://api.nytimes.com/svc/"

    // App APIs Urls
    const val GET_MOST_VIEWED_ARTICLES = "mostpopular/v2/mostviewed/all-sections/{$duration}.json"
}