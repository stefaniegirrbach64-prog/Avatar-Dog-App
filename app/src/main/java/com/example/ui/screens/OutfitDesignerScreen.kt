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
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.DesignerMonogram
import com.example.model.DogCoutureOutfit
import com.example.model.OutfitFabric
import com.example.model.OutfitSilhouette
import com.example.model.OutfitTrim
import com.example.ui.CoutureViewModel
import com.example.ui.theme.CardSurface
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.PinkBokeh
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet
import kotlin.random.Random

// Luxury Color Swatches
private val SwatchColors = listOf(
  0xFFFF1493 to "Neon Pink",
  0xFFFFD700 to "24K Gold",
  0xFF8A2BE2 to "Violet Amethyst",
  0xFFC8A2C8 to "Metallic Lila",
  0xFF121118 to "Jet Rebel Black",
  0xFFE0115F to "Electric Magenta",
  0xFFB76E79 to "Rose Gold Glam",
  0xFFE0E6ED to "Diamond Silver",
  0xFF00FA9A to "Cyber Emerald",
  0xFF39FF14 to "Acid Lime Glow"
)

// Grand Opening Curated Capsule Outfits
private val GrandOpeningCapsules = listOf(
  DogCoutureOutfit(
    id = "cap_grand_moto",
    name = "Grand Opening Gold Ribbon Moto",
    silhouette = OutfitSilhouette.MOTO_BIKER,
    fabric = OutfitFabric.PATENT_LEATHER,
    trim = OutfitTrim.GOLD_BULLION,
    primaryColorHex = 0xFFFF1493,
    secondaryColorHex = 0xFFFFD700,
    monogram = DesignerMonogram.GRAND_OPENING,
    isGrandOpeningExclusive = true
  ),
  DogCoutureOutfit(
    id = "cap_imperial_cape",
    name = "Imperial Velvet Gala Cape",
    silhouette = OutfitSilhouette.ROYAL_CAPE,
    fabric = OutfitFabric.CRUSHED_VELVET,
    trim = OutfitTrim.MINK_FUR,
    primaryColorHex = 0xFF8A2BE2,
    secondaryColorHex = 0xFFFFD700,
    monogram = DesignerMonogram.CGE_FLAGSHIP,
    isGrandOpeningExclusive = true
  ),
  DogCoutureOutfit(
    id = "cap_cyber_puffer",
    name = "Cyber Neon Panther Puffer",
    silhouette = OutfitSilhouette.HAUTE_PUFFER,
    fabric = OutfitFabric.METALLIC_LAME,
    trim = OutfitTrim.DIAMOND_SPIKES,
    primaryColorHex = 0xFFE0115F,
    secondaryColorHex = 0xFF00FA9A,
    monogram = DesignerMonogram.NO_SLEEP_RICH,
    isGrandOpeningExclusive = true
  ),
  DogCoutureOutfit(
    id = "cap_silk_trench",
    name = "Parisian Silk Trench Coat",
    silhouette = OutfitSilhouette.SILK_TRENCH,
    fabric = OutfitFabric.CROC_EMBOSSED,
    trim = OutfitTrim.CHUNKY_ZIPPER,
    primaryColorHex = 0xFF121118,
    secondaryColorHex = 0xFFB76E79,
    monogram = DesignerMonogram.VOGUE_PAWS,
    isGrandOpeningExclusive = true
  ),
  DogCoutureOutfit(
    id = "cap_diamond_bodice",
    name = "Diamond Pavé Harness Gown",
    silhouette = OutfitSilhouette.DIAMOND_HARNESS,
    fabric = OutfitFabric.DIAMOND_QUILT,
    trim = OutfitTrim.RHINESTONE_FRINGE,
    primaryColorHex = 0xFFE0E6ED,
    secondaryColorHex = 0xFFFF1493,
    monogram = DesignerMonogram.ROYAL_PANTHER,
    isGrandOpeningExclusive = true
  )
)

