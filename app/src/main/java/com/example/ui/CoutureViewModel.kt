package com.example.ui

import android.app.Application
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.R
import com.example.data.CoutureAvatarEntity
import com.example.data.CoutureDatabase
import com.example.data.CoutureRepository
import com.example.model.AccessoryItem
import com.example.model.AvatarBackground
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.model.CanineVoiceStyle
import com.example.model.DesignerMonogram
import com.example.model.DogCoutureOutfit
import com.example.model.JacketColor
import com.example.model.LeoPattern
import com.example.model.OutfitFabric
import com.example.model.OutfitSilhouette
import com.example.model.OutfitTrim
import com.example.model.StoryboardScene
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.random.Random

enum class StudioTab(val title: String, val icon: String) {
  RUNWAY("Runway", "👑"),
  HEY_DOG("HeyDog Studio", "🎬"),
  OUTFIT_DESIGNER("Outfit Atelier", "🪡"),
  LEO_PATTERNS("Leo Fur", "🐆"),
  JACKET_COLORS("Jackets", "🧥"),
  ACCESSORIES("Bling Lab", "💎"),
  BACKGROUNDS("Backdrops", "✨"),
  LOOKBOOK("Lookbook", "📸")
}

val DefaultDubaiStoryboardScenes = listOf(
  StoryboardScene(
    id = "burj_khalifa_night",
    title = "Burj Khalifa at Night",
    location = "Downtown Dubai & Fountain Laser Show",
    scriptPrompt = "Good evening Dubai! Live from the Burj Khalifa sky lounge. Draped in 24-carat gold and neon pink diamonds! Still rich, never sleeping, more is more, darling!",
    backgroundRes = R.drawable.img_dubai_burj,
    defaultVoice = CanineVoiceStyle.SASSY_DIVA,
    captions = listOf(
      "BURJ KHALIFA MIDNIGHT 🏙️",
      "24K GOLD & NEON PINK 💎",
      "NO SLEEP STILL RICH IN DUBAI! ✨",
      "MORE IS MORE, DARLING! 👑"
    ),
    luxuryBadge = "DUBAI NIGHTS VIP"
  ),
  StoryboardScene(
    id = "dubai_yacht",
    title = "Dubai Marina Mega Yacht",
    location = "Arabian Gulf VIP Anchorage",
    scriptPrompt = "Sunset on our private superyacht cruising Dubai Marina! Rose gold skies, champagne on ice, and golden leopard fur shining in the warm breeze. Pure canine haute couture!",
    backgroundRes = R.drawable.img_dubai_yacht,
    defaultVoice = CanineVoiceStyle.ROYAL_DUCHESS,
    captions = listOf(
      "DUBAI SUPERYACHT SUNSET ⛵",
      "ROSE GOLD SKY & CHAMPAGNE 🥂",
      "LEOPARD COUTURE ON DECK 🐆",
      "LIVING THE CANINE DREAM 🌟"
    ),
    luxuryBadge = "MARINA MEGA YACHT"
  ),
  StoryboardScene(
    id = "dubai_jet",
    title = "Gulfstream Couture Private Jet",
    location = "Cruising at 45,000 Feet to Dubai",
    scriptPrompt = "Welcome aboard my private jet! Direct flight to Dubai. Quilted cream leather seats, rose gold fixtures, and first class luxury only for elite dogs. Wheels up!",
    backgroundRes = R.drawable.img_dubai_jet,
    defaultVoice = CanineVoiceStyle.BILLIONAIRE_BOSS,
    captions = listOf(
      "GULFSTREAM G700 PRIVATE JET ✈️",
      "CREAM LEATHER & ROSE GOLD 💫",
      "FIRST CLASS CANINE VIP 🐾",
      "NEXT STOP: DUBAI RUNWAY! 💖"
    ),
    luxuryBadge = "SKYLINE PRIVATE JET"
  ),
  StoryboardScene(
    id = "grand_opening",
    title = "Flagship Boutique Grand Opening",
    location = "CGE Haute Couture Dog Fashion Avenue",
    scriptPrompt = "Big news everybody! Today our luxury dog fashion boutique has officially opened! Come grab your bespoke moto jackets, diamond collars, and runway outfits right now!",
    backgroundRes = R.drawable.img_grand_opening_outfit,
    defaultVoice = CanineVoiceStyle.PLAYFUL_VIP,
    captions = listOf(
      "GRAND OPENING TODAY! 🎉",
      "HAUTE COUTURE DOG FASHION 🪡",
      "BESPOKE JACKETS & TIARAS 👑",
      "SHOP IS OFFICIALLY OPEN! ✨"
    ),
    luxuryBadge = "GRAND OPENING ATELIER"
  )
)

