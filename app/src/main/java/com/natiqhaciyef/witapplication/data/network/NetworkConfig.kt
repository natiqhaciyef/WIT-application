package com.natiqhaciyef.witapplication.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkConfig{
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val logger = OkHttpClient.Builder().addInterceptor(interceptor)
    val BASE_URL = "https://techtive.tech/wit/"
}

object UserEndpoint{
    const val GET = "users/get_users.php"
    const val DELETE = "users/delete_user.php"
    const val UPDATE = "users/update_user.php"
    const val INSERT = "users/insert_user.php"
}

object PostEndpoint{
    const val GET = "posts/get_posts.php"
    const val DELETE = "posts/delete_post.php"
    const val UPDATE = "posts/update_post.php"
    const val INSERT = "posts/insert_post.php"
}