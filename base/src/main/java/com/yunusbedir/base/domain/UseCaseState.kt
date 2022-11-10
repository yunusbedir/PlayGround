package com.yunusbedir.base.domain

sealed class UseCaseState<T> {
    data class Success<T>(val result: T) : UseCaseState<T>()
    data class Error<T>(val errorMessage: String) : UseCaseState<T>()
}