class CoutureViewModel(application: Application) : AndroidViewModel(application) {
  private val repository: CoutureRepository

  // Android Native Text-to-Speech Engine for HeyDog Video
  private var textToSpeech: TextToSpeech? = null
  private var isTtsReady = false

  init {
    val db = CoutureDatabase.getDatabase(application)
    repository = CoutureRepository(db.coutureAvatarDao())

    textToSpeech = TextToSpeech(application) { status ->
      if (status == TextToSpeech.SUCCESS) {
        textToSpeech?.language = Locale.ENGLISH
        isTtsReady = true
      }
    }
  }

  val savedAvatars: StateFlow<List<CoutureAvatarEntity>> = repository.allAvatars
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  private val _activeConfig = MutableStateFlow(
    AvatarConfig(
      name = "Cleo Rich Panther",
      vipTitle = "Grand Duchess of Still Rich Dogs",
      baseEdition = BaseEdition.LEO_GLAM,
      leoPattern = LeoPattern.GOLD_LEO,
      jacketColor = JacketColor.NEON_PINK,
      hangingEars = true, // Authentic floppy hanging ears
      selectedAccessories = listOf(
        AccessoryItem.JEWELED_TIARA.id,
        AccessoryItem.GOLD_CHAINS_PINK_DIAMONDS.id,
        AccessoryItem.PIERCINGS_PINK_DIAMONDS.id,
        AccessoryItem.MINIMAL_GOLD_NECKLACE.id
      ),
      background = AvatarBackground.BURJ_KHALIFA_NIGHT,
      customOutfit = DogCoutureOutfit(
        id = "grand_opening_moto",
        name = "Grand Opening Gold Ribbon Moto",
        silhouette = OutfitSilhouette.MOTO_BIKER,
        fabric = OutfitFabric.PATENT_LEATHER,
        trim = OutfitTrim.GOLD_BULLION,
        primaryColorHex = 0xFFFF1493,
        secondaryColorHex = 0xFFFFD700,
        monogram = DesignerMonogram.GRAND_OPENING,
        isGrandOpeningExclusive = true
      )
    )
  )
  val activeConfig: StateFlow<AvatarConfig> = _activeConfig.asStateFlow()

  private val _activeTab = MutableStateFlow(StudioTab.RUNWAY)
  val activeTab: StateFlow<StudioTab> = _activeTab.asStateFlow()

  private val _snackbarMessage = MutableStateFlow<String?>(null)
  val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

  // --- LUXURY OUTFIT & ICON DESIGNER STATE ---
  private val _outfitDraft = MutableStateFlow(
    DogCoutureOutfit(
      id = "boutique_opening_custom",
      name = "Grand Opening Flagship Moto",
      silhouette = OutfitSilhouette.MOTO_BIKER,
      fabric = OutfitFabric.PATENT_LEATHER,
      trim = OutfitTrim.GOLD_BULLION,
      primaryColorHex = 0xFFFF1493, // Neon Pink
      secondaryColorHex = 0xFFFFD700, // 24k Gold
      monogram = DesignerMonogram.GRAND_OPENING,
      isGrandOpeningExclusive = true
    )
  )
  val outfitDraft: StateFlow<DogCoutureOutfit> = _outfitDraft.asStateFlow()

  private val _grandOpeningCelebrationActive = MutableStateFlow(true)
  val grandOpeningCelebrationActive: StateFlow<Boolean> = _grandOpeningCelebrationActive.asStateFlow()

  // --- HEYDOG AI TALKING AVATAR VIDEO STUDIO STATE ---
  val storyboardScenes = DefaultDubaiStoryboardScenes

  private val _selectedScene = MutableStateFlow(DefaultDubaiStoryboardScenes[0])
  val selectedScene: StateFlow<StoryboardScene> = _selectedScene.asStateFlow()

  private val _selectedVoice = MutableStateFlow(CanineVoiceStyle.SASSY_DIVA)
  val selectedVoice: StateFlow<CanineVoiceStyle> = _selectedVoice.asStateFlow()

  private val _scriptText = MutableStateFlow(DefaultDubaiStoryboardScenes[0].scriptPrompt)
  val scriptText: StateFlow<String> = _scriptText.asStateFlow()

