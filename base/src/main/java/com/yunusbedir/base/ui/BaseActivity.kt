package com.yunusbedir.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        onBeforeSetContent()
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding!!.root)
        setup()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setup() {
        setupCollectAndObserve()
        setupUI()
        setupListeners()
    }

    // region generic fun
    /** Generic collect flow **/
    fun <T> Flow<T>.collectLaunchOnLifecycleScope(execute: suspend (data: T) -> Unit) {
        lifecycleScope.launch {
            collect {
                execute(it)
            }
        }
    }

    protected open fun onBeforeSetContent(){

    }
    // endregion generic fun

    // region abstract fun

    protected abstract fun setupUI()

    protected abstract fun setupCollectAndObserve()

    protected abstract fun setupListeners()
    // endregion abstract fun

}
