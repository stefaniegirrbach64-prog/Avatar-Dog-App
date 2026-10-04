package com.example.ui.screens

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AvatarConfig
import com.example.model.JacketColor
import com.example.ui.CoutureViewModel
import com.example.ui.components.CoutureAvatarRenderer
import com.example.ui.theme.CardSurface
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet

@Composable
fun JacketScreen(
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
      border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink)
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
            text = activeConfig.jacketColor.displayName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = NeonPink
          )
          Text(
            text = "Texture: ${activeConfig.jacketColor.textureType}",
            fontSize = 12.sp,
            color = ImperialGold
          )
          Text(
            text = "High-fashion tailored canine couture fit with precision seams",
            fontSize = 10.sp,
            color = TextMutedPink,
            maxLines = 1
          )
        }
      }
    }

    // Header & User Color Scheme Highlight
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp, bottom = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "15 LUXURY JACKET COLORS",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        color = ImperialGold
      )
      Text(
        text = "Neon & Violet Haute Couture",
        fontSize = 11.sp,
        color = Lila
      )
    }

    // 15 JACKET COLORS GRID
    LazyVerticalGrid(
      columns = GridCells.Fixed(3),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(bottom = 16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(JacketColor.values()) { jacket ->
        val isSelected = activeConfig.jacketColor == jacket
        val jacketColor = Color(jacket.colorHex)

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.setJacketColor(jacket) }
            .testTag("jacket_${jacket.id}"),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) DeepVioletSurface else CardSurface
          ),
          shape = RoundedCornerShape(16.dp),
          border = androidx.compose.foundation.BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) ImperialGold else Lila.copy(alpha = 0.3f)
          )
        ) {
          Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Box(
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(jacketColor)
                .border(
                  width = 2.dp,
                  color = if (isSelected) ImperialGold else Color(0x55FFFFFF),
                  shape = CircleShape
                ),
              contentAlignment = Alignment.Center
            ) {
              if (isSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = if (jacket == JacketColor.METALLIC_GOLD || jacket == JacketColor.DIAMOND_SILVER) Color.Black else Color.White,
                  modifier = Modifier.size(18.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = jacket.displayName.substringBefore(" #"),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = TextHighLuxury,
              maxLines = 1
            )
            Surface(
              color = DeepVioletSurface,
              shape = RoundedCornerShape(4.dp),
              modifier = Modifier.padding(top = 2.dp)
            ) {
              Text(
                text = jacket.textureType.substringBefore(" "),
                fontSize = 9.sp,
                color = Lila,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
        }
      }
    }
  }
}
