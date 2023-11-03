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
    const val GET_BY_EMAIL = "users/get_user_by_email.php"
    const val DELETE = "users/delete_user.php"
    const val UPDATE = "users/update_user.php"
    const val INSERT = "users/insert_user.php"
}

object VerifiedUserEndpoint{
    const val GET = "users/verified_users/get_verified_users.php"
    const val GET_BY_EMAIL = "users/verified_users/get_verified_user_by_email.php"
    const val DELETE = "users/verified_users/delete_verified_user.php"
    const val UPDATE = "users/verified_users/update_verified_user.php"
    const val INSERT = "users/verified_users/insert_verified_user.php"
}

object PostEndpoint{
    const val GET = "posts/get_posts.php"
    const val DELETE = "posts/delete_post.php"
    const val UPDATE = "posts/update_post.php"
    const val INSERT = "posts/insert_post.php"
}

object ContactEndpoint{
    const val GET = "contacts/get_contacts.php"
    const val DELETE = "contacts/delete_contact.php"
    const val INSERT = "contacts/insert_contact.php"
}

object InterviewQuestionEndpoint{
    const val GET = "interview/get_questions.php"
    const val INSERT = "interview/insert_question.php"
}