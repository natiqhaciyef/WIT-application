package com.natiqhaciyef.witapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.witapplication.data.models.PostModel

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    suspend fun getAllSavedPosts(): List<PostModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePost(postModel: PostModel)

    @Delete
    suspend fun removePost(postModel: PostModel)
}