package com.natiqhaciyef.witapplication.util_module_test.mappers

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.natiqhaciyef.util.common.mappers.toMappedPost
import com.natiqhaciyef.util.common.mappers.toPost
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import org.junit.Test

class PostMapperTest {
    private val user = Gson().toJson(
        UserWithoutPasswordModel(
            id = 1,
            name = "Arslan",
            email = "arslan123@gmail.com"
        )
    )

    @Test
    fun `standard to mapped post returns success`() {
        val postModel = PostModel(
            id = 0,
            title = "title1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = user.toString()
        )

        val mappedPostModel = MappedPostModel(
            id = 0,
            title = "title1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = UserWithoutPasswordModel(id = 1, name = "Arslan", email = "arslan123@gmail.com")
        )

        val result = postModel.toMappedPost()
        assertThat(result).isEqualTo(mappedPostModel)
    }

    @Test
    fun `mapped to standard post returns success`() {
        val postModel = PostModel(
            id = 0,
            title = "title1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = user.toString()
        )

        val mappedPostModel = MappedPostModel(
            id = 0,
            title = "title1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = UserWithoutPasswordModel(id = 1, name = "Arslan", email = "arslan123@gmail.com")
        )

        val result = mappedPostModel.toPost()
        assertThat(result).isEqualTo(postModel)
    }


    @Test
    fun `mapped to standard post with empty title returns error`(){
        val mappedPostModel = MappedPostModel(
            id = 0,
            title = "",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = UserWithoutPasswordModel(id = 1, name = "Arslan", email = "arslan123@gmail.com")
        )

        val result = mappedPostModel.toPost()
        assertThat(result).isNull()
    }

    @Test
    fun `mapped to standard post with empty description returns error`(){
        val mappedPostModel = MappedPostModel(
            id = 0,
            title = "title 1",
            description = "",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = UserWithoutPasswordModel(id = 1, name = "Arslan", email = "arslan123@gmail.com")
        )

        val result = mappedPostModel.toPost()
        assertThat(result).isNull()
    }

    @Test
    fun `mapped to standard post with empty publish date returns error`(){
        val mappedPostModel = MappedPostModel(
            id = 0,
            title = "title 1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "",
            user = UserWithoutPasswordModel(id = 1, name = "Arslan", email = "arslan123@gmail.com")
        )

        val result = mappedPostModel.toPost()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped post with empty title returns error`(){
        val postModel = PostModel(
            id = 0,
            title = "",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = user.toString()
        )


        val result = postModel.toMappedPost()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped post with empty description returns error`(){
        val postModel = PostModel(
            id = 0,
            title = "title 1",
            description = "",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = user.toString()
        )


        val result = postModel.toMappedPost()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped post with empty publish date returns error`(){
        val postModel = PostModel(
            id = 0,
            title = "title 1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "",
            user = user.toString()
        )


        val result = postModel.toMappedPost()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped post with empty user returns error`(){
        val postModel = PostModel(
            id = 0,
            title = "title 1",
            description = "desc 1",
            image = null,
            likeCount = 12,
            location = null,
            publishDate = "19.10.2023",
            user = ""
        )


        val result = postModel.toMappedPost()
        assertThat(result).isNull()
    }
}