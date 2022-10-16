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


/*

        "createdAt": "2022-10-11T20:57:10.988Z",
        "imageUrl": "http://loremflickr.com/640/480",
        "explanation": "Placeat quia omnis.",
        "latitude": "-23.1062",
        "longitude": "-162.3397",
        "city": "Lake Dandre",
        "id": "1",
        "likes": [
            {
                "firstName": "Macie",
                "lastName": "Howell",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/721.jpg",
                "id": "1",
                "postId": "1"
            },
            {
                "firstName": "Judah",
                "lastName": "Hickle",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/676.jpg",
                "id": "57",
                "postId": "1"
            },
            {
                "firstName": "Sadye",
                "lastName": "Harvey",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/414.jpg",
                "id": "91",
                "postId": "1"
            },
            {
                "firstName": "Philip",
                "lastName": "VonRueden",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/695.jpg",
                "id": "91",
                "postId": "1"
            }
        ],
        "comments": [
            {
                "createdAt": "2022-10-12T18:31:23.074Z",
                "firstName": "Zoie",
                "lastName": "Fay",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/749.jpg",
                "comment": "Qui aut totam illum expedita unde ipsam iure. Quibusdam quia maiores earum. Est rerum ratione quia voluptas rerum assumenda voluptatibus. Praesentium eveniet iure vel sequi.",
                "id": "1",
                "postId": "1"
            },
            {
                "createdAt": "2022-10-11T22:08:48.340Z",
                "firstName": "Alice",
                "lastName": "Williamson",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/474.jpg",
                "comment": "Eos vero earum porro nostrum consequatur dolores aspernatur aliquid atque. Qui et officiis. Aut dolores aut maiores quo quos deleniti.",
                "id": "44",
                "postId": "1"
            },
            {
                "createdAt": "2022-10-12T11:06:26.592Z",
                "firstName": "Eleanora",
                "lastName": "Kris",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/291.jpg",
                "comment": "Voluptatem numquam enim dignissimos ullam. Aspernatur qui explicabo quod consectetur quam debitis aperiam dolores. Recusandae non non nam ut vitae et.",
                "id": "52",
                "postId": "1"
            },
            {
                "createdAt": "2022-10-12T02:06:49.408Z",
                "firstName": "Ethan",
                "lastName": "Lindgren",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/978.jpg",
                "comment": "Ab hic quo magnam sed aliquid eum iste eius. Quia ut sunt similique accusamus. Cupiditate enim qui dolorem et at vitae impedit aperiam. Numquam cumque quia nobis cum qui.",
                "id": "78",
                "postId": "1"
            },
            {
                "createdAt": "2022-10-12T07:30:50.381Z",
                "firstName": "Vida",
                "lastName": "Bernhard",
                "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/13.jpg",
                "comment": "Recusandae similique itaque. Qui doloremque odit. Veniam quis distinctio enim provident est nemo asperiores tempore dignissimos. Ab velit qui dicta est non optio. Iste voluptatem modi ex nesciunt tempore voluptas nisi.",
                "id": "100",
                "postId": "1"
            }
        ]
    }
 */