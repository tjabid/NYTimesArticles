package com.sample.nytimesarticles.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentDetailBinding

/**
 * A fragment representing a single Item detail screen.
 */
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        arguments?.let {
            val url = DetailFragmentArgs.fromBundle(it).articleUrl

            viewModelFactory = DetailViewModelFactory(url)
            viewModel = ViewModelProvider(this, viewModelFactory)
                .get(DetailViewModel::class.java)

            binding.itemDetail.loadUrl(viewModel.articleUrl)
        }

        return binding.root
    }

}