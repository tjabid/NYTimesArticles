package com.sample.nytimesarticles.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sample.nytimesarticles.R
import com.sample.nytimesarticles.databinding.CellDurationBinding
import com.sample.nytimesarticles.model.Duration
import java.lang.ref.WeakReference

class DaysFilterAdapter(
    var list: List<Duration>,
    private val listener: OnFilterClickListener
) : RecyclerView.Adapter<DaysFilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list, position)
    }

    override fun getItemCount(): Int = list.size

    interface OnFilterClickListener {
        fun onClicked(value: String)
    }

    fun getSelected(): String {
        return list.firstOrNull { it.isSelected }?.value ?: Duration.ONE_DAY.value
    }

    class ViewHolder private constructor(
        private val binding: CellDurationBinding,
        private val listenerRef: WeakReference<OnFilterClickListener>?
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(list: List<Duration>, position: Int) {
            val item = list[position]
            binding.durationDays.setTextColor(
                ContextCompat.getColor(
                    binding.durationDays.context,
                    if (item.isSelected) {
                        R.color.white_text_color
                    } else {
                        R.color.black_text_color
                    }
                )
            )
            binding.durationDays.background =
                ContextCompat.getDrawable(
                    binding.durationDays.context, if (item.isSelected) {
                        R.drawable.bg_round
                    } else {
                        R.drawable.bg_stroke_round
                    }
                )

            binding.durationDays.text = binding.durationDays.context.getString(item.name)
            binding.durationDays.setOnClickListener {
                val selected = list.firstOrNull { it.isSelected }
                if (selected?.value != item.value) {
                    selected?.isSelected = false
                    item.isSelected = true
                    listenerRef?.get()?.onClicked(item.value)
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, listener: OnFilterClickListener?): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CellDurationBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, WeakReference(listener))
            }
        }
    }
}