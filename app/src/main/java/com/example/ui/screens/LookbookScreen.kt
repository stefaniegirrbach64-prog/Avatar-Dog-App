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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CoutureAvatarEntity
import com.example.ui.CoutureViewModel
import com.example.ui.StudioTab
import com.example.ui.components.CoutureAvatarRenderer
import com.example.ui.theme.CardSurface
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.Lila
import com.example.ui.theme.NeonPink
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.TextMutedPink
import com.example.ui.theme.Violet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LookbookScreen(
  viewModel: CoutureViewModel,
  modifier: Modifier = Modifier
) {
  val savedAvatars by viewModel.savedAvatars.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Lookbook Header
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "CGE VIP LOOKBOOK",
          fontSize = 11.sp,
          fontWeight = FontWeight.ExtraBold,
          letterSpacing = 2.sp,
          color = ImperialGold
        )
        Text(
          text = "SAVED COUTURE AVATARS",
          fontSize = 18.sp,
          fontWeight = FontWeight.Black,
          color = TextHighLuxury
        )
      }

      Surface(
        color = CardSurface,
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink)
      ) {
        Text(
          text = "${savedAvatars.size} Looks",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = NeonPink,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }

    if (savedAvatars.isEmpty()) {
      // Empty state
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        contentAlignment = Alignment.Center
      ) {
        Card(
          colors = CardDefaults.cardColors(containerColor = CardSurface),
          shape = RoundedCornerShape(20.dp),
          border = androidx.compose.foundation.BorderStroke(1.dp, Lila.copy(alpha = 0.3f)),
          modifier = Modifier.padding(24.dp)
        ) {
          Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(text = "👑", fontSize = 42.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = "No Saved Couture Looks Yet",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = TextHighLuxury,
              textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Design your custom Pitbull in the Runway studio with maximalist leopard print, neon pink outfits, and gold chains, then tap 'Save Look'!",
              fontSize = 12.sp,
              color = TextMutedPink,
              textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
              onClick = { viewModel.setTab(StudioTab.RUNWAY) },
              colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text("Go to Runway Studio")
            }
          }
        }
      }
    } else {
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp),
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
      ) {
        items(savedAvatars, key = { it.id }) { entity ->
          val config = entity.toAvatarConfig()
          val dateStr = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
            .format(Date(entity.createdAt))

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("saved_avatar_${entity.id}"),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            shape = RoundedCornerShape(20.dp),
            border = androidx.compose.foundation.BorderStroke(
              1.2.dp,
              if (entity.isFavorite) ImperialGold else Violet.copy(alpha = 0.5f)
            )
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
              ) {
                // Miniature Avatar Renderer
                CoutureAvatarRenderer(
                  config = config,
                  modifier = Modifier
                    .size(92.dp)
                    .clickable { viewModel.loadSavedAvatar(entity) },
                  cornerRadius = 14.dp,
                  showLabels = false,
                  interactiveSparkles = false
                )

                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = entity.name,
                      fontSize = 16.sp,
                      fontWeight = FontWeight.Bold,
                      color = TextHighLuxury,
                      maxLines = 1,
                      modifier = Modifier.weight(1f)
                    )
                    IconButton(
                      onClick = { viewModel.toggleFavorite(entity) },
                      modifier = Modifier.size(32.dp)
                    ) {
                      Icon(
                        imageVector = if (entity.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (entity.isFavorite) ImperialGold else Lila,
                        modifier = Modifier.size(18.dp)
                      )
                    }
                  }

                  Text(
                    text = entity.vipTitle,
                    fontSize = 11.sp,
                    color = Lila,
                    maxLines = 1
                  )

                  Text(
                    text = "${config.leoPattern.displayName} • ${config.jacketColor.displayName}",
                    fontSize = 10.sp,
                    color = ImperialGold,
                    maxLines = 1,
                    modifier = Modifier.padding(top = 4.dp)
                  )

                  Text(
                    text = if (entity.hangingEars) "Floppy Hanging Ears" else "Upright Cropped Ears",
                    fontSize = 10.sp,
                    color = TextMutedPink
                  )

                  Text(
                    text = "Saved $dateStr",
                    fontSize = 9.sp,
                    color = Lila.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 2.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(10.dp))
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Button(
                  onClick = { viewModel.loadSavedAvatar(entity) },
                  modifier = Modifier.weight(1f),
                  colors = ButtonDefaults.buttonColors(containerColor = DeepVioletSurface),
                  border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink),
                  shape = RoundedCornerShape(10.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = ImperialGold
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    "Wear in Studio",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighLuxury
                  )
                }

                IconButton(
                  onClick = { viewModel.deleteAvatar(entity) },
                  modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DeepVioletSurface)
                ) {
                  Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Look",
                    tint = Lila,
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
