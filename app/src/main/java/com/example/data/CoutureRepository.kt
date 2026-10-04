package com.example.data

import com.example.model.AccessoryItem
import com.example.model.AvatarBackground
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.model.JacketColor
import com.example.model.LeoPattern
import kotlinx.coroutines.flow.Flow

class CoutureRepository(private val dao: CoutureAvatarDao) {
  val allAvatars: Flow<List<CoutureAvatarEntity>> = dao.getAllSavedAvatars()
  val favoriteAvatars: Flow<List<CoutureAvatarEntity>> = dao.getFavoriteAvatars()

  suspend fun saveAvatar(config: AvatarConfig, isFavorite: Boolean = false): Long {
    val entity = CoutureAvatarEntity.fromConfig(config, isFavorite)
    return dao.insertAvatar(entity)
  }

  suspend fun deleteAvatar(entity: CoutureAvatarEntity) {
    dao.deleteAvatar(entity)
  }

  suspend fun deleteAvatarById(id: Long) {
    dao.deleteAvatarById(id)
  }

  suspend fun toggleFavorite(entity: CoutureAvatarEntity) {
    val updated = entity.copy(isFavorite = !entity.isFavorite)
    dao.updateAvatar(updated)
  }

  companion object {
    val ICONIC_PRESETS = listOf(
      AvatarConfig(
        name = "Empress Roxie Leo",
        vipTitle = "Grand Duchess of Maximalist Glamour",
        baseEdition = BaseEdition.LEO_GLAM,
        leoPattern = LeoPattern.GOLD_LEO,
        jacketColor = JacketColor.NEON_PINK,
        hangingEars = true, // Hanging floppy ears
        selectedAccessories = listOf(
          AccessoryItem.JEWELED_TIARA.id,
          AccessoryItem.GOLD_CHAINS_PINK_DIAMONDS.id,
          AccessoryItem.STRASS_FOREHEAD_MAKEUP.id,
          AccessoryItem.PIERCINGS_PINK_DIAMONDS.id
        ),
        background = AvatarBackground.VIOLET_GLITTER
      ),
      AvatarConfig(
        name = "Panther Spike Rebel",
        vipTitle = "Punk-Rock Haute Canine",
        baseEdition = BaseEdition.PUNK_ROCK,
        leoPattern = LeoPattern.MIDNIGHT_BLACK_LEO,
        jacketColor = JacketColor.JET_REBEL_BLACK,
        hangingEars = true,
        selectedAccessories = listOf(
          AccessoryItem.LILA_MOHAWK_GLITTER.id,
          AccessoryItem.NEON_PINK_PATCHES.id,
          AccessoryItem.PIERCINGS_PINK_DIAMONDS.id,
          AccessoryItem.SPIKED_GOLD_COLLAR.id
        ),
        background = AvatarBackground.VIOLET_GLITTER
      ),
      AvatarConfig(
        name = "Bella Belladonna",
        vipTitle = "Bossy Chic Runway Icon",
        baseEdition = BaseEdition.BOSSY_CHIC,
        leoPattern = LeoPattern.ROSE_GOLD_LEO,
        jacketColor = JacketColor.LILA,
        hangingEars = true,
        selectedAccessories = listOf(
          AccessoryItem.LIGHT_PINK_BOW.id,
          AccessoryItem.VIOLET_SILK_SCARF.id,
          AccessoryItem.MINIMAL_GOLD_NECKLACE.id,
          AccessoryItem.STRASS_FOREHEAD_MAKEUP.id
        ),
        background = AvatarBackground.LILA_BOKEH
      )
    )
  }
}
