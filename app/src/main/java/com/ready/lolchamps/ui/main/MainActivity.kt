package com.ready.lolchamps.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ActivityMainBinding
import com.ready.lolchamps.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    @Inject lateinit var championAdapter: ChampionAdapter
    @Inject lateinit var championItemDecoration: ChampionItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            vm = viewModel
            adapter = championAdapter
            itemDecoration = championItemDecoration
        }
    }
}