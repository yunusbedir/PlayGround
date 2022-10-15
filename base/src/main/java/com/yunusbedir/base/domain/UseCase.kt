package com.yunusbedir.base.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<P, R> {
    suspend fun invoke(params: P): Flow<UseCaseState<R>>
}