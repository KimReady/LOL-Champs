package com.ready.lolchamps.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
    ) : AppCompatActivity() {
    protected val binding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, layoutId)
    }

    init {
        addOnContextAvailableListener {
            binding.notifyChange()
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    protected inline fun bind(block: T.() -> Unit) {
        binding.apply(block)
    }
}