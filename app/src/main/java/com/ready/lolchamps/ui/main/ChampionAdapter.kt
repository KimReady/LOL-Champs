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
import android.util.Pair
import android.view.View

class ChampionAdapter(
    private val whenItemClicked: (Champion, Pair<View, String>) -> Unit
) : ListAdapter<Champion, ChampionAdapter.ChampionViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChampionBinding>(layoutInflater, R.layout.item_champion, parent, false)
        return ChampionViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                whenItemClicked(
                    getItem(position),
                    Pair(binding.championSplashImage, binding.championSplashImage.transitionName)
                )
            }
        }
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