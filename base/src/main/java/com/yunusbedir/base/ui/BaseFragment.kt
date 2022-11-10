package com.yunusbedir.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return _binding?.root
    }

    /** Memory leaky i engellemek için destory durumunda _binding sıfırlanmalıdır. **/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupCollectAndObserve()
        setupUI()
        setupListeners()
    }

    // region generic fun
    /** Generic collect flow **/
    fun <T> Flow<T>.collectLaunchOnLifecycleScope(execute: suspend (data: T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            collect {
                execute(it)
            }
        }
    }
    // endregion generic fun

    // region abstract fun
    abstract fun setupUI()

    abstract fun setupCollectAndObserve()

    abstract fun setupListeners()
    // endregion abstract fun

}
