package com.ready.lolchamps.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ActivityDetailBinding
import com.ready.lolchamps.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.Factory

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, championId)
    }

    lateinit var championId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        championId = intent.getStringExtra(CHAMPION_ID_KEY)
            ?: throw IllegalArgumentException("Failed to receive the champion's id")

        bind {
            vm = viewModel
        }
    }

    companion object {
        const val CHAMPION_ID_KEY = "CHAMPION_ID_KEY"
        const val TRANSITION_KEY = "TRANSITION_KEY"
    }
}