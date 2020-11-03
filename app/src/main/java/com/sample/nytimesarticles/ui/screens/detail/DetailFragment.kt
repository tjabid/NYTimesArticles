package com.sample.nytimesarticles.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.FragmentDetailBinding

/**
 * A fragment representing a single Item detail screen.
 */
class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            if (it.containsKey(ARG_ITEM_ID)) {
//                // Load the dummy content specified by the fragment
//                // arguments. In a real-world scenario, use a Loader
//                // to load content from a content provider.
//                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
//                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.content
//            }
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        binding.itemDetail.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailToArticles())
        }
        return binding.root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}