package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CoutureAvatarDao {
  @Query("SELECT * FROM saved_avatars ORDER BY createdAt DESC")
  fun getAllSavedAvatars(): Flow<List<CoutureAvatarEntity>>

  @Query("SELECT * FROM saved_avatars WHERE isFavorite = 1 ORDER BY createdAt DESC")
  fun getFavoriteAvatars(): Flow<List<CoutureAvatarEntity>>

  @Query("SELECT * FROM saved_avatars WHERE id = :id LIMIT 1")
  suspend fun getAvatarById(id: Long): CoutureAvatarEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAvatar(avatar: CoutureAvatarEntity): Long

  @Update
  suspend fun updateAvatar(avatar: CoutureAvatarEntity)

  @Delete
  suspend fun deleteAvatar(avatar: CoutureAvatarEntity)

  @Query("DELETE FROM saved_avatars WHERE id = :id")
  suspend fun deleteAvatarById(id: Long)
}
