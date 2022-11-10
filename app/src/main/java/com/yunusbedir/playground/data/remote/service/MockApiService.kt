package com.yunusbedir.playground.data.remote.service

import com.yunusbedir.playground.data.remote.model.PostsResponse
import retrofit2.http.GET

interface MockApiService {

    @GET("posts")
    suspend fun getPosts() : List<PostsResponse>
}
