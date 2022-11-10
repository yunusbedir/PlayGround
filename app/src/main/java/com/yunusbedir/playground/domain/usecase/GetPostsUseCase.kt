package com.yunusbedir.playground.domain.usecase

import com.yunusbedir.base.domain.UseCase
import com.yunusbedir.base.domain.UseCaseState
import com.yunusbedir.playground.data.remote.model.PostsResponse
import com.yunusbedir.playground.domain.model.result.PostResult
import com.yunusbedir.playground.domain.repository.MockApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val mockApiRepositoryImpl: MockApiRepository
) : UseCase<Any?, List<PostResult>> {


    override suspend fun invoke(params: Any?): Flow<UseCaseState<List<PostResult>>> = flow {
        try {
            val response = mockApiRepositoryImpl.getPosts()
            val result = response.convertToResult()
            emit(UseCaseState.Success(result))
        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }

    companion object {
        fun List<PostsResponse>.convertToResult(): List<PostResult> {
            return map {
                PostResult(
                    id = it.id,
                    createdAt = it.createdAt,
                    imageUrl = it.imageUrl,
                    explanation = it.explanation,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    city = it.city,
                    likes = it.likes.size,
                    comments = it.comments.size,
                )
            }
        }
    }
}
