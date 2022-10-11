package com.yunusbedir.base.domain

sealed class UIState {
    object Init : UIState()
    data class IsLoading(val isLoading: Boolean) : UIState()
    data class ShowToast(val message: String) : UIState()
    data class Error(val errorMessage: String) : UIState()
}
