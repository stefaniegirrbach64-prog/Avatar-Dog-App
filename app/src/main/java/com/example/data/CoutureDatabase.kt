package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [CoutureAvatarEntity::class],
  version = 1,
  exportSchema = false
)
abstract class CoutureDatabase : RoomDatabase() {
  abstract fun coutureAvatarDao(): CoutureAvatarDao

  companion object {
    @Volatile
    private var INSTANCE: CoutureDatabase? = null

    fun getDatabase(context: Context): CoutureDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CoutureDatabase::class.java,
          "cge_couture_dogs.db"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        instance
      }
    }
  }
}
