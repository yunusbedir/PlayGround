package com.yunusbedir.base.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<P, R> {
    fun invoke(params: P): Flow<UseCaseState<R>>
}