  private val _isPlayingVideo = MutableStateFlow(false)
  val isPlayingVideo: StateFlow<Boolean> = _isPlayingVideo.asStateFlow()

  private val _speechAmplitude = MutableStateFlow(0f)
  val speechAmplitude: StateFlow<Float> = _speechAmplitude.asStateFlow()

  private val _currentCaption = MutableStateFlow(DefaultDubaiStoryboardScenes[0].captions.firstOrNull() ?: "")
  val currentCaption: StateFlow<String> = _currentCaption.asStateFlow()

  private val _videoProgress = MutableStateFlow(0f)
  val videoProgress: StateFlow<Float> = _videoProgress.asStateFlow()

  private var videoPlayJob: Job? = null

  fun setTab(tab: StudioTab) {
    _activeTab.value = tab
  }

  fun clearSnackbar() {
    _snackbarMessage.value = null
  }

  fun setBaseEdition(edition: BaseEdition) {
    _activeConfig.value = _activeConfig.value.copy(baseEdition = edition)
  }

  fun setLeoPattern(leo: LeoPattern) {
    _activeConfig.value = _activeConfig.value.copy(leoPattern = leo)
  }

  fun setJacketColor(jacket: JacketColor) {
    _activeConfig.value = _activeConfig.value.copy(jacketColor = jacket)
  }

  fun toggleHangingEars() {
    val current = _activeConfig.value.hangingEars
    _activeConfig.value = _activeConfig.value.copy(hangingEars = !current)
  }

  fun setHangingEars(enabled: Boolean) {
    _activeConfig.value = _activeConfig.value.copy(hangingEars = enabled)
  }

  // --- ACCESSORY CUSTOMIZATION & ARRANGEMENT ---
  fun toggleAccessory(accessoryId: String) {
    val list = _activeConfig.value.selectedAccessories.toMutableList()
    if (list.contains(accessoryId)) {
      list.remove(accessoryId)
    } else {
      list.add(accessoryId)
    }
    _activeConfig.value = _activeConfig.value.copy(selectedAccessories = list)
  }

  fun moveAccessoryUp(index: Int) {
    val list = _activeConfig.value.selectedAccessories.toMutableList()
    if (index > 0 && index < list.size) {
      val item = list.removeAt(index)
      list.add(index - 1, item)
      _activeConfig.value = _activeConfig.value.copy(selectedAccessories = list)
    }
  }

  fun moveAccessoryDown(index: Int) {
    val list = _activeConfig.value.selectedAccessories.toMutableList()
    if (index >= 0 && index < list.size - 1) {
      val item = list.removeAt(index)
      list.add(index + 1, item)
      _activeConfig.value = _activeConfig.value.copy(selectedAccessories = list)
    }
  }

  fun removeAccessory(accessoryId: String) {
    val list = _activeConfig.value.selectedAccessories.toMutableList()
    list.remove(accessoryId)
    _activeConfig.value = _activeConfig.value.copy(selectedAccessories = list)
  }

  fun clearAllAccessories() {
    _activeConfig.value = _activeConfig.value.copy(selectedAccessories = emptyList())
    _snackbarMessage.value = "Cleared all accessories"
  }

  fun equipMaximalistSet() {
    _activeConfig.value = _activeConfig.value.copy(
      selectedAccessories = listOf(
        AccessoryItem.JEWELED_TIARA.id,
        AccessoryItem.OVERSIZED_SUNGLASSES.id,
        AccessoryItem.GOLD_CHAINS_PINK_DIAMONDS.id,
        AccessoryItem.MINIMAL_GOLD_NECKLACE.id,
        AccessoryItem.DESIGNER_DOG_COLLAR.id,
        AccessoryItem.PIERCINGS_PINK_DIAMONDS.id,
        AccessoryItem.STRASS_FOREHEAD_MAKEUP.id
      )
    )
    _snackbarMessage.value = "Equipped 'Pure Diamond Bling' Set!"
  }

  fun equipRebelPunkSet() {
    _activeConfig.value = _activeConfig.value.copy(
      selectedAccessories = listOf(
        AccessoryItem.LILA_MOHAWK_GLITTER.id,
        AccessoryItem.PIERCINGS_PINK_DIAMONDS.id,
        AccessoryItem.SPIKED_GOLD_COLLAR.id,
        AccessoryItem.NEON_PINK_PATCHES.id,
        AccessoryItem.GOLD_FANG_GRILL.id
      )
    )
    _snackbarMessage.value = "Equipped 'Rebel Punk Rockstar' Set!"
  }

