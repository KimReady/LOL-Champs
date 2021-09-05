package com.ready.lolchamps.ui.detail

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ActivityDetailBinding
import com.ready.lolchamps.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            vm = viewModel
            adapter = SkinAdapter(viewModel.championId)
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