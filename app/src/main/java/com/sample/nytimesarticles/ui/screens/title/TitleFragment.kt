package com.sample.nytimesarticles.ui.screens.title

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentTitleBinding

/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        binding.imgLogo.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToArticles())
        }
        return binding.root
    }
}