  fun equipSlenderChicSet() {
    _activeConfig.value = _activeConfig.value.copy(
      selectedAccessories = listOf(
        AccessoryItem.LIGHT_PINK_BOW.id,
        AccessoryItem.VIOLET_SILK_SCARF.id,
        AccessoryItem.MINIMAL_GOLD_NECKLACE.id,
        AccessoryItem.STRASS_FOREHEAD_MAKEUP.id,
        AccessoryItem.DIAMOND_NOSE_STUD.id
      )
    )
    _snackbarMessage.value = "Equipped 'Bossy Slender Chic' Set!"
  }

  // --- OUTFIT DESIGNER & ICON STUDIO ACTIONS ---
  fun setOutfitSilhouette(silhouette: OutfitSilhouette) {
    _outfitDraft.value = _outfitDraft.value.copy(silhouette = silhouette)
  }

  fun setOutfitFabric(fabric: OutfitFabric) {
    _outfitDraft.value = _outfitDraft.value.copy(fabric = fabric)
  }

  fun setOutfitTrim(trim: OutfitTrim) {
    _outfitDraft.value = _outfitDraft.value.copy(trim = trim)
  }

  fun setOutfitColors(primaryHex: Long, secondaryHex: Long) {
    _outfitDraft.value = _outfitDraft.value.copy(
      primaryColorHex = primaryHex,
      secondaryColorHex = secondaryHex
    )
  }

  fun setOutfitMonogram(monogram: DesignerMonogram) {
    _outfitDraft.value = _outfitDraft.value.copy(monogram = monogram)
  }

  fun setOutfitName(name: String) {
    _outfitDraft.value = _outfitDraft.value.copy(name = name)
  }

  fun loadPredefinedOutfit(outfit: DogCoutureOutfit) {
    _outfitDraft.value = outfit
    _snackbarMessage.value = "Loaded Capsule: ${outfit.name}"
  }

  fun applyDraftToAvatar() {
    val draft = _outfitDraft.value
    _activeConfig.value = _activeConfig.value.copy(customOutfit = draft)
    _activeTab.value = StudioTab.RUNWAY
    _snackbarMessage.value = "Applied '${draft.name}' to Avatar Runway!"
  }

  fun toggleCelebrationConfetti() {
    _grandOpeningCelebrationActive.value = !_grandOpeningCelebrationActive.value
  }

  // --- HEYDOG AI VIDEO STUDIO (HEYGEN DOUBLE FOR DOGS) METHODS ---
  fun selectStoryboardScene(scene: StoryboardScene) {
    pauseVideo()
    _selectedScene.value = scene
    _scriptText.value = scene.scriptPrompt
    _selectedVoice.value = scene.defaultVoice
    _currentCaption.value = scene.captions.firstOrNull() ?: ""

    // Also update avatar background to match the chosen storyboard location
    when (scene.id) {
      "burj_khalifa_night" -> setBackground(AvatarBackground.BURJ_KHALIFA_NIGHT)
      "dubai_yacht" -> setBackground(AvatarBackground.DUBAI_YACHT)
      "dubai_jet" -> setBackground(AvatarBackground.DUBAI_JET)
      "grand_opening" -> setBackground(AvatarBackground.SHOP_INTERIOR)
    }
  }

  fun setScriptText(text: String) {
    _scriptText.value = text
  }

  fun setVoiceStyle(voice: CanineVoiceStyle) {
    _selectedVoice.value = voice
    if (isTtsReady) {
      textToSpeech?.setPitch(voice.pitch)
      textToSpeech?.setSpeechRate(voice.speechRate)
    }
  }

  fun playVideo() {
    if (_isPlayingVideo.value) return

    val textToSpeak = _scriptText.value
    val scene = _selectedScene.value
    val voice = _selectedVoice.value

    _isPlayingVideo.value = true

    // Configure and speak with native TextToSpeech
    if (isTtsReady && textToSpeech != null) {
      textToSpeech?.setPitch(voice.pitch)
      textToSpeech?.setSpeechRate(voice.speechRate)
      textToSpeech?.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "HeyDogSpeechId")
    }

