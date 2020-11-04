package com.sample.nytimesarticles.ui.screens.title

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentTitleBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(1500L)
            findNavController().navigate(TitleFragmentDirections.actionTitleToArticles())
        }
    }
}