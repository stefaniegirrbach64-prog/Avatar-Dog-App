package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AvatarConfig
import com.example.model.CanineVoiceStyle
import com.example.model.StoryboardScene
import com.example.ui.CoutureViewModel
import com.example.ui.components.CoutureAvatarRenderer
import com.example.ui.theme.CardBorderGold
import com.example.ui.theme.CardSurface
import com.example.ui.theme.CardSurfaceElevated
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.HotNeonPink
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.JoyfulVioletBg
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.RoseGold
import com.example.ui.theme.RoseGoldGlow
import com.example.ui.theme.SunsetRoseGold
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet
import com.example.ui.theme.WarmGold

@Composable
fun HeyDogStudioScreen(
  viewModel: CoutureViewModel,
  activeConfig: AvatarConfig,
  modifier: Modifier = Modifier
) {
  val selectedScene by viewModel.selectedScene.collectAsState()
  val selectedVoice by viewModel.selectedVoice.collectAsState()
  val scriptText by viewModel.scriptText.collectAsState()
  val isPlaying by viewModel.isPlayingVideo.collectAsState()
  val speechAmplitude by viewModel.speechAmplitude.collectAsState()
  val currentCaption by viewModel.currentCaption.collectAsState()
  val videoProgress by viewModel.videoProgress.collectAsState()

  var isReelVerticalMode by remember { mutableStateOf(false) }
  var showExportDialog by remember { mutableStateOf(false) }

  // Animated shimmer pulse for active video playback
  val infiniteTransition = rememberInfiniteTransition(label = "HeyDogPulse")
  val pulseRing by infiniteTransition.animateFloat(
    initialValue = 0.85f,
    targetValue = 1.15f,
    animationSpec = infiniteRepeatable(
      animation = tween(800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "RingPulse"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 6.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    contentPadding = PaddingValues(bottom = 28.dp)
  ) {
    // 1. BRAND HERO HEADER: "MORE IS MORE" HEYDOG AI VIDEO STUDIO
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .shadow(12.dp, RoundedCornerShape(20.dp), ambientColor = ImperialGold, spotColor = NeonPink),
        colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.8.dp,
          Brush.horizontalGradient(listOf(ImperialGold, NeonPink, RoseGold, Violet, ImperialGold))
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  JoyfulVioletBg,
                  Color(0xFF53147E),
                  Color(0xFF3F0D63)
                )
              )
            )
            .padding(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = DeepVioletSurface,
              shape = RoundedCornerShape(8.dp),
              border = androidx.compose.foundation.BorderStroke(1.2.dp, ImperialGold)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Text(text = "🎬", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "HEYDOG AI TALKING VIDEO STUDIO",
                  color = ImperialGold,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.ExtraBold,
                  letterSpacing = 1.sp
                )
              }
            }

            // Aspect Ratio toggle: Reel 9:16 vs Wide
            Row(verticalAlignment = Alignment.CenterVertically) {
              FilterChip(
                selected = !isReelVerticalMode,
                onClick = { isReelVerticalMode = false },
                label = { Text("Cinema", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = NeonPink,
                  selectedLabelColor = Color.White
                )
              )
              Spacer(modifier = Modifier.width(4.dp))
              FilterChip(
                selected = isReelVerticalMode,
                onClick = { isReelVerticalMode = true },
                label = { Text("Reel 9:16", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = ImperialGold,
                  selectedLabelColor = Color.Black
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Dubai Storyboards & Canine AI Talking Avatar",
            color = TextHighLuxury,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
          )
          Text(
            text = "HeyGen double engineered exclusively for dogs! Voiceover, real-time mouth sync & Dubai storyboards.",
            color = TextMutedPink,
            fontSize = 11.sp,
            lineHeight = 14.sp
          )
        }
      }
    }

    // 2. VIDEO PLAYER & TALKING CANINE AVATAR STAGE
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(22.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.8.dp,
          Brush.horizontalGradient(listOf(RoseGold, ImperialGold, NeonPink))
        )
      ) {
        Column(
          modifier = Modifier.padding(12.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Top Stage Info
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = selectedScene.title.uppercase(),
                color = ImperialGold,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.8.sp
              )
              Text(
                text = "${selectedScene.location} • ${selectedVoice.displayName}",
                color = Lila,
                fontSize = 10.sp
              )
            }

            Surface(
              color = DeepVioletSurface,
              shape = RoundedCornerShape(12.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, RoseGold)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.VolumeUp,
                  contentDescription = null,
                  tint = RoseGoldGlow,
                  modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = if (isPlaying) "TALKING LIVE" else "READY",
                  color = if (isPlaying) NeonPink else RoseGold,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Video Viewport Box (Aspect Ratio changes based on Cinema / Reel)
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .aspectRatio(if (isReelVerticalMode) 0.85f else 1.35f)
              .clip(RoundedCornerShape(18.dp))
              .border(
                2.dp,
                Brush.linearGradient(listOf(ImperialGold, NeonPink, RoseGold, ImperialGold)),
                RoundedCornerShape(18.dp)
              )
          ) {
            // Scene Background Image
            Image(
              painter = painterResource(id = selectedScene.backgroundRes),
              contentDescription = selectedScene.title,
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )

            // Dynamic Lighting Scrim
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    listOf(
                      Color(0x3310021F),
                      Color(0x55350A58),
                      Color(0xCC200538)
                    )
                  )
                )
            )

            // Centered Talking Dog Avatar Layer
            Box(
              modifier = Modifier
                .fillMaxWidth(if (isReelVerticalMode) 0.82f else 0.58f)
                .aspectRatio(1f)
                .align(Alignment.Center)
                .padding(bottom = 14.dp)
            ) {
              // Avatar portrait
              Image(
                painter = painterResource(id = activeConfig.baseEdition.imageRes),
                contentDescription = "Talking Canine Avatar",
                modifier = Modifier
                  .fillMaxSize()
                  .clip(CircleShape)
                  .border(
                    width = if (isPlaying) (3.5f * pulseRing).dp else 2.dp,
                    brush = Brush.radialGradient(listOf(ImperialGold, NeonPink, RoseGold)),
                    shape = CircleShape
                  ),
                contentScale = ContentScale.Crop
              )

              // MOUTH SYNC & TALKING JAW OVERLAY (HEYGEN LIP SYNC ANIMATOR)
              if (isPlaying) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                  val w = size.width
                  val h = size.height
                  val mouthCenter = Offset(w * 0.5f, h * 0.62f)

                  // Animated talking mouth opening based on speechAmplitude
                  val mouthHeight = 8f + (speechAmplitude * 18f)
                  val mouthWidth = 16f + (speechAmplitude * 12f)

                  drawOval(
                    color = Color(0xEE2A0A1F),
                    topLeft = Offset(mouthCenter.x - mouthWidth / 2f, mouthCenter.y - mouthHeight / 2f),
                    size = androidx.compose.ui.geometry.Size(mouthWidth, mouthHeight)
                  )
                  drawOval(
                    color = NeonPink.copy(alpha = 0.85f),
                    topLeft = Offset(mouthCenter.x - (mouthWidth * 0.6f) / 2f, mouthCenter.y - (mouthHeight * 0.4f) / 2f),
                    size = androidx.compose.ui.geometry.Size(mouthWidth * 0.6f, mouthHeight * 0.4f)
                  )

                  // Diamond glint sparkles while dog speaks
                  for (i in 0..2) {
                    val angle = (i * 120f) * (3.14159f / 180f)
                    val dist = 32f + (speechAmplitude * 20f)
                    drawCircle(
                      color = ImperialGold,
                      radius = 2.5f,
                      center = Offset(
                        mouthCenter.x + kotlin.math.cos(angle) * dist,
                        mouthCenter.y + kotlin.math.sin(angle) * dist
                      )
                    )
                  }
                }
              }
            }

            // Top VIP Location Badge Stamp
            Surface(
              color = Color(0xEE28074A),
              shape = RoundedCornerShape(8.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
              modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Text(text = "👑", fontSize = 11.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = selectedScene.luxuryBadge,
                  color = ImperialGold,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.ExtraBold,
                  letterSpacing = 0.5.sp
                )
              }
            }

            // Top-right MORE IS MORE Brand Tag
            Surface(
              color = Color(0xDD3E0E68),
              shape = RoundedCornerShape(8.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink),
              modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
            ) {
              Text(
                text = "MORE IS MORE ✨",
                color = HotNeonPink,
                fontSize = 9.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
              )
            }

            // Live Audio Waveform Visualizer on stage
            if (isPlaying) {
              Canvas(
                modifier = Modifier
                  .fillMaxWidth()
                  .height(30.dp)
                  .align(Alignment.BottomCenter)
                  .padding(bottom = 36.dp, start = 20.dp, end = 20.dp)
              ) {
                val step = size.width / 24f
                for (i in 0..23) {
                  val barAmp = ((kotlin.math.sin((i * 0.6f) + (speechAmplitude * 6.28f)) + 1f) / 2f) * speechAmplitude
                  val barHeight = 6f + (barAmp * 22f)
                  val barX = i * step + step / 2f
                  val barColor = when (i % 3) {
                    0 -> ImperialGold
                    1 -> NeonPink
                    else -> RoseGold
                  }
                  drawLine(
                    color = barColor,
                    start = Offset(barX, size.height / 2f - barHeight / 2f),
                    end = Offset(barX, size.height / 2f + barHeight / 2f),
                    strokeWidth = 3.5f,
                    cap = StrokeCap.Round
                  )
                }
              }
            }

            // Subtitle / Teleprompter Live Highlight Bar
            Surface(
              color = Color(0xF018042D),
              shape = RoundedCornerShape(12.dp),
              border = androidx.compose.foundation.BorderStroke(1.2.dp, NeonPink),
              modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = "💬", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (isPlaying && currentCaption.isNotEmpty()) currentCaption else selectedScene.title,
                  color = WarmGold,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 0.5.sp,
                  maxLines = 1,
                  modifier = Modifier.weight(1f)
                )
                if (isPlaying) {
                  Surface(
                    color = NeonPink,
                    shape = CircleShape,
                    modifier = Modifier.size(8.dp)
                  ) {}
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Video Progress Bar
          LinearProgressIndicator(
            progress = { videoProgress },
            modifier = Modifier
              .fillMaxWidth()
              .height(4.dp)
              .clip(RoundedCornerShape(2.dp)),
            color = ImperialGold,
            trackColor = DeepVioletSurface
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Player Buttons (Play / Pause / Replay / Export Reel)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              // Main Big Play/Pause
              Button(
                onClick = {
                  if (isPlaying) viewModel.pauseVideo() else viewModel.playVideo()
                },
                modifier = Modifier
                  .height(48.dp)
                  .testTag("heydog_play_pause_button"),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isPlaying) SunsetRoseGold else NeonPink
                ),
                shape = RoundedCornerShape(14.dp)
              ) {
                Icon(
                  imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                  contentDescription = if (isPlaying) "Pause" else "Play",
                  tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (isPlaying) "Pause Talking" else "Make Dog Talk",
                  color = Color.White,
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 13.sp
                )
              }

              Spacer(modifier = Modifier.width(8.dp))

              IconButton(
                onClick = {
                  viewModel.pauseVideo()
                  viewModel.playVideo()
                },
                modifier = Modifier.size(42.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Replay,
                  contentDescription = "Replay",
                  tint = ImperialGold
                )
              }
            }

            Button(
              onClick = { showExportDialog = true },
              modifier = Modifier
                .height(44.dp)
                .testTag("export_canine_reel_button"),
              colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
              border = androidx.compose.foundation.BorderStroke(1.2.dp, RoseGold),
              shape = RoundedCornerShape(12.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Videocam,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = RoseGoldGlow
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text("Export Reel", color = RoseGoldGlow, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }
          }
        }
      }
    }

    // 3. STORYBOARD SCENE SELECTOR (DUBAI YACHT, PRIVATE JET, BURJ KHALIFA, GRAND OPENING)
    item {
      Column {
        Text(
          text = "CHOOSE STORYBOARD LOCATION",
          color = ImperialGold,
          fontSize = 11.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          modifier = Modifier.padding(bottom = 6.dp)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          items(viewModel.storyboardScenes) { scene ->
            val isSelected = selectedScene.id == scene.id
            Card(
              modifier = Modifier
                .width(200.dp)
                .clickable { viewModel.selectStoryboardScene(scene) }
                .testTag("scene_card_${scene.id}"),
              colors = CardDefaults.cardColors(
                containerColor = if (isSelected) CardSurfaceElevated else CardSurface
              ),
              shape = RoundedCornerShape(16.dp),
              border = androidx.compose.foundation.BorderStroke(
                if (isSelected) 2.dp else 1.dp,
                if (isSelected) ImperialGold else Lila.copy(alpha = 0.35f)
              )
            ) {
              Column {
                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                ) {
                  Image(
                    painter = painterResource(id = scene.backgroundRes),
                    contentDescription = scene.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                  )
                  Box(
                    modifier = Modifier
                      .fillMaxSize()
                      .background(
                        Brush.verticalGradient(
                          listOf(Color.Transparent, Color(0xCC200636))
                        )
                      )
                  )
                  Surface(
                    color = Color(0xDD350958),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                      .align(Alignment.TopStart)
                      .padding(6.dp)
                  ) {
                    Text(
                      text = scene.luxuryBadge,
                      color = ImperialGold,
                      fontSize = 8.sp,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                  }
                }

                Column(modifier = Modifier.padding(8.dp)) {
                  Text(
                    text = scene.title,
                    color = TextHighLuxury,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                  )
                  Text(
                    text = scene.location,
                    color = TextMutedPink,
                    fontSize = 10.sp,
                    maxLines = 1
                  )
                }
              }
            }
          }
        }
      }
    }

    // 4. CANINE VOICE STYLE SELECTOR
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, RoseGold.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "CANINE AI VOICE STYLE",
              color = ImperialGold,
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 0.8.sp
            )
            Text(
              text = "Text-to-Speech Engine",
              color = RoseGold,
              fontSize = 10.sp
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(CanineVoiceStyle.values()) { voice ->
              val isSelected = selectedVoice == voice
              Card(
                modifier = Modifier
                  .width(145.dp)
                  .clickable { viewModel.setVoiceStyle(voice) },
                colors = CardDefaults.cardColors(
                  containerColor = if (isSelected) DeepVioletSurface else CardSurface
                ),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                  1.dp,
                  if (isSelected) NeonPink else Lila.copy(alpha = 0.3f)
                )
              ) {
                Column(modifier = Modifier.padding(8.dp)) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = voice.emoji, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = voice.displayName,
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isSelected) ImperialGold else TextHighLuxury
                    )
                  }
                  Spacer(modifier = Modifier.height(3.dp))
                  Text(
                    text = voice.description,
                    fontSize = 9.sp,
                    color = TextMutedPink,
                    lineHeight = 11.sp,
                    maxLines = 2
                  )
                }
              }
            }
          }
        }
      }
    }

    // 5. SCRIPT & TELEPROMPTER EDITOR
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.35f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "CANINE SPEECH SCRIPT",
              color = ImperialGold,
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 0.8.sp
            )
            TextButton(
              onClick = {
                viewModel.setScriptText(selectedScene.scriptPrompt)
              }
            ) {
              Text("Reset Script", color = RoseGold, fontSize = 10.sp)
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          OutlinedTextField(
            value = scriptText,
            onValueChange = { viewModel.setScriptText(it) },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("heydog_script_input"),
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = DeepVioletSurface,
              unfocusedContainerColor = DeepVioletSurface,
              focusedTextColor = TextHighLuxury,
              unfocusedTextColor = TextHighLuxury,
              focusedBorderColor = NeonPink,
              unfocusedBorderColor = Lila.copy(alpha = 0.4f)
            ),
            shape = RoundedCornerShape(12.dp),
            minLines = 3,
            maxLines = 5
          )
        }
      }
    }
  }

  // EXPORT CANINE REEL DIALOG
  if (showExportDialog) {
    AlertDialog(
      onDismissRequest = { showExportDialog = false },
      containerColor = DeepVioletSurface,
      shape = RoundedCornerShape(22.dp),
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🎬", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Export HeyDog Video Reel",
            color = ImperialGold,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
          )
        }
      },
      text = {
        Column {
          Text(
            text = "Your canine AI video is ready for Dubai social media reels & stories!",
            color = TextHighLuxury,
            fontSize = 12.sp
          )
          Spacer(modifier = Modifier.height(10.dp))
          Surface(
            color = CardSurface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Text(text = "• Location: ${selectedScene.title}", color = TextMutedPink, fontSize = 11.sp)
              Text(text = "• Canine Voice: ${selectedVoice.displayName}", color = TextMutedPink, fontSize = 11.sp)
              Text(text = "• Format: 1080p 60fps Vertical Reel (9:16)", color = ImperialGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
              Text(text = "• Lip-Sync: Real-time Canine Phoneme Sync", color = RoseGold, fontSize = 11.sp)
              Text(text = "• Watermark: No Sleep Still Rich VIP", color = TextMutedPink, fontSize = 11.sp)
            }
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            showExportDialog = false
            viewModel.playVideo()
          },
          colors = ButtonDefaults.buttonColors(containerColor = NeonPink)
        ) {
          Text("Play & Export Reel", color = Color.White, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showExportDialog = false }) {
          Text("Close", color = Lila)
        }
      }
    )
  }
}
