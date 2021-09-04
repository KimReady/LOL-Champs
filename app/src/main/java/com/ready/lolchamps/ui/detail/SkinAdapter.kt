package com.ready.lolchamps.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ItemChampionSkinBinding
import com.ready.lolchamps.model.ChampionInfo

class SkinAdapter(
    private val id: String
) : ListAdapter<ChampionInfo.Skin, SkinAdapter.SkinViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChampionSkinBinding>(layoutInflater, R.layout.item_champion_skin, parent, false)
        return SkinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SkinViewHolder(val binding: ItemChampionSkinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChampionInfo.Skin) {
            binding.apply {
                championId = id
                skin = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ChampionInfo.Skin>() {
            override fun areItemsTheSame(oldItem: ChampionInfo.Skin, newItem: ChampionInfo.Skin): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ChampionInfo.Skin, newItem: ChampionInfo.Skin): Boolean =
                oldItem == newItem
        }
    }
}