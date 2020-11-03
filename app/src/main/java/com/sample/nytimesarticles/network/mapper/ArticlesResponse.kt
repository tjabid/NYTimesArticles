package com.sample.nytimesarticles.network.mapper

import com.google.gson.annotations.SerializedName

/**
 * response from web service
 */
data class ArticlesResponse (
    val status : String,
    val num_results : Int,
    @SerializedName("results")
    val articles : List<ArticleResponse>
) {

    data class ArticleResponse (
        val id : Long,
        val byline : String,
        val title : String,
        @SerializedName("published_date")
        val publishedDate : String?,
        val updated : String?,
        val section : String?,
        val media : List<MediaResponse>,
        val url : String
    )

    data class MediaResponse (
        val type : String,
        val caption : String,
        @SerializedName("media-metadata")
        val metadata : List<MediaMetadata>
    )

    data class MediaMetadata (
        val url : String,
        val format : String,
        val height : Int,
        val width : Int
    )
}