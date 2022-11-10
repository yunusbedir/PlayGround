package com.yunusbedir.playground.di

import com.yunusbedir.playground.data.remote.repository.MockApiRepositoryImpl
import com.yunusbedir.playground.domain.repository.MockApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMockApiRepository(mockApiRepository: MockApiRepositoryImpl): MockApiRepository

}
