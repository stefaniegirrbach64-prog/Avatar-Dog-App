package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AvatarBackground
import com.example.model.AvatarConfig
import com.example.ui.CoutureViewModel
import com.example.ui.components.CoutureAvatarRenderer
import com.example.ui.theme.CardSurface
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.PinkBokeh
import com.example.ui.theme.RoseGold
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet
import com.example.ui.theme.VioletSparkle

@Composable
fun BackgroundsScreen(
  viewModel: CoutureViewModel,
  activeConfig: AvatarConfig,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Top Live Preview Card (compact)
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      colors = CardDefaults.cardColors(containerColor = CardSurface),
      shape = RoundedCornerShape(20.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, RoseGold)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        CoutureAvatarRenderer(
          config = activeConfig,
          modifier = Modifier.size(80.dp),
          cornerRadius = 14.dp,
          showLabels = false,
          interactiveSparkles = false
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = activeConfig.background.displayName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = ImperialGold
          )
          Text(
            text = "Active Haute Couture Stage",
            fontSize = 12.sp,
            color = NeonPink
          )
          Text(
            text = "Bokeh particles and diamond glints shimmer in real-time",
            fontSize = 10.sp,
            color = TextMutedPink
          )
        }
      }
    }

    // Official Palette Banner - "MORE IS MORE"
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      colors = CardDefaults.cardColors(containerColor = DeepVioletSurface),
      shape = RoundedCornerShape(14.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold.copy(alpha = 0.5f))
    ) {
      Column(modifier = Modifier.padding(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "OFFICIAL BRAND PALETTE (MORE IS MORE)",
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.sp,
            color = ImperialGold
          )
          Text(
            text = "JOYFUL LUXURY",
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            color = NeonPink
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          PaletteSwatch("Violet", "#8A2BE2", Violet)
          PaletteSwatch("Neon Pink", "#FF1493", NeonPink)
          PaletteSwatch("Gold", "#FFD700", ImperialGold)
          PaletteSwatch("Rose Gold", "#E8A598", RoseGold)
          PaletteSwatch("Glitter", "Sparkles", PinkBokeh)
        }
      }
    }

    Text(
      text = "COUTURE & DUBAI STORYBOARD BACKDROPS",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp,
      color = ImperialGold,
      modifier = Modifier.padding(top = 8.dp, bottom = 6.dp)
    )

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(bottom = 24.dp),
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(AvatarBackground.values()) { bg ->
        val isSelected = activeConfig.background == bg
        val cTop = Color(bg.colorTop)
        val cBottom = Color(bg.colorBottom)

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.setBackground(bg) }
            .testTag("bg_${bg.id}"),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) DeepVioletSurface else CardSurface
          ),
          shape = RoundedCornerShape(16.dp),
          border = androidx.compose.foundation.BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) NeonPink else Lila.copy(alpha = 0.25f)
          )
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Background preview thumbnail
            Box(
              modifier = Modifier
                .size(54.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.5.dp, if (isSelected) NeonPink else ImperialGold, RoundedCornerShape(12.dp)),
              contentAlignment = Alignment.Center
            ) {
              if (bg.sceneDrawableRes != null) {
                Image(
                  painter = painterResource(id = bg.sceneDrawableRes),
                  contentDescription = bg.displayName,
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Crop
                )
              } else {
                Box(
                  modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(cTop, cBottom)))
                )
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(24.dp)
                )
              }
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = bg.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextHighLuxury
              )
              Text(
                text = when (bg) {
                  AvatarBackground.VIOLET_GLITTER -> "Deep ultraviolet backdrop with twinkling violet stardust (#8A2BE2)"
                  AvatarBackground.PINK_GLITTER -> "Radiant neon pink aura with energetic bokeh sparkles (#FF1493)"
                  AvatarBackground.LILA_BOKEH -> "Soft luminous lila atmosphere with delicate light spheres (#C8A2C8)"
                  AvatarBackground.LEOPARD_PRINT -> "Maximalist couture gold & magenta designer pattern"
                  AvatarBackground.SHOP_INTERIOR -> "The ultra-luxe CGE Panther boutique & runway salon"
                  AvatarBackground.DUBAI_YACHT -> "Sunset on Dubai Marina superyacht with rose gold reflections"
                  AvatarBackground.DUBAI_JET -> "Bespoke Gulfstream G700 private jet cabin with cream & gold leather"
                  AvatarBackground.BURJ_KHALIFA_NIGHT -> "Burj Khalifa at midnight with neon pink lasers & Dubai fountains"
                },
                fontSize = 11.sp,
                color = TextMutedPink,
                maxLines = 2
              )
            }

            if (isSelected) {
              Surface(
                color = NeonPink,
                shape = CircleShape,
                modifier = Modifier.size(24.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun PaletteSwatch(name: String, hex: String, color: Color) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Box(
      modifier = Modifier
        .size(26.dp)
        .clip(CircleShape)
        .background(color)
        .border(1.dp, ImperialGold, CircleShape)
    )
    Text(
      text = name,
      fontSize = 9.sp,
      fontWeight = FontWeight.Bold,
      color = TextHighLuxury,
      modifier = Modifier.padding(top = 2.dp)
    )
    Text(
      text = hex,
      fontSize = 8.sp,
      color = Lila
    )
  }
}
