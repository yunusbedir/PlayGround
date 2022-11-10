package com.yunusbedir.playground.data.remote.repository

import com.yunusbedir.playground.data.remote.model.PostsResponse
import com.yunusbedir.playground.data.remote.service.MockApiService
import com.yunusbedir.playground.domain.repository.MockApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MockApiRepositoryImpl @Inject constructor(
    private val mockApiService: MockApiService
): MockApiRepository {

    override suspend fun getPosts(): List<PostsResponse> =
        withContext(Dispatchers.IO) {
            mockApiService.getPosts()
        }
}
