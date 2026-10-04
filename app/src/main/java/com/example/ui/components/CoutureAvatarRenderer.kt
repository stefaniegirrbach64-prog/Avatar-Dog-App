package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.AccessoryItem
import com.example.model.AvatarBackground
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.PinkBokeh
import com.example.ui.theme.Violet
import com.example.ui.theme.VioletSparkle
import kotlin.random.Random

data class SparkleParticle(
  val x: Float,
  val y: Float,
  val radius: Float,
  val color: Color,
  val speed: Float,
  val phaseOffset: Float,
  val isStar: Boolean = false
)

@Composable
fun CoutureAvatarRenderer(
  config: AvatarConfig,
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 24.dp,
  showLabels: Boolean = true,
  interactiveSparkles: Boolean = true
) {
  // Sparkle animation transition
  val infiniteTransition = rememberInfiniteTransition(label = "SparkleLoop")
  val pulseAnim by infiniteTransition.animateFloat(
    initialValue = 0.2f,
    targetValue = 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(2200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "PulseGlow"
  )

  // Interactive tap sparkles
  val tapSparkles = remember { mutableStateListOf<Offset>() }

  val bgTopColor = Color(config.background.colorTop)
  val bgBottomColor = Color(config.background.colorBottom)
  val leoColor = Color(config.leoPattern.primaryHex)
  val jacketColor = Color(config.jacketColor.colorHex)

  // Seeded background particles
  val staticParticles = remember(config.background) {
    val random = Random(config.background.hashCode())
    List(36) {
      val isPink = random.nextBoolean()
      SparkleParticle(
        x = random.nextFloat(),
        y = random.nextFloat(),
        radius = random.nextFloat() * 6f + 2f,
        color = if (isPink) PinkBokeh.copy(alpha = 0.6f + random.nextFloat() * 0.35f)
        else VioletSparkle.copy(alpha = 0.6f + random.nextFloat() * 0.35f),
        speed = 0.6f + random.nextFloat() * 0.8f,
        phaseOffset = random.nextFloat() * 6.28f,
        isStar = random.nextBoolean()
      )
    }
  }

  BoxWithConstraints(
    modifier = modifier
      .aspectRatio(1f)
      .clip(RoundedCornerShape(cornerRadius))
      .shadow(16.dp, RoundedCornerShape(cornerRadius), ambientColor = NeonPink, spotColor = Violet)
      .border(
        width = 2.5.dp,
        brush = Brush.linearGradient(
          colors = listOf(ImperialGold, NeonPink, Violet, ImperialGold)
        ),
        shape = RoundedCornerShape(cornerRadius)
      )
      .pointerInput(interactiveSparkles) {
        if (interactiveSparkles) {
          detectTapGestures { offset ->
            if (tapSparkles.size > 12) tapSparkles.removeAt(0)
            tapSparkles.add(offset)
          }
        }
      }
      .testTag("avatar_canvas_box")
  ) {
    // 1. BACKGROUND LAYER
    if (config.background.sceneDrawableRes != null) {
      Image(
        painter = painterResource(id = config.background.sceneDrawableRes!!),
        contentDescription = config.background.displayName,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.radialGradient(
              colors = listOf(Color.Transparent, Color(0x662E0C49), Color(0xAA3B0F5C))
            )
          )
      )
    } else {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(bgTopColor, bgBottomColor)
            )
          )
      )
    }

    // 2. CANVAS SPARKLE & BOKEH PARTICLES LAYER
    Canvas(
      modifier = Modifier
        .fillMaxSize()
        .testTag("sparkle_particle_canvas")
    ) {
      val canvasWidth = size.width
      val canvasHeight = size.height

      // Background bokeh bubbles
      staticParticles.forEach { p ->
        val dynamicAlpha = ((kotlin.math.sin(pulseAnim * 3.14f + p.phaseOffset) + 1f) / 2f) * 0.7f + 0.3f
        val currentY = (p.y * canvasHeight + (pulseAnim * 15f * p.speed)) % canvasHeight
        val currentX = p.x * canvasWidth

        if (p.isStar) {
          drawSparkleStar(
            center = Offset(currentX, currentY),
            size = p.radius * 2.2f,
            color = p.color.copy(alpha = dynamicAlpha)
          )
        } else {
          drawCircle(
            color = p.color.copy(alpha = dynamicAlpha),
            radius = p.radius * 1.5f,
            center = Offset(currentX, currentY)
          )
        }
      }

      // Tap burst sparkles
      tapSparkles.forEach { tapOffset ->
        for (i in 0..7) {
          val angle = (i * 45) * (3.14159f / 180f)
          val distance = 25f + pulseAnim * 30f
          val starX = tapOffset.x + kotlin.math.cos(angle) * distance
          val starY = tapOffset.y + kotlin.math.sin(angle) * distance
          drawSparkleStar(
            center = Offset(starX, starY),
            size = 10f * pulseAnim,
            color = if (i % 2 == 0) ImperialGold else NeonPink
          )
        }
      }
    }

    // 3. BASE PITBULL PORTRAIT LAYER
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = config.baseEdition.imageRes),
        contentDescription = "American Pitbull Terrier Avatar",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )
    }

    // 4. PROCEDURAL LEO & FUR & ACCESSORY GRAPHICS LAYER
    Canvas(
      modifier = Modifier.fillMaxSize()
    ) {
      val cWidth = size.width
      val cHeight = size.height

      // Leo tint
      val furTint = leoColor.copy(alpha = 0.16f)
      drawRect(
        brush = Brush.radialGradient(
          colors = listOf(furTint, Color.Transparent),
          center = Offset(cWidth * 0.5f, cHeight * 0.45f),
          radius = cWidth * 0.65f
        )
      )

      // Strass forehead makeup
      if (config.selectedAccessories.contains(AccessoryItem.STRASS_FOREHEAD_MAKEUP.id) ||
        config.baseEdition == BaseEdition.LEO_GLAM) {
        val centerX = cWidth * 0.5f
        val centerY = cHeight * 0.28f

        drawSparkleStar(Offset(centerX, centerY), size = 18f, color = ImperialGold)
        drawCircle(color = NeonPink, radius = 6f, center = Offset(centerX, centerY))
        drawCircle(color = Color.White, radius = 2.5f, center = Offset(centerX, centerY))

        for (step in 1..4) {
          val dx = step * 16f
          val dy = (step * step) * 2f
          drawCircle(color = NeonPink, radius = 4f, center = Offset(centerX - dx, centerY - 6f + dy))
          drawCircle(color = ImperialGold, radius = 2f, center = Offset(centerX - dx, centerY - 6f + dy))
          drawCircle(color = NeonPink, radius = 4f, center = Offset(centerX + dx, centerY - 6f + dy))
          drawCircle(color = ImperialGold, radius = 2f, center = Offset(centerX + dx, centerY - 6f + dy))
        }
      }

      // Ears styling indicator
      if (config.hangingEars) {
        // Floppy hanging ear strass
        drawCircle(color = NeonPink.copy(alpha = 0.7f), radius = 5f, center = Offset(cWidth * 0.18f, cHeight * 0.36f))
        drawCircle(color = ImperialGold.copy(alpha = 0.8f), radius = 3f, center = Offset(cWidth * 0.18f, cHeight * 0.36f))
        drawCircle(color = NeonPink.copy(alpha = 0.7f), radius = 5f, center = Offset(cWidth * 0.82f, cHeight * 0.36f))
        drawCircle(color = ImperialGold.copy(alpha = 0.8f), radius = 3f, center = Offset(cWidth * 0.82f, cHeight * 0.36f))
      } else {
        // Upright alert ear studs
        drawSparkleStar(center = Offset(cWidth * 0.26f, cHeight * 0.16f), size = 10f, color = ImperialGold)
        drawSparkleStar(center = Offset(cWidth * 0.74f, cHeight * 0.16f), size = 10f, color = ImperialGold)
      }

      // Dynamic Jacket / Custom Tailored Outfit Color Tint
      val outfit = config.customOutfit
      val bodyPrimaryColor = if (outfit != null) Color(outfit.primaryColorHex) else jacketColor
      val bodySecondaryColor = if (outfit != null) Color(outfit.secondaryColorHex) else ImperialGold

      val bodyTint = bodyPrimaryColor.copy(alpha = 0.28f)
      drawRect(
        brush = Brush.verticalGradient(
          colors = listOf(Color.Transparent, bodyTint, bodyPrimaryColor.copy(alpha = 0.44f)),
          startY = cHeight * 0.60f,
          endY = cHeight
        )
      )

      if (outfit != null) {
        // Draw Grand Opening Lapel & Crest on Avatar
        drawCircle(
          color = bodySecondaryColor,
          radius = 12f,
          center = Offset(cWidth * 0.5f, cHeight * 0.88f)
        )
        drawCircle(
          color = Color(0xDD0F061A),
          radius = 10f,
          center = Offset(cWidth * 0.5f, cHeight * 0.88f)
        )
        drawCircle(
          color = bodyPrimaryColor,
          radius = 4f,
          center = Offset(cWidth * 0.5f, cHeight * 0.88f)
        )
      }

      // PROCEDURAL ACCESSORY VISUAL ENHANCEMENTS (RENDERED IN CUSTOM LAYER ORDER)
      config.selectedAccessories.forEach { accId ->
        when (accId) {
          AccessoryItem.PINK_DIAMOND_CROWN.id, AccessoryItem.JEWELED_TIARA.id -> {
            // Tiara / Crown across forehead/crest
            val crownCenter = Offset(cWidth * 0.5f, cHeight * 0.15f)
            drawSparkleStar(crownCenter, 22f, ImperialGold)
            drawCircle(color = NeonPink, radius = 7f, center = crownCenter)
            drawSparkleStar(Offset(cWidth * 0.42f, cHeight * 0.17f), 12f, NeonPink)
            drawSparkleStar(Offset(cWidth * 0.58f, cHeight * 0.17f), 12f, NeonPink)
          }

          AccessoryItem.OVERSIZED_SUNGLASSES.id, AccessoryItem.SUNGLASSES_ON_HEAD.id, AccessoryItem.RETRO_CAT_EYE_SHADES.id -> {
            // Oversized luxury shield sunglasses across eyes
            val eyeY = cHeight * 0.38f
            drawRoundRect(
              color = Color(0xDD0C0814),
              topLeft = Offset(cWidth * 0.22f, eyeY - 24f),
              size = androidx.compose.ui.geometry.Size(cWidth * 0.56f, 48f),
              cornerRadius = androidx.compose.ui.geometry.CornerRadius(14f, 14f)
            )
            drawRoundRect(
              brush = Brush.horizontalGradient(listOf(ImperialGold, NeonPink, ImperialGold)),
              topLeft = Offset(cWidth * 0.22f, eyeY - 24f),
              size = androidx.compose.ui.geometry.Size(cWidth * 0.56f, 48f),
              cornerRadius = androidx.compose.ui.geometry.CornerRadius(14f, 14f),
              style = Stroke(width = 3.5f)
            )
            // Lens glare reflection
            drawLine(
              color = Color(0x66FFFFFF),
              start = Offset(cWidth * 0.26f, eyeY + 12f),
              end = Offset(cWidth * 0.42f, eyeY - 16f),
              strokeWidth = 3f
            )
          }

          AccessoryItem.PIERCINGS_PINK_DIAMONDS.id -> {
            // Dangling diamond hoops on ears
            val leftEarX = cWidth * 0.16f
            val rightEarX = cWidth * 0.84f
            val earY = cHeight * 0.44f

            drawCircle(color = ImperialGold, radius = 8f, center = Offset(leftEarX, earY), style = Stroke(2f))
            drawSparkleStar(Offset(leftEarX, earY + 14f), 10f, NeonPink)

            drawCircle(color = ImperialGold, radius = 8f, center = Offset(rightEarX, earY), style = Stroke(2f))
            drawSparkleStar(Offset(rightEarX, earY + 14f), 10f, NeonPink)
          }

          AccessoryItem.GOLD_CHAINS_PINK_DIAMONDS.id -> {
            // Chunky Miami Cuban chains with pink diamonds
            val neckY = cHeight * 0.76f
            for (i in -4..4) {
              val linkX = cWidth * 0.5f + i * 22f
              val linkY = neckY + kotlin.math.abs(i) * 5f
              drawCircle(color = ImperialGold, radius = 10f, center = Offset(linkX, linkY))
              drawCircle(color = NeonPink, radius = 5f, center = Offset(linkX, linkY))
              drawCircle(color = Color.White, radius = 2f, center = Offset(linkX, linkY))
            }
          }

          AccessoryItem.MINIMAL_GOLD_NECKLACE.id -> {
            // Layered dainty gold chain
            val neckY = cHeight * 0.72f
            for (i in -3..3) {
              val linkX = cWidth * 0.5f + i * 16f
              val linkY = neckY + kotlin.math.abs(i) * 4f
              drawCircle(color = ImperialGold, radius = 4f, center = Offset(linkX, linkY))
            }
            drawCircle(color = ImperialGold, radius = 7f, center = Offset(cWidth * 0.5f, neckY + 16f))
          }

          AccessoryItem.DESIGNER_DOG_COLLAR.id, AccessoryItem.SPIKED_GOLD_COLLAR.id -> {
            // Designer dog collar with spikes or gold buckle
            val collarY = cHeight * 0.69f
            drawRoundRect(
              color = NeonPink,
              topLeft = Offset(cWidth * 0.28f, collarY),
              size = androidx.compose.ui.geometry.Size(cWidth * 0.44f, 16f),
              cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
            )
            for (i in -3..3) {
              val spikeX = cWidth * 0.5f + i * 20f
              val spikeY = collarY + 8f
              drawSparkleStar(Offset(spikeX, spikeY), 6f, ImperialGold)
            }
          }

          AccessoryItem.PEARL_GOLD_HARNESS.id -> {
            // Pearl and gold collar
            val collarY = cHeight * 0.70f
            for (i in -4..4) {
              val pearlX = cWidth * 0.5f + i * 18f
              val pearlY = collarY + kotlin.math.abs(i) * 4f
              drawCircle(color = Color(0xFFF5F5DC), radius = 6f, center = Offset(pearlX, pearlY))
              drawCircle(color = Color.White, radius = 2f, center = Offset(pearlX - 1f, pearlY - 1f))
            }
          }
        }
      }
    }

    // 5. ACCESSORY VISUAL BADGES & GLYPH OVERLAYS
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)
    ) {
      // Top active badge
      val topItem = config.selectedAccessories.firstOrNull()?.let { id ->
        AccessoryItem.values().firstOrNull { it.id == id }
      }

      if (topItem != null && showLabels) {
        Surface(
          color = ObsidianVelvet.copy(alpha = 0.88f),
          shape = RoundedCornerShape(12.dp),
          border = borderStroke(ImperialGold),
          modifier = Modifier
            .align(Alignment.TopCenter)
            .shadow(6.dp)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = topItem.iconGlyph, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = topItem.displayName.uppercase(),
              color = ImperialGold,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
          }
        }
      }

      // Bottom accessories pills
      if (showLabels) {
        Box(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
        ) {
          Surface(
            color = Color(0xDD120424),
            shape = RoundedCornerShape(14.dp),
            border = borderStroke(NeonPink),
            modifier = Modifier
              .fillMaxWidth()
              .shadow(8.dp)
          ) {
            Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
              Text(
                text = if (config.customOutfit != null)
                  "${config.customOutfit.name} • ${config.leoPattern.displayName}"
                else
                  "${config.leoPattern.displayName} • ${config.jacketColor.displayName}",
                color = TextHighLuxury,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                modifier = Modifier.align(Alignment.CenterStart)
              )
              Text(
                text = if (config.hangingEars) "Floppy Ears ✓" else "Upright Ears ✓",
                color = Lila,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterEnd)
              )
            }
          }
        }
      }
    }

    // 6. DIAMOND SPARKLE CORNER EMBLEMS
    Canvas(modifier = Modifier.fillMaxSize()) {
      val inset = 16f
      drawSparkleStar(Offset(inset, inset), 12f, ImperialGold)
      drawSparkleStar(Offset(size.width - inset, inset), 12f, NeonPink)
      drawSparkleStar(Offset(inset, size.height - inset), 12f, NeonPink)
      drawSparkleStar(Offset(size.width - inset, size.height - inset), 12f, ImperialGold)
    }
  }
}

private fun borderStroke(color: Color) = androidx.compose.foundation.BorderStroke(
  1.2.dp,
  Brush.horizontalGradient(listOf(color, NeonPink, ImperialGold))
)

private val ObsidianVelvet = Color(0xFF0F061A)
private val TextHighLuxury = Color(0xFFFFF0F8)

private fun DrawScope.drawSparkleStar(center: Offset, size: Float, color: Color) {
  val path = Path().apply {
    moveTo(center.x, center.y - size)
    quadraticTo(center.x, center.y, center.x + size, center.y)
    quadraticTo(center.x, center.y, center.x, center.y + size)
    quadraticTo(center.x, center.y, center.x - size, center.y)
    quadraticTo(center.x, center.y, center.x - size, center.y)
    close()
  }
  drawPath(path = path, color = color)
  drawCircle(color = Color.White, radius = size * 0.25f, center = center)
}