    videoPlayJob?.cancel()
    videoPlayJob = viewModelScope.launch {
      val totalSteps = 100
      val stepTimeMs = 80L
      val captions = scene.captions.ifEmpty { listOf(scene.title) }

      for (i in 0..totalSteps) {
        if (!isActive) break
        _videoProgress.value = i / 100f

        // Animate lip-sync amplitude rhythmically while speaking
        val waveBase = kotlin.math.sin(i * 0.45).toFloat()
        val waveRandom = Random.nextFloat() * 0.4f
        _speechAmplitude.value = kotlin.math.max(0.15f, (waveBase * 0.5f + 0.5f + waveRandom).coerceIn(0f, 1f))

        // Rotate captions dynamically
        val captionIndex = ((i.toFloat() / totalSteps.toFloat()) * captions.size).toInt().coerceIn(0, captions.size - 1)
        _currentCaption.value = captions[captionIndex]

        delay(stepTimeMs)
      }

      // Finished playback
      _isPlayingVideo.value = false
      _speechAmplitude.value = 0f
      _videoProgress.value = 1f
    }
  }

  fun pauseVideo() {
    _isPlayingVideo.value = false
    _speechAmplitude.value = 0f
    videoPlayJob?.cancel()
    if (isTtsReady) {
      textToSpeech?.stop()
    }
  }

  fun setBackground(bg: AvatarBackground) {
    _activeConfig.value = _activeConfig.value.copy(background = bg)
  }

  fun setName(name: String) {
    _activeConfig.value = _activeConfig.value.copy(name = name)
  }

  fun setVipTitle(title: String) {
    _activeConfig.value = _activeConfig.value.copy(vipTitle = title)
  }

  fun applyPreset(preset: AvatarConfig) {
    _activeConfig.value = preset
    _snackbarMessage.value = "Applied ${preset.name} Couture Look!"
  }

  fun randomizeHauteCouture() {
    val allEditions = BaseEdition.values()
    val allLeos = LeoPattern.values()
    val allJackets = JacketColor.values()
    val allBgs = AvatarBackground.values()
    val allAccessories = AccessoryItem.values()

    val randomEdition = allEditions.random()
    val randomLeo = allLeos.random()
    val randomJacket = allJackets.random()
    val randomBg = allBgs.random()
    val randomHangingEars = Random.nextBoolean()

    // Pick 3 to 6 accessories randomly
    val accCount = Random.nextInt(3, 7)
    val randomAcc = allAccessories.toList().shuffled().take(accCount).map { it.id }

    val coutureNames = listOf(
      "Baroness Valentina", "King Diablo Gold", "Princess Cleo Velvet",
      "Duchess Roxy Glam", "Lord Ziggy Stardust", "Siren Sapphire",
      "Empress Maya Panther", "Sir Versace Paws"
    )
    val coutureTitles = listOf(
      "No Sleep Still Rich Icon", "Haute Couture Superstar", "CGE Runway Sensation",
      "Maximalist VIP Icon", "Paris Fashion Week Winner", "Diamond Canine Empress"
    )

    _activeConfig.value = AvatarConfig(
      name = coutureNames.random(),
      vipTitle = coutureTitles.random(),
      baseEdition = randomEdition,
      leoPattern = randomLeo,
      jacketColor = randomJacket,
      hangingEars = randomHangingEars,
      selectedAccessories = randomAcc,
      background = randomBg
    )
    _snackbarMessage.value = "Generated Haute Couture Surprise!"
  }

  fun saveCurrentLook() {
    viewModelScope.launch {
      val config = _activeConfig.value
      repository.saveAvatar(config)
      _snackbarMessage.value = "Saved '${config.name}' to VIP Lookbook!"
    }
  }

  fun deleteAvatar(entity: CoutureAvatarEntity) {
    viewModelScope.launch {
      repository.deleteAvatar(entity)
      _snackbarMessage.value = "Removed '${entity.name}' from Lookbook."
    }
  }

  fun toggleFavorite(entity: CoutureAvatarEntity) {
    viewModelScope.launch {
      repository.toggleFavorite(entity)
    }
  }

  fun loadSavedAvatar(entity: CoutureAvatarEntity) {
    _activeConfig.value = entity.toAvatarConfig()
    _activeTab.value = StudioTab.RUNWAY
    _snackbarMessage.value = "Loaded '${entity.name}' into Studio!"
  }

  override fun onCleared() {
    super.onCleared()
    textToSpeech?.stop()
    textToSpeech?.shutdown()
  }
}
