package com.yunusbedir.playground.domain.model.result

data class PostResult(
    val id: String,
    val createdAt: String,
    val imageUrl: String,
    val explanation: String,
    val latitude: String,
    val longitude: String,
    val city: String,
    val likes: Int,
    val comments: Int
)
