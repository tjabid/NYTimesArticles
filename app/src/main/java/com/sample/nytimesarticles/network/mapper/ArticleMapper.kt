package com.sample.nytimesarticles.network.mapper

import com.sample.nytimesarticles.model.Article
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticleMapper {

    fun map(response: ArticlesResponse): List<Article> {
        val articles = ArrayList<Article>()

        val list = response.articles
        if (list.isNotEmpty()) {
            for (item in list) {
                articles.add(parseArticle(item))
            }
        }
        return articles
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
}
