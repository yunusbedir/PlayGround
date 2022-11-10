package com.yunusbedir.playground.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostsResponse(
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "imageUrl") val imageUrl: String,
    @Json(name = "explanation") val explanation: String,
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String,
    @Json(name = "city") val city: String,
    @Json(name = "id") val id: String,
    @Json(name = "likes") val likes: List<LikeResponse>,
    @Json(name = "comments") val comments: List<CommentResponse>
){
    @JsonClass(generateAdapter = true)
    data class LikeResponse(
        @Json(name = "firstName") val firstName: String,
        @Json(name = "lastName") val lastName: String,
        @Json(name = "avatar") val avatar: String,
        @Json(name = "id") val id: String,
        @Json(name = "postId") val postId: String,
    )

    @JsonClass(generateAdapter = true)
    data class CommentResponse(
        @Json(name = "createdAt") val createdAt: String,
        @Json(name = "firstName") val firstName: String,
        @Json(name = "lastName") val lastName: String,
        @Json(name = "avatar") val avatar: String,
        @Json(name = "comment") val comment: String,
        @Json(name = "id") val id: String,
        @Json(name = "postId") val postId: String
    )
}
