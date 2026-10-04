package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.AvatarBackground
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.model.JacketColor
import com.example.model.LeoPattern

@Entity(tableName = "saved_avatars")
data class CoutureAvatarEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val name: String,
  val vipTitle: String,
  val baseEditionId: String,
  val leoPatternId: String,
  val jacketColorId: String,
  val hangingEars: Boolean,
  val accessoriesCsv: String,
  val backgroundId: String,
  val createdAt: Long = System.currentTimeMillis(),
  val isFavorite: Boolean = false
) {
  fun toAvatarConfig(): AvatarConfig {
    val edition = BaseEdition.values().firstOrNull { it.id == baseEditionId } ?: BaseEdition.LEO_GLAM
    val leo = LeoPattern.values().firstOrNull { it.id == leoPatternId } ?: LeoPattern.GOLD_LEO
    val jacket = JacketColor.values().firstOrNull { it.id == jacketColorId } ?: JacketColor.NEON_PINK
    val bg = AvatarBackground.values().firstOrNull { it.id == backgroundId } ?: AvatarBackground.VIOLET_GLITTER
    val accList = if (accessoriesCsv.isBlank()) emptyList() else accessoriesCsv.split(",").filter { it.isNotBlank() }

    return AvatarConfig(
      name = name,
      vipTitle = vipTitle,
      baseEdition = edition,
      leoPattern = leo,
      jacketColor = jacket,
      hangingEars = hangingEars,
      selectedAccessories = accList,
      background = bg
    )
  }

  companion object {
    fun fromConfig(config: AvatarConfig, isFavorite: Boolean = false): CoutureAvatarEntity {
      return CoutureAvatarEntity(
        name = config.name,
        vipTitle = config.vipTitle,
        baseEditionId = config.baseEdition.id,
        leoPatternId = config.leoPattern.id,
        jacketColorId = config.jacketColor.id,
        hangingEars = config.hangingEars,
        accessoriesCsv = config.selectedAccessories.joinToString(","),
        backgroundId = config.background.id,
        isFavorite = isFavorite
      )
    }
  }
}
