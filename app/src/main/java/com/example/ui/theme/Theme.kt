package com.example.ui.theme

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val LuxuryCoutureColorScheme = darkColorScheme(
  primary = NeonPink,
  onPrimary = Color.White,
  primaryContainer = Color(0xFF881250),
  onPrimaryContainer = Color(0xFFFFD8E6),
  secondary = Violet,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFF6B1CAE),
  onSecondaryContainer = Color(0xFFECD8FF),
  tertiary = ImperialGold,
  onTertiary = Color.Black,
  tertiaryContainer = Color(0xFF7A6000),
  onTertiaryContainer = Color(0xFFFFE082),
  background = JoyfulVioletBg,
  onBackground = TextHighLuxury,
  surface = DeepVioletSurface,
  onSurface = TextHighLuxury,
  surfaceVariant = CardSurface,
  onSurfaceVariant = TextMutedPink,
  outline = RoseGold.copy(alpha = 0.7f),
  outlineVariant = ImperialGold.copy(alpha = 0.5f)
)

/**
 * Joyful Brand Gradient Modifier - "MORE IS MORE"
 * Cheerful rich gradient from Radiant Violet through Electric Neon Pink to Rose Gold & Imperial Gold.
 */
fun Modifier.joyfulBrandBackground(): Modifier = this.background(
  Brush.verticalGradient(
    colors = listOf(
      JoyfulVioletBg,
      Color(0xFF410E63),
      Color(0xFF501275),
      Color(0xFF380C55)
    )
  )
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = LuxuryCoutureColorScheme,
    typography = Typography,
    content = content
  )
}
