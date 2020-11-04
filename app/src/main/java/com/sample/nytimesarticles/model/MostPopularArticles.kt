package com.sample.nytimesarticles.model

/**
 *
 */

data class MostPopularArticles(
    val list : List<Article>?,
    val error: String?
)