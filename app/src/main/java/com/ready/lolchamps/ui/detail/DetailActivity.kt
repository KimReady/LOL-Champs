package com.ready.lolchamps.ui.detail

import android.os.Bundle
import android.view.ViewTreeObserver
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

    private lateinit var championId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        championId = intent.getStringExtra(CHAMPION_ID_KEY)
            ?: throw IllegalArgumentException("Failed to receive the champion's id")

        bind {
            vm = viewModel
            adapter = SkinAdapter(championId)
        }

        initTransition()
    }

    private fun initTransition() {
        postponeEnterTransition()
        binding.splashImageView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (binding.splashImageView.measuredHeight > 0) {
                    binding.splashImageView.viewTreeObserver.removeOnPreDrawListener(this)
                    supportStartPostponedEnterTransition()
                }
                return true
            }
        })
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()

        super.onBackPressed()
    }

    companion object {
        const val CHAMPION_ID_KEY = "CHAMPION_ID_KEY"
    }
}