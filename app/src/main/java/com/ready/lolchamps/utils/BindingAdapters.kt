package com.ready.lolchamps.utils

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ready.lolchamps.R
import com.ready.lolchamps.ui.main.ChampionAdapter
import com.ready.lolchamps.ui.main.MainUiState


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("splashImage")
    fun AppCompatImageView.bindSplashImage(name: String?) {
        if (name != null) {
            Glide.with(context)
                .load(getSplashImageUri(name))
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("passiveImage")
    fun AppCompatImageView.bindPassiveImage(name: String?) {
        if (name != null) {
            Glide.with(context)
                .load(getPassiveImageUri(name))
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("spellImage")
    fun AppCompatImageView.bindSpellImage(name: String?) {
        if (name != null) {
            Glide.with(context)
                .load(getSpellImageUri(name))
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("show")
    fun View.bindShow(uiState: MainUiState) {
        visibility = if (uiState is MainUiState.Loading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("isLoading")
    fun View.bindIsLoading(isLoading: Boolean?) {
        if (isLoading != null) {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun View.bindToast(uiState: MainUiState) {
        if (uiState is MainUiState.Error) {
            uiState.error?.message?.let { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("error")
    fun View.bindErrorMessage(error: Throwable?) {
        error?.message?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        addItemDecoration(itemDecoration)
    }

    @JvmStatic
    @BindingAdapter("champions")
    fun RecyclerView.bindSubmitList(uiState: MainUiState) {
        val boundAdapter = this.adapter
        if (boundAdapter is ChampionAdapter && uiState is MainUiState.Success) {
            boundAdapter.submitList(uiState.data)
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