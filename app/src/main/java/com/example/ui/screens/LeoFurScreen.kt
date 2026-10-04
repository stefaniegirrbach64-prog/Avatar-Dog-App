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
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AvatarConfig
import com.example.model.BaseEdition
import com.example.model.LeoPattern
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
fun LeoFurScreen(
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
      border = androidx.compose.foundation.BorderStroke(1.dp, Violet)
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
            text = activeConfig.leoPattern.displayName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = ImperialGold
          )
          Text(
            text = activeConfig.leoPattern.description,
            fontSize = 11.sp,
            color = TextMutedPink,
            maxLines = 2
          )
        }
      }
    }

    // 1. BREED SILHOUETTE SELECTOR
    Text(
      text = "AMERICAN PITBULL SILHOUETTE",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp,
      color = ImperialGold,
      modifier = Modifier.padding(top = 4.dp, bottom = 6.dp)
    )
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      BaseEdition.values().forEach { edition ->
        val selected = activeConfig.baseEdition == edition
        FilterChip(
          selected = selected,
          onClick = { viewModel.setBaseEdition(edition) },
          label = {
            Text(
              text = when (edition) {
                BaseEdition.LEO_GLAM -> "Broad Head (Leo)"
                BaseEdition.PUNK_ROCK -> "Rebel Head (Punk)"
                BaseEdition.BOSSY_CHIC -> "Slender (Chic)"
                BaseEdition.SHOP_FLAGSHIP -> "Shop Flagship"
              },
              fontSize = 11.sp,
              fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
          },
          leadingIcon = {
            if (selected) {
              Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = ImperialGold
              )
            }
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = NeonPink,
            selectedLabelColor = Color.White,
            containerColor = DeepVioletSurface,
            labelColor = Lila
          ),
          border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selected,
            borderColor = if (selected) ImperialGold else Violet.copy(alpha = 0.5f)
          ),
          modifier = Modifier.weight(1f).testTag("edition_${edition.id}")
        )
      }
    }

    // 2. HANGING EARS TOGGLE
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      colors = CardDefaults.cardColors(containerColor = DeepVioletSurface),
      shape = RoundedCornerShape(14.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, Violet)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Hearing,
            contentDescription = null,
            tint = NeonPink,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = if (activeConfig.hangingEars)
              "Authentic Floppy Hanging Ears [ON]"
            else
              "Upright Cropped Ears [ON]",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextHighLuxury
          )
        }
        Switch(
          checked = activeConfig.hangingEars,
          onCheckedChange = { viewModel.toggleHangingEars() },
          colors = SwitchDefaults.colors(
            checkedThumbColor = ImperialGold,
            checkedTrackColor = NeonPink,
            uncheckedThumbColor = Lila,
            uncheckedTrackColor = CardSurface
          )
        )
      }
    }

    // 3. 10 LEO PATTERNS GRID (USER REQUIREMENT)
    Text(
      text = "10 SIGNATURE LEO PATTERNS",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp,
      color = ImperialGold,
      modifier = Modifier.padding(top = 4.dp, bottom = 6.dp)
    )

    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(bottom = 16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(LeoPattern.values()) { pattern ->
        val isSelected = activeConfig.leoPattern == pattern
        val pColor = Color(pattern.primaryHex)
        val sColor = Color(pattern.spotHex)

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.setLeoPattern(pattern) }
            .testTag("pattern_${pattern.id}"),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) DeepVioletSurface else CardSurface
          ),
          shape = RoundedCornerShape(16.dp),
          border = androidx.compose.foundation.BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) NeonPink else Lila.copy(alpha = 0.3f)
          )
        ) {
          Column(modifier = Modifier.padding(10.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              // Dual-color swatch representing base + leopard rosette
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(
                    Brush.radialGradient(listOf(pColor, sColor))
                  )
                  .border(1.5.dp, ImperialGold, CircleShape),
                contentAlignment = Alignment.Center
              ) {
                Text(text = "🐆", fontSize = 14.sp)
              }

              if (isSelected) {
                Surface(
                  color = NeonPink,
                  shape = CircleShape,
                  modifier = Modifier.size(20.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(3.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = pattern.displayName,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = TextHighLuxury,
              maxLines = 1
            )
            Text(
              text = pattern.description,
              fontSize = 10.sp,
              color = TextMutedPink,
              maxLines = 2,
              lineHeight = 12.sp,
              modifier = Modifier.padding(top = 2.dp)
            )
          }
        }
      }
    }
  }
}
