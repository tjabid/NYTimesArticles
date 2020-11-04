package com.sample.nytimesarticles.ui.screen.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.nytimesarticles.mock
import com.sample.nytimesarticles.repository.Repository
import com.sample.nytimesarticles.ui.screens.articles.ArticlesViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@RunWith(JUnit4::class)
class ArticlesViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(Repository::class.java)
    private var repoViewModel = ArticlesViewModel(repository)


    @Test
    fun testNull() {
        MatcherAssert.assertThat(repoViewModel.articles, CoreMatchers.notNullValue())
        Mockito.verify(repository, Mockito.never()).getMostViewedArticles(Mockito.anyString())
    }

    @Test
    fun doNotFetchWithoutObservers() {
        repoViewModel.setDuration("1")
        Mockito.verify(repository, Mockito.never()).getMostViewedArticles(Mockito.anyString())
    }

    @Test
    fun fetchWhenObserved() {
        repoViewModel.setDuration("1")
        repoViewModel.articles.observeForever(mock())
        Mockito.verify(repository).getMostViewedArticles("1")
    }

    @Test
    fun changeWhileObserved() {
        repoViewModel.articles.observeForever(mock())

        repoViewModel.setDuration("1")
        repoViewModel.setDuration("7")

        Mockito.verify(repository).getMostViewedArticles("1")
        Mockito.verify(repository).getMostViewedArticles("7")
    }

    @Test
    fun blankSetDuration() {
        repoViewModel.articles.observeForever(mock())
        repoViewModel.setDuration("")
        Mockito.verify(repository, Mockito.never()).getMostViewedArticles(Mockito.anyString())
    }

    @Test
    fun retry() {
        repoViewModel.articles.observeForever(mock())
        repoViewModel.retry("1")
        Mockito.verify(repository).getMostViewedArticles("1")
    }

    @Test
    fun blankRetry() {
        repoViewModel.articles.observeForever(mock())
        repoViewModel.retry("")
        Mockito.verify(repository, Mockito.never()).getMostViewedArticles(Mockito.anyString())
    }

}