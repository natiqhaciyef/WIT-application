package com.natiqhaciyef.witapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.natiqhaciyef.witapplication.data.models.PostModel

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    suspend fun getAllSavedPosts(): List<PostModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePost(postModel: PostModel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateSavedPost(postModel: PostModel)

    @Delete
    suspend fun removeSavedPost(postModel: PostModel)
}