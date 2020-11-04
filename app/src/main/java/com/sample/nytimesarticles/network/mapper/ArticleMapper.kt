package com.sample.nytimesarticles.network.mapper

import com.sample.nytimesarticles.model.Article
import com.sample.nytimesarticles.model.MostPopularArticles
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticleMapper {

    fun map(response: Response<ArticlesResponse>?, error: String?): MostPopularArticles {
        return MostPopularArticles(
            list = parseList(response?.body()),
            error = parseError(response, error)
        )
    }

    private fun parseList(response: ArticlesResponse?): List<Article>? {
        response?.let {
            if (response.articles.isNotEmpty()) {
                val articles = ArrayList<Article>()
                for (item in response.articles) {
                    articles.add(parseArticle(item))
                }
                return articles
            }
        }
        return null
    }

    private fun parseArticle(item: ArticlesResponse.ArticleResponse): Article {
        var publish = ""
        try {
            val date = when {
                item.publishedDate?.isNotEmpty() == true -> {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.publishedDate)
                }
                item.updated?.isNotEmpty() == true -> {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(item.updated)
                }
                else -> {
                    null
                }
            }
            date?.let {
                publish = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Article(
            id = item.id,
            title = item.title,
            url = item.url,
            author = item.byline,
            thumbnail = item.media.firstOrNull()?.metadata?.lastOrNull()?.url ?: "",
            publishedDate = publish
        )
    }

    private fun parseError(response: Response<ArticlesResponse>?, error: String?): String? {
        return response?.let {
            if (it.isSuccessful) {
                if (response.body() == null || response.code() == 204) {
                    ""
                } else {
                    null
                }
            } else {
                response.errorBody()?.string() ?: response.message()
            }
        } ?: error
    }
}
