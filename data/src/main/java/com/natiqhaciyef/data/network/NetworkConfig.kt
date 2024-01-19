package com.natiqhaciyef.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkConfig{
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val logger = OkHttpClient.Builder().addInterceptor(interceptor)
    val BASE_URL = "https://techtive.tech/wit/"
    const val API_KEY = "30e171ab-3e37-4a9e-b146-5965922caf97"
}

internal object PostEndpoint{
    const val GET = "posts/get_posts.php"
    const val DELETE = "posts/delete_post.php"
    const val UPDATE = "posts/update_post.php"
    const val INSERT = "posts/insert_post.php"
}

internal object ContactEndpoint{
    const val GET = "contacts/get_contacts.php"
    const val DELETE = "contacts/delete_contact.php"
    const val INSERT = "contacts/insert_contact.php"
}

internal object InterviewQuestionEndpoint{
    const val GET = "interview/get_questions.php"
    const val INSERT = "interview/insert_question.php"
}

internal object ExamEndpoint{
    const val GET = "exams/get_exams.php"
    const val INSERT = "exams/insert_exam.php"
}