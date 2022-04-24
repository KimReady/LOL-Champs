package com.ready.lolchamps.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ActivityMainBinding
import com.ready.lolchamps.ui.base.BaseActivity
import com.ready.lolchamps.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private val championAdapter: ChampionAdapter by lazy {
        ChampionAdapter { champion, sharedElements ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.CHAMPION_ID_KEY, champion.id)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                sharedElements
            )

            startActivity(intent, options.toBundle())
        }
    }
    private val championItemDecoration: ChampionItemDecoration by lazy {
        ChampionItemDecoration()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            vm = viewModel
            adapter = championAdapter
            itemDecoration = championItemDecoration
        }
    }
}