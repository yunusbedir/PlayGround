package com.yunusbedir.playground.domain.repository

import com.yunusbedir.playground.data.remote.model.PostsResponse

interface MockApiRepository {

    suspend fun getPosts(): List<PostsResponse>
}