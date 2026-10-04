package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CoutureRepository
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.ui.CoutureViewModel
import com.example.ui.StudioTab
import com.example.ui.components.CoutureAvatarRenderer
import com.example.ui.theme.CardSurface
import com.example.ui.theme.CardSurfaceElevated
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.HotNeonPink
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.ObsidianVelvet
import com.example.ui.theme.PinkBokeh
import com.example.ui.theme.RoseGold
import com.example.ui.theme.RoseGoldGlow
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet
import com.example.ui.theme.VioletSparkle

@Composable
fun RunwayScreen(
  viewModel: CoutureViewModel,
  activeConfig: AvatarConfig,
  modifier: Modifier = Modifier
) {
  var showNameDialog by remember { mutableStateOf(false) }
  var showMagazineDialog by remember { mutableStateOf(false) }
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // 0. OFFICIAL SHOP HERO BANNER (MATCHING SHOP DESIGN BILD 2)
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp)
        .shadow(14.dp, RoundedCornerShape(22.dp), ambientColor = ImperialGold, spotColor = NeonPink)
        .testTag("shop_hero_banner_card"),
      colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
      shape = RoundedCornerShape(22.dp),
      border = androidx.compose.foundation.BorderStroke(
        1.5.dp,
        Brush.horizontalGradient(listOf(NeonPink, ImperialGold, RoseGold, Violet))
      )
    ) {
      Box(modifier = Modifier.fillMaxWidth()) {
        // Glowing contour circle lines background (matching Bild 2)
        Canvas(modifier = Modifier.matchParentSize()) {
          drawCircle(
            color = Violet.copy(alpha = 0.25f),
            radius = size.width * 0.75f,
            center = Offset(size.width * 0.5f, size.height * 1.1f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.5f)
          )
          drawCircle(
            color = NeonPink.copy(alpha = 0.15f),
            radius = size.width * 0.55f,
            center = Offset(size.width * 0.5f, size.height * 1.1f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
          )
        }

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          // Kicker: ✨ THE NEW DOG ERA
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "✨", fontSize = 12.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "THE NEW DOG ERA",
              color = RoseGoldGlow,
              fontSize = 11.sp,
              fontWeight = FontWeight.ExtraBold,
              letterSpacing = 2.sp
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          // Headline: "Für Hunde, die auffallen."
          Text(
            text = "Für Hunde,",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextHighLuxury,
            letterSpacing = (-0.5).sp
          )
          Text(
            text = "die auffallen.",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = HotNeonPink,
            letterSpacing = (-0.5).sp
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Subtitle from shop
          Text(
            text = "Hunde-Fashion und Accessoires für den eleganten Hund von heute. Laut, luxuriös und ganz und gar nicht gewöhnlich.",
            fontSize = 12.sp,
            color = TextMutedPink,
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Hot Neon Pink Pill CTA Button
          Button(
            onClick = { viewModel.setTab(StudioTab.OUTFIT_DESIGNER) },
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .shadow(8.dp, RoundedCornerShape(24.dp), spotColor = HotNeonPink)
              .testTag("shop_kollektion_entdecken_button"),
            colors = ButtonDefaults.buttonColors(containerColor = HotNeonPink),
            shape = RoundedCornerShape(24.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Text(
                text = "KOLLEKTION ENTDECKEN",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(text = "➔", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Attitude line from shop
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "UNSERE ATTITUDE ↘",
              color = ImperialGold,
              fontSize = 10.sp,
              fontWeight = FontWeight.ExtraBold,
              letterSpacing = 1.sp
            )
            Text(
              text = "Fashionhund in Bikerjacke",
              color = Lila,
              fontSize = 10.sp
            )
          }
        }
      }
    }

    // 1. BRAND HEADER & VIP TITLE
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "CGE PANTHER COUTURE",
          fontSize = 13.sp,
          fontWeight = FontWeight.ExtraBold,
          letterSpacing = 2.sp,
          color = ImperialGold
        )
        Text(
          text = "HAUTE CANINE RUNWAY",
          fontSize = 20.sp,
          fontWeight = FontWeight.Black,
          color = TextHighLuxury,
          letterSpacing = 0.5.sp
        )
      }

      Surface(
        color = CardSurface,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = ImperialGold,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "VIP SALON",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = NeonPink,
            letterSpacing = 1.sp
          )
        }
      }
    }

    // 2. MAIN AVATAR RENDERER
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      contentAlignment = Alignment.Center
    ) {
      CoutureAvatarRenderer(
        config = activeConfig,
        modifier = Modifier
          .fillMaxWidth()
          .shadow(20.dp, spotColor = NeonPink, ambientColor = Violet),
        cornerRadius = 28.dp,
        showLabels = true
      )
    }

    // 3. AVATAR NAME & BIO BADGE (EDITABLE)
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { showNameDialog = true }
        .testTag("avatar_name_card"),
      colors = CardDefaults.cardColors(containerColor = CardSurface),
      shape = RoundedCornerShape(18.dp),
      border = androidx.compose.foundation.BorderStroke(
        1.5.dp,
        Brush.horizontalGradient(listOf(ImperialGold, NeonPink, Violet))
      )
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = activeConfig.name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = TextHighLuxury
          )
          Text(
            text = activeConfig.vipTitle,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Lila
          )
        }
        IconButton(
          onClick = { showNameDialog = true },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Name",
            tint = ImperialGold,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }

    // 3.4 HEYDOG DUBAI STORYBOARDS TEASER
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { viewModel.setTab(StudioTab.HEY_DOG) }
        .testTag("runway_heydog_studio_banner"),
      colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
      shape = RoundedCornerShape(16.dp),
      border = androidx.compose.foundation.BorderStroke(
        1.5.dp,
        Brush.horizontalGradient(listOf(RoseGold, NeonPink, ImperialGold))
      )
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = DeepVioletSurface,
          shape = CircleShape,
          border = androidx.compose.foundation.BorderStroke(1.2.dp, RoseGold),
          modifier = Modifier.size(44.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Text(text = "🎬", fontSize = 20.sp)
          }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "HEYDOG AI VIDEO STUDIO",
              color = RoseGoldGlow,
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
              color = NeonPink,
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "HEYGEN FOR DOGS",
                color = Color.White,
                fontSize = 7.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
          Text(
            text = "Dubai Storyboard: Yacht, Jet & Burj Khalifa",
            color = TextHighLuxury,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Talking canine avatar, lip-sync voiceovers & reels",
            color = TextMutedPink,
            fontSize = 10.sp
          )
        }
        Icon(
          imageVector = Icons.Default.AutoAwesome,
          contentDescription = null,
          tint = ImperialGold,
          modifier = Modifier.size(18.dp)
        )
      }
    }

    // 3.5 GRAND OPENING BOUTIQUE OUTFIT ATELIER TEASER
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { viewModel.setTab(StudioTab.OUTFIT_DESIGNER) }
        .testTag("runway_outfit_atelier_banner"),
      colors = CardDefaults.cardColors(containerColor = CardSurface),
      shape = RoundedCornerShape(16.dp),
      border = androidx.compose.foundation.BorderStroke(
        1.2.dp,
        Brush.horizontalGradient(listOf(ImperialGold, NeonPink, ImperialGold))
      )
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = DeepVioletSurface,
          shape = CircleShape,
          border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
          modifier = Modifier.size(42.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Text(text = "🪡", fontSize = 20.sp)
          }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "GRAND OPENING ATELIER",
              color = ImperialGold,
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
              color = NeonPink,
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "NEW",
                color = Color.White,
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
          Text(
            text = "Dog Luxury Fashion Outfits & Icon Designer",
            color = TextHighLuxury,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Design bespoke jackets, gala capes & export boutique icons",
            color = TextMutedPink,
            fontSize = 10.sp
          )
        }
        Icon(
          imageVector = Icons.Default.AutoAwesome,
          contentDescription = null,
          tint = ImperialGold,
          modifier = Modifier.size(18.dp)
        )
      }
    }

    // 4. HANGING EARS TOGGLE (REQUESTED: "Hanging ears option ON/OFF")
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .testTag("hanging_ears_toggle_card"),
      colors = CardDefaults.cardColors(containerColor = DeepVioletSurface),
      shape = RoundedCornerShape(16.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, Violet)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
              .background(NeonPink.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Hearing,
              contentDescription = null,
              tint = NeonPink,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = if (activeConfig.hangingEars) "Floppy Hanging Ears [ON]" else "Cropped Upright Ears [ON]",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = TextHighLuxury
            )
            Text(
              text = if (activeConfig.hangingEars)
                "Authentic breed floppy ears with pink strass"
              else
                "Sculpted alert posture with diamond tip studs",
              fontSize = 11.sp,
              color = TextMutedPink
            )
          }
        }

        Switch(
          checked = activeConfig.hangingEars,
          onCheckedChange = { viewModel.toggleHangingEars() },
          colors = SwitchDefaults.colors(
            checkedThumbColor = ImperialGold,
            checkedTrackColor = NeonPink,
            uncheckedThumbColor = Lila,
            uncheckedTrackColor = CardSurface
          ),
          modifier = Modifier.testTag("hanging_ears_switch")
        )
      }
    }

    // 5. QUICK ACTIONS: HAUTE COUTURE SURPRISE + SAVE TO LOOKBOOK + VOGUE COVER
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = { viewModel.randomizeHauteCouture() },
        modifier = Modifier
          .weight(1f)
          .height(46.dp)
          .testTag("randomize_button"),
        colors = ButtonDefaults.buttonColors(containerColor = Violet),
        shape = RoundedCornerShape(14.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Shuffle,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
          tint = Color.White
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text("Surprise Me", fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }

      Button(
        onClick = { viewModel.saveCurrentLook() },
        modifier = Modifier
          .weight(1f)
          .height(46.dp)
          .testTag("save_look_button"),
        colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
        shape = RoundedCornerShape(14.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Bookmark,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
          tint = Color.White
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text("Save Look", fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }

      OutlinedButton(
        onClick = { showMagazineDialog = true },
        modifier = Modifier
          .height(46.dp)
          .testTag("magazine_cover_button"),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, ImperialGold)
      ) {
        Icon(
          imageVector = Icons.Default.CameraAlt,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
          tint = ImperialGold
        )
      }
    }

    // 6. THREE SIGNATURE LOOKS CAROUSEL
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "SIGNATURE COUTURE EDITIONS",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.5.sp,
          color = ImperialGold
        )
        Text(
          text = "3 Iconic Styles",
          fontSize = 11.sp,
          color = Lila
        )
      }
      Spacer(modifier = Modifier.height(8.dp))

      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 6.dp)
      ) {
        items(CoutureRepository.ICONIC_PRESETS) { preset ->
          val isSelected = activeConfig.baseEdition == preset.baseEdition
          Card(
            modifier = Modifier
              .width(220.dp)
              .clickable { viewModel.applyPreset(preset) }
              .testTag("preset_${preset.baseEdition.id}"),
            colors = CardDefaults.cardColors(
              containerColor = if (isSelected) DeepVioletSurface else CardSurface
            ),
            shape = RoundedCornerShape(18.dp),
            border = androidx.compose.foundation.BorderStroke(
              if (isSelected) 2.dp else 1.dp,
              if (isSelected) NeonPink else Lila.copy(alpha = 0.4f)
            )
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Surface(
                  color = if (isSelected) NeonPink else Color(0x33FF1493),
                  shape = RoundedCornerShape(6.dp)
                ) {
                  Text(
                    text = preset.baseEdition.tag,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isSelected) Color.White else NeonPink,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
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

              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = preset.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextHighLuxury
              )
              Text(
                text = preset.baseEdition.subtitle,
                fontSize = 11.sp,
                color = TextMutedPink,
                maxLines = 2,
                modifier = Modifier.padding(top = 2.dp)
              )

              Spacer(modifier = Modifier.height(8.dp))
              Surface(
                color = ObsidianVelvet,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(
                  text = "Wear Signature Look",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = ImperialGold,
                  textAlign = TextAlign.Center,
                  modifier = Modifier.padding(vertical = 4.dp)
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }

  // DIALOG 1: EDIT AVATAR NAME & VIP TITLE
  if (showNameDialog) {
    var tempName by remember { mutableStateOf(activeConfig.name) }
    var tempTitle by remember { mutableStateOf(activeConfig.vipTitle) }

    AlertDialog(
      onDismissRequest = { showNameDialog = false },
      title = {
        Text(
          "Personalize Couture Dog",
          color = ImperialGold,
          fontWeight = FontWeight.Bold
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          OutlinedTextField(
            value = tempName,
            onValueChange = { tempName = it },
            label = { Text("Pitbull Name") },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = NeonPink,
              unfocusedBorderColor = Lila,
              focusedTextColor = TextHighLuxury,
              unfocusedTextColor = TextHighLuxury
            ),
            modifier = Modifier.fillMaxWidth()
          )
          OutlinedTextField(
            value = tempTitle,
            onValueChange = { tempTitle = it },
            label = { Text("VIP Title / Pedigree") },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = ImperialGold,
              unfocusedBorderColor = Lila,
              focusedTextColor = TextHighLuxury,
              unfocusedTextColor = TextHighLuxury
            ),
            modifier = Modifier.fillMaxWidth()
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.setName(tempName)
            viewModel.setVipTitle(tempTitle)
            showNameDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = NeonPink)
        ) {
          Text("Save")
        }
      },
      dismissButton = {
        TextButton(onClick = { showNameDialog = false }) {
          Text("Cancel", color = Lila)
        }
      },
      containerColor = CardSurface
    )
  }

  // DIALOG 2: VOGUE PAWS HIGH FASHION MAGAZINE COVER
  if (showMagazineDialog) {
    AlertDialog(
      onDismissRequest = { showMagazineDialog = false },
      title = null,
      text = {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ObsidianVelvet)
            .border(2.dp, ImperialGold, RoundedCornerShape(20.dp))
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "VOGUE PAWS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 4.sp,
            color = TextHighLuxury
          )
          Text(
            text = "CGE PANTHER COUTURE SPECIAL EDITION",
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = ImperialGold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(12.dp))

          // Avatar renderer preview
          CoutureAvatarRenderer(
            config = activeConfig,
            modifier = Modifier.size(240.dp),
            cornerRadius = 16.dp,
            showLabels = false,
            interactiveSparkles = false
          )

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = activeConfig.name.uppercase(),
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = NeonPink,
            letterSpacing = 2.sp
          )
          Text(
            text = activeConfig.vipTitle,
            fontSize = 11.sp,
            color = Lila,
            textAlign = TextAlign.Center
          )
          Text(
            text = "“THE MAXIMALIST PITBULL REVOLUTION”",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ImperialGold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
          )

          Spacer(modifier = Modifier.height(12.dp))
          Surface(
            color = CardSurface,
            shape = RoundedCornerShape(8.dp)
          ) {
            Text(
              text = "||| | ||||| || |||||| | |||| 940294-CGE",
              fontSize = 10.sp,
              color = Lila,
              letterSpacing = 2.sp,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.saveCurrentLook()
            showMagazineDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Save Magazine Look to Lookbook")
        }
      },
      containerColor = CardSurface
    )
  }
}
