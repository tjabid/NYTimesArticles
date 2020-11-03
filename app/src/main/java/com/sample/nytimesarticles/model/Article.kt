package com.sample.nytimesarticles.model

data class Article (
    val id: Long,
    val author : String,
    val title : String,
    val publishedDate : String?,
    val thumbnail : String,
    val url : String
)
