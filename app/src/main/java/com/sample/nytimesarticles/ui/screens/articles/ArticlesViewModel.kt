package com.sample.nytimesarticles.ui.screens.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sample.nytimesarticles.model.Article
import com.sample.nytimesarticles.model.MostPopularArticles
import com.sample.nytimesarticles.repository.Repository

class ArticlesViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _duration: MutableLiveData<String> = MutableLiveData()

    val articles : LiveData<MostPopularArticles> = Transformations
        .switchMap(_duration) {
            repository.getMostViewedArticles(it)
        }

    fun setDuration(duration: String){
        if (duration.isEmpty() || _duration.value == duration) {
            return
        }
        _duration.value = duration
    }

    fun retry(duration: String){
        if (duration.isEmpty()) {
            return
        }
        _duration.value = duration
    }

    fun cancelJobs(){
        repository.cancelJobs()
    }
}