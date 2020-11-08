package com.sample.nytimesarticles.ui.screens.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentArticlesBinding
import com.sample.nytimesarticles.model.Article
import com.sample.nytimesarticles.model.Duration.Companion.ONE_DAY
import com.sample.nytimesarticles.model.Duration.Companion.SEVEN_DAYS
import com.sample.nytimesarticles.model.Duration.Companion.THIRTY_DAYS
import com.sample.nytimesarticles.repository.Repository
import com.sample.nytimesarticles.ui.adapter.ArticleAdapter
import com.sample.nytimesarticles.ui.adapter.DaysFilterAdapter

class ArticlesFragment : Fragment() {

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var viewModelFactory: ArticlesViewModelFactory
    private lateinit var adapter: ArticleAdapter
    private lateinit var binding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_articles, container, false)


        viewModelFactory = ArticlesViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticlesViewModel::class.java)

        val adapterDuration = DaysFilterAdapter(
            listOf(ONE_DAY, SEVEN_DAYS, THIRTY_DAYS),
            object : DaysFilterAdapter.OnFilterClickListener {
                override fun onClicked(value: String) {
                    adapter.submitList(listOf())
                    binding.rvDays.adapter?.notifyDataSetChanged()
                    viewModel.setDuration(value)
                    binding.viewErrorLoading.root.visibility = GONE
                }
            }
        )
        binding.rvDays.layoutManager = LinearLayoutManager(binding.rvDays.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDays.adapter = adapterDuration

        binding.viewErrorLoading.connectionRetryBtn.setOnClickListener {
            viewModel.retry(adapterDuration.getSelected())
            binding.viewErrorLoading.root.visibility = GONE
        }

        adapter = ArticleAdapter(object : ArticleAdapter.OnItemClickListener {
            override fun onClicked(article: Article) {
                val action = ArticlesFragmentDirections.actionArticlesToDetails()
                action.articleUrl = article.url
                findNavController().navigate(action)
            }
        })
        binding.recyclerView.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) {
            it?.list?.let { list ->
                adapter.submitList(list)
            } ?: showError(it?.error)
        }
        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.lifecycleOwner = this

        viewModel.setDuration(ONE_DAY.value)

        return binding.root
    }

    private fun showError(error: String?) {
        error?.let { binding.viewErrorLoading.errorMessage.text = it }
        binding.viewErrorLoading.root.visibility = VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }


}

