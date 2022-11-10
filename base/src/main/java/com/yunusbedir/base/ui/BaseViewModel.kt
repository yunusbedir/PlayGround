package com.yunusbedir.base.ui

import androidx.lifecycle.ViewModel
import com.yunusbedir.base.domain.UIState
import com.yunusbedir.base.domain.UseCaseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel : ViewModel() {

    private val _uiState = Channel<UIState>()
    val uiState get() = _uiState.receiveAsFlow()

    protected suspend fun showProgressing() {
        _uiState.send(UIState.IsLoading(true))
    }

    protected suspend fun hideProgressing() {
        _uiState.send(UIState.IsLoading(false))
    }

    protected suspend fun showToast(message: String) {
        _uiState.send(UIState.ShowToast(message))
    }

    protected suspend fun showErrorMessage(errorMessage: String) {
        _uiState.send(UIState.Error(errorMessage))
    }

    protected suspend fun <T> Flow<UseCaseState<T>>.collectWithUIState(execute: suspend (data: T) -> Unit) {
        onStart {
            showProgressing()
        }.catch { exception ->
            hideProgressing()
            showErrorMessage(exception.message.toString())
        }.collect {
            hideProgressing()
            when (it) {
                is UseCaseState.Error -> {
                    showErrorMessage(it.errorMessage)
                }
                is UseCaseState.Success -> {
                    execute.invoke(it.result)
                }
            }
        }
    }

}
