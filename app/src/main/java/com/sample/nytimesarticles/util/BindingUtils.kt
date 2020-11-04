package com.sample.nytimesarticles.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

@BindingAdapter("articleImage")
fun ImageView.setArticleImage(thumbnail: String) {

    Glide.with(this.context)
        .load(thumbnail)
        .transform(CircleCrop())
        .placeholder(android.R.drawable.ic_menu_gallery)
        .error(android.R.drawable.ic_menu_gallery)
        .fallback(android.R.drawable.ic_menu_gallery)
        .into(this)
}
