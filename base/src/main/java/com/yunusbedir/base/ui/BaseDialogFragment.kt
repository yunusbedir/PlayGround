package com.yunusbedir.base.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseDialogFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : DialogFragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return _binding?.root
    }

    override fun onStart() {
        super.onStart()
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val width = metrics.widthPixels
        view?.post {
            dialog?.window?.setLayout(
                width * WEIGHT, WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
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

    companion object {
        const val WEIGHT = 8 / 12
    }
}
