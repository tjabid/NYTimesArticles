package com.sample.nytimesarticles.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 */
class DetailViewModelFactory (private val url: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}