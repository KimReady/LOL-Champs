package com.ready.lolchamps.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.ready.lolchamps.R
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.ui.detail.SkinAdapter
import com.ready.lolchamps.ui.main.ChampionAdapter
import com.ready.lolchamps.ui.base.UiState


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("image")
    fun AppCompatImageView.bindImage(uri: String?) {
        if (uri != null) {
            Glide.with(context)
                .load(uri)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["championId", "skinNum"], requireAll = true)
    fun AppCompatImageView.bindSkinImage(championId: String?, skinNum: Int?) {
        if (championId != null && skinNum != null) {
            Glide.with(context)
                .load(getSkinImageUri(championId, skinNum))
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("show")
    fun ProgressBar.bindShow(uiState: UiState) {
        visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun View.bindToast(uiState: UiState) {
        if (uiState is UiState.Error) {
            uiState.error?.message?.let { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("skinAdapter")
    fun CarouselRecyclerview.bindSkinAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter

        setInfinite(true)
        setAlpha(true)
        setIntervalRatio(0.9f)
        isNestedScrollingEnabled = false
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        addItemDecoration(itemDecoration)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("championItems")
    fun RecyclerView.bindChampionItems(uiState: UiState) {
        val boundAdapter = this.adapter
        if (boundAdapter is ChampionAdapter && uiState is UiState.Success<*>) {
            boundAdapter.submitList(uiState.data as List<Champion>)
        }
    }

    @JvmStatic
    @BindingAdapter("skinItems")
    fun RecyclerView.bindSkinItems(skinItems: List<ChampionInfo.Skin>?) {
        val boundAdapter = this.adapter
        if (boundAdapter is SkinAdapter && !skinItems.isNullOrEmpty()) {
            boundAdapter.submitList(skinItems)
        }
    }

    @JvmStatic
    @BindingAdapter("tags")
    fun ChipGroup.bindTags(tags: List<String>?) {
        tags?.forEach { tag ->
            val tagView: Chip = Chip(context).apply {
                text = tag
                isCheckable = false
                isCloseIconVisible = false
                setChipBackgroundColorResource(R.color.purple)
                setTextAppearanceResource(R.style.TextStyle_Tag)
            }
            addView(tagView)
        }
    }
}