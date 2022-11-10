package com.yunusbedir.playground.ui

import androidx.lifecycle.viewModelScope
import com.yunusbedir.base.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 *
 */
class MainActivityViewModel : BaseViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(DELAY)
            _isLoading.value = false
        }
    }

    companion object {
        const val DELAY = 1_000L
    }
}