@Composable
fun OutfitDesignerScreen(
  viewModel: CoutureViewModel,
  modifier: Modifier = Modifier
) {
  val outfitDraft by viewModel.outfitDraft.collectAsState()
  val confettiActive by viewModel.grandOpeningCelebrationActive.collectAsState()

  var isIconBadgeMode by remember { mutableStateOf(false) }
  var showExportDialog by remember { mutableStateOf(false) }

  // Confetti particles loop
  val infiniteTransition = rememberInfiniteTransition(label = "ConfettiLoop")
  val confettiAnim by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(3000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "ConfettiFall"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 6.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    // 1. GRAND OPENING BOUTIQUE CELEBRATION HERO BANNER
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .shadow(12.dp, RoundedCornerShape(20.dp), ambientColor = ImperialGold, spotColor = NeonPink),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          Brush.horizontalGradient(listOf(ImperialGold, NeonPink, Violet, ImperialGold))
        )
      ) {
        Box(modifier = Modifier.fillMaxWidth()) {
          // Atelier Photo Background
          Image(
            painter = painterResource(id = R.drawable.img_grand_opening_outfit),
            contentDescription = "Grand Opening Couture Atelier",
            modifier = Modifier
              .fillMaxWidth()
              .height(130.dp),
            contentScale = ContentScale.Crop
          )
          // Gradient Scrim
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(130.dp)
              .background(
                Brush.verticalGradient(
                  colors = listOf(Color(0x660F061A), Color(0xEE16072B))
                )
              )
          )

          // Festive Confetti Canvas
          if (confettiActive) {
            Canvas(
              modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
            ) {
              val rand = Random(42)
              for (i in 0..24) {
                val x = (rand.nextFloat() * size.width)
                val speed = 0.5f + rand.nextFloat() * 0.8f
                val y = ((confettiAnim * speed * size.height) + (rand.nextFloat() * size.height)) % size.height
                val color = when (i % 4) {
                  0 -> ImperialGold
                  1 -> NeonPink
                  2 -> Lila
                  else -> Color.White
                }
                drawCircle(color = color.copy(alpha = 0.85f), radius = 3.5f, center = Offset(x, y))
              }
            }
          }

          // Banner Content
          Column(
            modifier = Modifier
              .fillMaxWidth()
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
                border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                  Text(text = "🎉", fontSize = 12.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "SHOP GRAND OPENING TODAY",
                    color = ImperialGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                  )
                }
              }

              IconButton(
                onClick = { viewModel.toggleCelebrationConfetti() },
                modifier = Modifier.size(32.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Celebration,
                  contentDescription = "Toggle Confetti",
                  tint = if (confettiActive) ImperialGold else Lila
                )
              }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Dog Luxury Fashion Outfits & Icon Designer",
              color = TextHighLuxury,
              fontSize = 16.sp,
              fontWeight = FontWeight.Black
            )
            Text(
              text = "Design bespoke couture jackets, gala capes & generate your exclusive boutique icon badge!",
              color = TextMutedPink,
              fontSize = 11.sp,
              lineHeight = 14.sp
            )
          }
        }
      }
    }

    // 2. LIVE PROCEDURAL OUTFIT & FASHION ICON CANVAS
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.4f))
      ) {
        Column(
          modifier = Modifier.padding(12.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = outfitDraft.name.uppercase(),
                color = ImperialGold,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
              )
              Text(
                text = "${outfitDraft.silhouette.displayName} • ${outfitDraft.fabric.displayName}",
                color = Lila,
                fontSize = 11.sp
              )
            }

            // Toggle Icon vs Mannequin View
            Row(verticalAlignment = Alignment.CenterVertically) {
              FilterChip(
                selected = !isIconBadgeMode,
                onClick = { isIconBadgeMode = false },
                label = { Text("Atelier", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = NeonPink,
                  selectedLabelColor = Color.White
                )
              )
              Spacer(modifier = Modifier.width(4.dp))
              FilterChip(
                selected = isIconBadgeMode,
                onClick = { isIconBadgeMode = true },
                label = { Text("Icon Badge", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = ImperialGold,
                  selectedLabelColor = Color.Black
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // 3D Visual Shader Box for Custom Outfit
          BoxWithConstraints(
            modifier = Modifier
              .fillMaxWidth()
              .aspectRatio(if (isIconBadgeMode) 1f else 1.35f)
              .clip(if (isIconBadgeMode) CircleShape else RoundedCornerShape(16.dp))
              .shadow(16.dp, if (isIconBadgeMode) CircleShape else RoundedCornerShape(16.dp))
              .background(DeepVioletSurface)
              .border(
                width = if (isIconBadgeMode) 3.dp else 1.5.dp,
                brush = Brush.linearGradient(
                  listOf(ImperialGold, NeonPink, Violet, ImperialGold)
                ),
                shape = if (isIconBadgeMode) CircleShape else RoundedCornerShape(16.dp)
              )
          ) {
            val primaryColor = Color(outfitDraft.primaryColorHex)
            val secondaryColor = Color(outfitDraft.secondaryColorHex)

            Canvas(modifier = Modifier.fillMaxSize()) {
              drawOutfitProceduralShader(
                outfit = outfitDraft,
                isBadge = isIconBadgeMode,
                primaryColor = primaryColor,
                secondaryColor = secondaryColor
              )
            }

            // Top-left Grand Opening Ribbon Stamp
            if (outfitDraft.isGrandOpeningExclusive) {
              Surface(
                color = Color(0xDD0F061A),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
                modifier = Modifier
                  .align(Alignment.TopStart)
                  .padding(10.dp)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(text = "👑", fontSize = 10.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "GRAND OPENING EDITION",
                    color = ImperialGold,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }

            // Bottom Monogram Tag
            Surface(
              color = Color(0xEE140526),
              shape = RoundedCornerShape(10.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink),
              modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Text(text = outfitDraft.monogram.badgeGlyph, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = outfitDraft.monogram.label,
                  color = TextHighLuxury,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 0.5.sp
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Action Buttons: Wear on Avatar & Export Icon Badge
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Button(
              onClick = { viewModel.applyDraftToAvatar() },
              modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .testTag("apply_outfit_to_avatar_button"),
              colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
              shape = RoundedCornerShape(12.dp)
            ) {
              Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.White
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text("Wear on Runway", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }

            Button(
              onClick = { showExportDialog = true },
              modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .testTag("export_icon_badge_button"),
              colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
              border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
              shape = RoundedCornerShape(12.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = ImperialGold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text("Export Icon Badge", color = ImperialGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
          }
        }
      }
    }

    // 3. GRAND OPENING CAPSULE COLLECTION PRESETS CAROUSEL
    item {
      Column {
        Text(
          text = "GRAND OPENING CAPSULE COLLECTION",
          color = ImperialGold,
          fontSize = 11.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          modifier = Modifier.padding(bottom = 6.dp)
        )

        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(GrandOpeningCapsules) { capsule ->
            val isSelected = outfitDraft.id == capsule.id
            Card(
              modifier = Modifier
                .width(180.dp)
                .clickable { viewModel.loadPredefinedOutfit(capsule) },
              colors = CardDefaults.cardColors(
                containerColor = if (isSelected) DeepVioletSurface else CardSurface
              ),
              shape = RoundedCornerShape(14.dp),
              border = androidx.compose.foundation.BorderStroke(
                if (isSelected) 1.8.dp else 1.dp,
                if (isSelected) NeonPink else Lila.copy(alpha = 0.25f)
              )
            ) {
              Column(modifier = Modifier.padding(10.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(text = capsule.silhouette.iconGlyph, fontSize = 18.sp)
                  Surface(
                    color = Color(capsule.primaryColorHex),
                    shape = CircleShape,
                    border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
                    modifier = Modifier.size(16.dp)
                  ) {}
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = capsule.name,
                  color = TextHighLuxury,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  maxLines = 1
                )
                Text(
                  text = capsule.trim.displayName,
                  color = Lila,
                  fontSize = 10.sp,
                  maxLines = 1
                )
              }
            }
          }
        }
      }
    }

    // 4. SILHOUETTE SELECTOR
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.25f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "1. COUTURE SILHOUETTE & CUT",
            color = ImperialGold,
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          OutfitSilhouette.values().forEach { sil ->
            val isSelected = outfitDraft.silhouette == sil
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) DeepVioletSurface else Color.Transparent)
                .border(
                  width = 1.dp,
                  color = if (isSelected) NeonPink else Color.Transparent,
                  shape = RoundedCornerShape(10.dp)
                )
                .clickable { viewModel.setOutfitSilhouette(sil) }
                .padding(horizontal = 10.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Text(text = sil.iconGlyph, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = sil.displayName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighLuxury
                  )
                  Text(
                    text = sil.description,
                    fontSize = 10.sp,
                    color = TextMutedPink
                  )
                }
              }

              if (isSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = NeonPink,
                  modifier = Modifier.size(18.dp)
                )
              }
            }
          }
        }
      }
    }

    // 5. FABRICS & TEXTURES
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.25f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "2. LUXURY FABRIC & FINISH",
            color = ImperialGold,
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(OutfitFabric.values()) { fab ->
              val isSelected = outfitDraft.fabric == fab
              Card(
                modifier = Modifier
                  .width(140.dp)
                  .clickable { viewModel.setOutfitFabric(fab) },
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
                  Text(
                    text = fab.displayName,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighLuxury
                  )
                  Text(
                    text = fab.textureNote,
                    fontSize = 9.sp,
                    color = Lila,
                    lineHeight = 11.sp
                  )
                }
              }
            }
          }
        }
      }
    }

    // 6. TRIMS & HARDWARE
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.25f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "3. HARDWARE & BESPOKE TRIMS",
            color = ImperialGold,
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          OutfitTrim.values().forEach { tr ->
            val isSelected = outfitDraft.trim == tr
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) DeepVioletSurface else Color.Transparent)
                .border(
                  width = 1.dp,
                  color = if (isSelected) ImperialGold else Color.Transparent,
                  shape = RoundedCornerShape(10.dp)
                )
                .clickable { viewModel.setOutfitTrim(tr) }
                .padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = tr.displayName,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = TextHighLuxury
                )
                Text(
                  text = tr.description,
                  fontSize = 10.sp,
                  color = TextMutedPink
                )
              }
              if (isSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = ImperialGold,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }
        }
      }
    }

    // 7. COLOR PALETTE SWATCHES
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.25f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "4. OUTFIT FABRIC COLOR",
            color = ImperialGold,
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(SwatchColors) { (hex, label) ->
              val isSelected = outfitDraft.primaryColorHex == hex
              Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                  viewModel.setOutfitColors(hex, outfitDraft.secondaryColorHex)
                }
              ) {
                Surface(
                  color = Color(hex),
                  shape = CircleShape,
                  border = androidx.compose.foundation.BorderStroke(
                    if (isSelected) 2.dp else 1.dp,
                    if (isSelected) Color.White else ImperialGold
                  ),
                  modifier = Modifier.size(36.dp)
                ) {
                  if (isSelected) {
                    Box(contentAlignment = Alignment.Center) {
                      Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = if (hex == 0xFFE0E6ED || hex == 0xFFFFD700) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                      )
                    }
                  }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = label,
                  fontSize = 9.sp,
                  color = Lila,
                  maxLines = 1
                )
              }
            }
          }
        }
      }
    }

    // 8. DESIGNER MONOGRAM CREST
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold.copy(alpha = 0.4f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "5. BOUTIQUE MONOGRAM & CREST",
            color = ImperialGold,
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          DesignerMonogram.values().forEach { mono ->
            val isSelected = outfitDraft.monogram == mono
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) DeepVioletSurface else Color.Transparent)
                .border(
                  width = 1.dp,
                  color = if (isSelected) NeonPink else Color.Transparent,
                  shape = RoundedCornerShape(10.dp)
                )
                .clickable { viewModel.setOutfitMonogram(mono) }
                .padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = mono.badgeGlyph, fontSize = 18.sp)
              Spacer(modifier = Modifier.width(10.dp))
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = mono.label,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = TextHighLuxury
                )
                Text(
                  text = mono.subtitle,
                  fontSize = 9.sp,
                  color = TextMutedPink
                )
              }
              if (isSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = NeonPink,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }
        }
      }
    }
  }

  // EXPORT ICON BADGE DIALOG
  if (showExportDialog) {
    AlertDialog(
      onDismissRequest = { showExportDialog = false },
      containerColor = DeepVioletSurface,
      shape = RoundedCornerShape(20.dp),
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🎉", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Grand Opening Outfit Icon",
            color = ImperialGold,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
          )
        }
      },
      text = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          // Circular Preview
          Box(
            modifier = Modifier
              .size(120.dp)
              .clip(CircleShape)
              .border(3.dp, ImperialGold, CircleShape)
              .background(Color(0xFF0F061A))
          ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
              drawOutfitProceduralShader(
                outfit = outfitDraft,
                isBadge = true,
                primaryColor = Color(outfitDraft.primaryColorHex),
                secondaryColor = Color(outfitDraft.secondaryColorHex)
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = outfitDraft.name,
            color = TextHighLuxury,
            fontWeight = FontWeight.Black,
            fontSize = 14.sp
          )
          Text(
            text = "Official CGE Boutique Fashion Icon Stamp",
            color = Lila,
            fontSize = 11.sp
          )
          Spacer(modifier = Modifier.height(8.dp))
          Surface(
            color = CardSurface,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(8.dp)) {
              Text(text = "• Cut: ${outfitDraft.silhouette.displayName}", color = TextMutedPink, fontSize = 10.sp)
              Text(text = "• Fabric: ${outfitDraft.fabric.displayName}", color = TextMutedPink, fontSize = 10.sp)
              Text(text = "• Trim: ${outfitDraft.trim.displayName}", color = TextMutedPink, fontSize = 10.sp)
              Text(text = "• Crest: ${outfitDraft.monogram.label}", color = ImperialGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.applyDraftToAvatar()
            showExportDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = NeonPink)
        ) {
          Text("Equip on Dog Avatar", color = Color.White, fontWeight = FontWeight.Bold)
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

/**
 * Procedural Canvas drawing for the Luxury Outfit and Icon Badge
 */
private fun DrawScope.drawOutfitProceduralShader(
  outfit: DogCoutureOutfit,
  isBadge: Boolean,
  primaryColor: Color,
  secondaryColor: Color
) {
  val w = size.width
  val h = size.height

  // 1. Shaded backdrop
  drawRect(
    brush = Brush.radialGradient(
      colors = listOf(primaryColor.copy(alpha = 0.25f), Color(0xFF100520)),
      center = Offset(w * 0.5f, h * 0.5f),
      radius = w * 0.6f
    )
  )

  // 2. Garment Silhouette Body Shape
  val bodyPath = Path().apply {
    when (outfit.silhouette) {
      OutfitSilhouette.ROYAL_CAPE -> {
        // Broad sweeping imperial coronation cape
        moveTo(w * 0.26f, h * 0.25f)
        lineTo(w * 0.74f, h * 0.25f)
        lineTo(w * 0.88f, h * 0.88f)
        quadraticTo(w * 0.5f, h * 0.96f, w * 0.12f, h * 0.88f)
        close()
      }
      OutfitSilhouette.HAUTE_PUFFER -> {
        // Voluminous puffy quilted shape
        moveTo(w * 0.24f, h * 0.30f)
        lineTo(w * 0.76f, h * 0.30f)
        quadraticTo(w * 0.86f, h * 0.55f, w * 0.82f, h * 0.86f)
        lineTo(w * 0.18f, h * 0.86f)
        quadraticTo(w * 0.14f, h * 0.55f, w * 0.24f, h * 0.30f)
        close()
      }
      else -> {
        // Tailored moto biker jacket / silk trench
        moveTo(w * 0.28f, h * 0.28f)
        lineTo(w * 0.72f, h * 0.28f)
        lineTo(w * 0.80f, h * 0.85f)
        lineTo(w * 0.20f, h * 0.85f)
        close()
      }
    }
  }

  // Draw main fabric
  drawPath(path = bodyPath, color = primaryColor)

  // Fabric Finish Shader Overlays
  when (outfit.fabric) {
    OutfitFabric.PATENT_LEATHER -> {
      // High-Gloss reflective highlight streak
      drawLine(
        brush = Brush.linearGradient(
          listOf(Color.Transparent, Color.White.copy(alpha = 0.45f), Color.Transparent),
          start = Offset(w * 0.3f, h * 0.3f),
          end = Offset(w * 0.6f, h * 0.8f)
        ),
        start = Offset(w * 0.3f, h * 0.3f),
        end = Offset(w * 0.6f, h * 0.8f),
        strokeWidth = 14f
      )
    }
    OutfitFabric.CRUSHED_VELVET -> {
      // Soft velvet depth shadows
      drawPath(
        path = bodyPath,
        brush = Brush.verticalGradient(
          listOf(Color.Transparent, Color.Black.copy(alpha = 0.38f), Color.Transparent)
        )
      )
    }
    OutfitFabric.METALLIC_LAME -> {
      // Shimmer gold lamé grain
      for (i in 0..12) {
        val y = h * (0.35f + (i * 0.04f))
        drawLine(
          color = ImperialGold.copy(alpha = 0.25f),
          start = Offset(w * 0.25f, y),
          end = Offset(w * 0.75f, y),
          strokeWidth = 2.5f
        )
      }
    }
    OutfitFabric.DIAMOND_QUILT -> {
      // Diamond quilt criss-cross lattice
      for (i in -4..5) {
        drawLine(
          color = Color.Black.copy(alpha = 0.3f),
          start = Offset(w * 0.2f + i * 28f, h * 0.35f),
          end = Offset(w * 0.45f + i * 28f, h * 0.85f),
          strokeWidth = 2f
        )
        drawLine(
          color = Color.Black.copy(alpha = 0.3f),
          start = Offset(w * 0.8f - i * 28f, h * 0.35f),
          end = Offset(w * 0.55f - i * 28f, h * 0.85f),
          strokeWidth = 2f
        )
      }
    }
    OutfitFabric.CROC_EMBOSSED -> {
      // Croc texture scales
      for (row in 0..4) {
        val y = h * 0.42f + row * 24f
        for (col in -3..3) {
          val x = w * 0.5f + col * 26f
          drawRoundRect(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(x - 10f, y),
            size = Size(20f, 14f),
            cornerRadius = CornerRadius(4f, 4f),
            style = Stroke(1.5f)
          )
        }
      }
    }
  }

  // 3. Lapels & Trims
  when (outfit.trim) {
    OutfitTrim.MINK_FUR -> {
      // Luxurious plush faux fur collar across shoulders
      val furPath = Path().apply {
        moveTo(w * 0.22f, h * 0.24f)
        quadraticTo(w * 0.5f, h * 0.40f, w * 0.78f, h * 0.24f)
        lineTo(w * 0.82f, h * 0.32f)
        quadraticTo(w * 0.5f, h * 0.48f, w * 0.18f, h * 0.32f)
        close()
      }
      drawPath(path = furPath, color = Color(0xFFFFF0F5))
      for (i in 0..12) {
        val fx = w * (0.22f + i * 0.045f)
        drawCircle(color = Color(0xFFF5E6ED), radius = 6f, center = Offset(fx, h * 0.32f))
      }
    }
    OutfitTrim.DIAMOND_SPIKES -> {
      // Sharp pyramid pink diamond studs along lapel line
      for (i in -3..3) {
        val sx = w * 0.5f + i * 26f
        val sy = h * 0.34f + kotlin.math.abs(i) * 6f
        drawCircle(color = secondaryColor, radius = 6f, center = Offset(sx, sy))
        drawCircle(color = Color.White, radius = 2f, center = Offset(sx, sy))
      }
    }
    OutfitTrim.GOLD_BULLION -> {
      // 24k Bullion Braid along border
      drawPath(
        path = bodyPath,
        color = ImperialGold,
        style = Stroke(width = 4.5f)
      )
    }
    OutfitTrim.CHUNKY_ZIPPER -> {
      // Heavy diagonal zipper
      drawLine(
        color = ImperialGold,
        start = Offset(w * 0.38f, h * 0.32f),
        end = Offset(w * 0.52f, h * 0.82f),
        strokeWidth = 6f
      )
      // Zipper teeth
      for (i in 0..10) {
        val tX = w * (0.38f + i * 0.014f)
        val tY = h * (0.32f + i * 0.05f)
        drawLine(
          color = Color(0xFF160A00),
          start = Offset(tX - 4f, tY),
          end = Offset(tX + 4f, tY),
          strokeWidth = 2f
        )
      }
    }
    OutfitTrim.RHINESTONE_FRINGE -> {
      // Dangling fringe
      for (i in -4..4) {
        val fx = w * 0.5f + i * 22f
        val fy = h * 0.85f
        drawLine(
          color = Color(0xFFE0E6ED),
          start = Offset(fx, fy),
          end = Offset(fx, fy + 22f),
          strokeWidth = 2.5f
        )
        drawCircle(color = Color.White, radius = 3f, center = Offset(fx, fy + 22f))
      }
    }
  }

  // 4. Center Designer Crest Stamp
  val crestCenter = Offset(w * 0.5f, h * 0.58f)
  drawCircle(
    color = Color(0xDD0F061A),
    radius = 18f,
    center = crestCenter
  )
  drawCircle(
    color = ImperialGold,
    radius = 18f,
    center = crestCenter,
    style = Stroke(2f)
  )
  drawCircle(
    color = secondaryColor,
    radius = 6f,
    center = crestCenter
  )
}
