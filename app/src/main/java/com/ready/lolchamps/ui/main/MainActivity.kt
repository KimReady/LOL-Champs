package com.ready.lolchamps.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ready.lolchamps.R
import com.ready.lolchamps.databinding.ActivityMainBinding
import com.ready.lolchamps.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            vm = viewModel
            adapter = ChampionAdapter(this@MainActivity)
            itemDecoration = ChampionItemDecoration()
        }
    }
}