package com.ready.lolchamps.ui.main

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ItemChampionBinding
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.ui.detail.DetailActivity
import com.ready.lolchamps.ui.detail.DetailActivity.Companion.CHAMPION_ID_KEY
import android.util.Pair
import androidx.core.view.ViewCompat
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ChampionAdapter @Inject constructor(
    @ActivityContext private val context: Context
) : ListAdapter<Champion, ChampionAdapter.ChampionViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChampionBinding>(layoutInflater, R.layout.item_champion, parent, false)
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChampionViewHolder(
        private val binding: ItemChampionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                if (context is Activity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(CHAMPION_ID_KEY, getItem(position).id)
                    }
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        context,
                        Pair(binding.championSplashImage, binding.championSplashImage.transitionName))

                    context.startActivity(intent, options.toBundle())
                }
            }
        }

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