package com.ready.lolchamps.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ItemChampionBinding
import com.ready.lolchamps.model.Champion
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class ChampionAdapter : ListAdapter<Champion, ChampionAdapter.ChampionViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChampionBinding>(layoutInflater, R.layout.item_champion, parent, false)
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChampionViewHolder(
        private val binding: ItemChampionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Champion) {
            binding.apply {
                champion = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Champion>() {
            override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean =
                oldItem == newItem
        }
    }
}