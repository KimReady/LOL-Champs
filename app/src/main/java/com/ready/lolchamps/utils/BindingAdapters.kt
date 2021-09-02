package com.ready.lolchamps.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.ui.base.UiState
import com.ready.lolchamps.ui.main.ChampionAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("progress")
    fun ProgressBar.bindProgressBar(value: Int?) {
        value?.let {
            progress = it
        }
    }

    @JvmStatic
    @BindingAdapter("splashImage")
    fun AppCompatImageView.bindSplashImage(name: String) {
        Glide.with(context)
            .load(getSplashImageUri(name))
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("show")
    fun View.bindShow(uiState: UiState) {
        visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun RecyclerView.bindToast(uiState: UiState) {
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
    @BindingAdapter("itemDecoration")
    fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        addItemDecoration(itemDecoration)
    }

    @JvmStatic
    @BindingAdapter("champions")
    fun RecyclerView.bindSubmitList(uiState: UiState) {
        val boundAdapter = this.adapter
        if (boundAdapter is ChampionAdapter
            && uiState is UiState.Success<*>) {
            uiState.data?.let {
                boundAdapter.submitList(it as List<Champion>)
            }
        }
    }
}