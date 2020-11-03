package com.sample.nytimesarticles.ui.screens.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentArticlesBinding
import com.sample.nytimesarticles.model.Article
import com.sample.nytimesarticles.ui.adapter.ArticleAdapter

class ArticlesFragment : Fragment() {

    private val viewModel: ArticlesViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentArticlesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_articles, container, false)

        binding.textView.setOnClickListener {
            findNavController().navigate(ArticlesFragmentDirections.actionArticlesToDetails())
        }

        adapter = ArticleAdapter(object : ArticleAdapter.OnItemClickListener {
            override fun onClicked(article: Article) {
                findNavController().navigate(ArticlesFragmentDirections.actionArticlesToDetails())
            }
        })
        binding.recyclerView.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.lifecycleOwner = this

        viewModel.setDuration("7")

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }


}