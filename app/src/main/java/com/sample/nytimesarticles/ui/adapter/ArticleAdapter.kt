package com.sample.nytimesarticles.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.nytimesarticles.databinding.CellArticleBinding
import com.sample.nytimesarticles.model.Article
import java.lang.ref.WeakReference

/**
 *
 */
internal class ArticleAdapter(
    private var listener: OnItemClickListener?
): ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ArticleViewHolder private constructor(
        private val binding: CellArticleBinding,
        private val listenerRef: WeakReference<OnItemClickListener>?
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Article) {
            binding.article = item
            binding.itemMainLayout.setOnClickListener { listenerRef?.get()?.onClicked(item) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, listener: OnItemClickListener?): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CellArticleBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding, WeakReference(listener))
            }
        }
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onClicked(article: Article)
    }
}