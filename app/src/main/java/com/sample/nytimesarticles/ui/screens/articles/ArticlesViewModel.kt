package com.sample.nytimesarticles.ui.screens.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sample.nytimesarticles.model.Article
import com.sample.nytimesarticles.repository.Repository

class ArticlesViewModel() : ViewModel() {

    private val _duration: MutableLiveData<String> = MutableLiveData()

    val articles : LiveData<List<Article>> = Transformations
        .switchMap(_duration) {
            Repository.getMostViewedArticles(it)
        }

    fun setDuration(duration: String){
        if (_duration.value == duration) {
            return
        }
        _duration.value = duration
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}