package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Reorder
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AccessoryCategory
import com.example.model.AccessoryItem
import com.example.model.AvatarConfig
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
fun AccessoriesScreen(
  viewModel: CoutureViewModel,
  activeConfig: AvatarConfig,
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf<AccessoryCategory?>(null) }
  var showArrangementPanel by remember { mutableStateOf(false) }

  val filteredAccessories = remember(selectedCategory) {
    if (selectedCategory == null) {
      AccessoryItem.values().toList()
    } else {
      AccessoryItem.values().filter { it.category == selectedCategory }
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // 1. TOP LIVE PREVIEW & CONTROLS
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 6.dp),
      colors = CardDefaults.cardColors(containerColor = CardSurface),
      shape = RoundedCornerShape(20.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold)
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
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "BLING CUSTOMIZATION",
              fontSize = 11.sp,
              fontWeight = FontWeight.ExtraBold,
              color = ImperialGold,
              letterSpacing = 1.sp
            )
            Surface(
              color = DeepVioletSurface,
              shape = RoundedCornerShape(6.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink)
            ) {
              Text(
                text = "${activeConfig.selectedAccessories.size} Layered",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = NeonPink,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
          Text(
            text = "Combine & Arrange Luxury Bling",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextHighLuxury,
            modifier = Modifier.padding(top = 2.dp)
          )
          Text(
            text = "Tiaras, sunglasses, diamond Cuban chains & collars",
            fontSize = 10.sp,
            color = TextMutedPink
          )
        }
      }
    }

    // 2. CURATED COMBINATION PRESETS BAR
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Button(
        onClick = { viewModel.equipMaximalistSet() },
        modifier = Modifier.weight(1f).height(36.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
      ) {
        Text("✨ All Bling", fontSize = 11.sp, color = ImperialGold, fontWeight = FontWeight.Bold)
      }
      Button(
        onClick = { viewModel.equipRebelPunkSet() },
        modifier = Modifier.weight(1f).height(36.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
      ) {
        Text("⚡ Rebel Punk", fontSize = 11.sp, color = NeonPink, fontWeight = FontWeight.Bold)
      }
      Button(
        onClick = { viewModel.equipSlenderChicSet() },
        modifier = Modifier.weight(1f).height(36.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Lila),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
      ) {
        Text("🎀 Bossy Chic", fontSize = 11.sp, color = Lila, fontWeight = FontWeight.Bold)
      }
    }

    // 3. ARRANGE & LAYER TOGGLE BUTTON
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedButton(
        onClick = { showArrangementPanel = !showArrangementPanel },
        modifier = Modifier.weight(1f).height(36.dp),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          if (showArrangementPanel) NeonPink else Lila.copy(alpha = 0.5f)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
          containerColor = if (showArrangementPanel) CardSurface else Color.Transparent
        )
      ) {
        Icon(
          imageVector = Icons.Default.Layers,
          contentDescription = null,
          modifier = Modifier.size(14.dp),
          tint = if (showArrangementPanel) NeonPink else Lila
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = if (showArrangementPanel) "Hide Layer Arrange" else "Arrange Layers (${activeConfig.selectedAccessories.size})",
          fontSize = 11.sp,
          color = if (showArrangementPanel) NeonPink else Lila,
          fontWeight = FontWeight.Bold
        )
      }

      if (activeConfig.selectedAccessories.isNotEmpty()) {
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
          onClick = { viewModel.clearAllAccessories() },
          modifier = Modifier.height(36.dp),
          shape = RoundedCornerShape(10.dp),
          border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.3f))
        ) {
          Text("Clear", fontSize = 11.sp, color = Lila)
        }
      }
    }

    // 4. EXPANDABLE LAYER ARRANGEMENT DRAWER
    AnimatedVisibility(visible = showArrangementPanel) {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink)
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Text(
            text = "ACCESSORY LAYER ORDER (TOP TO BOTTOM)",
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ImperialGold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(6.dp))

          if (activeConfig.selectedAccessories.isEmpty()) {
            Text(
              text = "No accessories equipped yet. Tap items below to add!",
              fontSize = 11.sp,
              color = TextMutedPink,
              modifier = Modifier.padding(vertical = 6.dp)
            )
          } else {
            activeConfig.selectedAccessories.forEachIndexed { index, accId ->
              val item = AccessoryItem.values().firstOrNull { it.id == accId }
              if (item != null) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .background(DeepVioletSurface, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Text(text = item.iconGlyph, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                      Text(
                        text = item.displayName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHighLuxury,
                        maxLines = 1
                      )
                      Text(
                        text = item.category.label,
                        fontSize = 9.sp,
                        color = Lila
                      )
                    }
                  }

                  Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                      onClick = { viewModel.moveAccessoryUp(index) },
                      enabled = index > 0,
                      modifier = Modifier.size(28.dp)
                    ) {
                      Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Move Up",
                        tint = if (index > 0) ImperialGold else Color(0x33FFFFFF),
                        modifier = Modifier.size(16.dp)
                      )
                    }
                    IconButton(
                      onClick = { viewModel.moveAccessoryDown(index) },
                      enabled = index < activeConfig.selectedAccessories.size - 1,
                      modifier = Modifier.size(28.dp)
                    ) {
                      Icon(
                        imageVector = Icons.Default.ArrowDownward,
                        contentDescription = "Move Down",
                        tint = if (index < activeConfig.selectedAccessories.size - 1) ImperialGold else Color(0x33FFFFFF),
                        modifier = Modifier.size(16.dp)
                      )
                    }
                    IconButton(
                      onClick = { viewModel.removeAccessory(accId) },
                      modifier = Modifier.size(28.dp)
                    ) {
                      Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove",
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
      }
    }

    // 5. CATEGORY FILTER CHIPS
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier.padding(vertical = 4.dp)
    ) {
      item {
        FilterChip(
          selected = selectedCategory == null,
          onClick = { selectedCategory = null },
          label = { Text("All (${AccessoryItem.values().size})", fontSize = 11.sp) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = NeonPink,
            selectedLabelColor = Color.White,
            containerColor = DeepVioletSurface,
            labelColor = Lila
          ),
          border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selectedCategory == null,
            borderColor = if (selectedCategory == null) ImperialGold else Violet.copy(alpha = 0.5f)
          )
        )
      }
      items(AccessoryCategory.values()) { cat ->
        val selected = selectedCategory == cat
        FilterChip(
          selected = selected,
          onClick = { selectedCategory = cat },
          label = { Text(cat.label, fontSize = 11.sp) },
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
          )
        )
      }
    }

    // 6. ACCESSORIES GRID
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(bottom = 16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(filteredAccessories) { acc ->
        val isEquipped = activeConfig.selectedAccessories.contains(acc.id)
        val accentColor = Color(acc.colorAccent)

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.toggleAccessory(acc.id) }
            .testTag("acc_${acc.id}"),
          colors = CardDefaults.cardColors(
            containerColor = if (isEquipped) DeepVioletSurface else CardSurface
          ),
          shape = RoundedCornerShape(16.dp),
          border = androidx.compose.foundation.BorderStroke(
            if (isEquipped) 2.dp else 1.dp,
            if (isEquipped) NeonPink else Lila.copy(alpha = 0.25f)
          )
        ) {
          Column(modifier = Modifier.padding(10.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                color = DeepVioletSurface,
                shape = CircleShape,
                border = androidx.compose.foundation.BorderStroke(1.dp, accentColor),
                modifier = Modifier.size(36.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(text = acc.iconGlyph, fontSize = 18.sp)
                }
              }

              Surface(
                color = if (isEquipped) NeonPink else Color(0x33C8A2C8),
                shape = CircleShape,
                modifier = Modifier.size(24.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  if (isEquipped) {
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

            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = acc.displayName,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = TextHighLuxury,
              maxLines = 1
            )
            Text(
              text = acc.description,
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
