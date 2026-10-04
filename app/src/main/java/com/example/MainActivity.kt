package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.CoutureViewModel
import com.example.ui.StudioTab
import com.example.ui.screens.AccessoriesScreen
import com.example.ui.screens.BackgroundsScreen
import com.example.ui.screens.HeyDogStudioScreen
import com.example.ui.screens.JacketScreen
import com.example.ui.screens.LeoFurScreen
import com.example.ui.screens.LookbookScreen
import com.example.ui.screens.OutfitDesignerScreen
import com.example.ui.screens.RunwayScreen
import com.example.ui.theme.CardSurface
import com.example.ui.theme.DeepVioletSurface
import com.example.ui.theme.HotNeonPink
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.JoyfulVioletBg
import com.example.ui.theme.Lila
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NeonPink
import com.example.ui.theme.RoseGold
import com.example.ui.theme.RoseGoldGlow
import com.example.ui.theme.TextHighLuxury
import com.example.ui.theme.Violet
import com.example.ui.theme.joyfulBrandBackground

class MainActivity : ComponentActivity() {
  private val viewModel: CoutureViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        CoutureStudioApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun CoutureStudioApp(viewModel: CoutureViewModel) {
  val activeTab by viewModel.activeTab.collectAsState()
  val activeConfig by viewModel.activeConfig.collectAsState()
  val snackbarMsg by viewModel.snackbarMessage.collectAsState()
  val snackbarHostState = remember { SnackbarHostState() }

  // Back handling: If on secondary tab, return to Runway
  BackHandler(enabled = activeTab != StudioTab.RUNWAY) {
    viewModel.setTab(StudioTab.RUNWAY)
  }

  // Handle snackbar messages
  LaunchedEffect(snackbarMsg) {
    snackbarMsg?.let {
      snackbarHostState.showSnackbar(it)
      viewModel.clearSnackbar()
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .joyfulBrandBackground(),
    containerColor = JoyfulVioletBg,
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    snackbarHost = {
      SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(bottom = 80.dp)
      )
    },
    topBar = {
      CoutureTopBar(
        onOpenShop = { viewModel.setTab(StudioTab.OUTFIT_DESIGNER) },
        onOpenHeyDog = { viewModel.setTab(StudioTab.HEY_DOG) }
      )
    },
    bottomBar = {
      CoutureNavigationBar(
        activeTab = activeTab,
        onTabSelected = { viewModel.setTab(it) }
      )
    }
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      AnimatedContent(
        targetState = activeTab,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "TabContentAnimation"
      ) { tab ->
        when (tab) {
          StudioTab.RUNWAY -> RunwayScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.HEY_DOG -> HeyDogStudioScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.OUTFIT_DESIGNER -> OutfitDesignerScreen(viewModel = viewModel)
          StudioTab.LEO_PATTERNS -> LeoFurScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.JACKET_COLORS -> JacketScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.ACCESSORIES -> AccessoriesScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.BACKGROUNDS -> BackgroundsScreen(viewModel = viewModel, activeConfig = activeConfig)
          StudioTab.LOOKBOOK -> LookbookScreen(viewModel = viewModel)
        }
      }
    }
  }
}

@Composable
fun CoutureTopBar(
  onOpenShop: () -> Unit = {},
  onOpenHeyDog: () -> Unit = {}
) {
  Column(modifier = Modifier.fillMaxWidth()) {
    // 1. TOP ANNOUNCEMENT BAR (MATCHING BILD 2: PREVIEW EDITION 01 • EU · EUR)
    Surface(
      color = HotNeonPink,
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "PREVIEW EDITION 01 • EU · EUR",
          color = Color.White,
          fontSize = 10.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 2.sp
        )
      }
    }

    // 2. MAIN BRAND HEADER
    Surface(
      color = DeepVioletSurface,
      modifier = Modifier
        .fillMaxWidth()
        .shadow(10.dp, ambientColor = NeonPink, spotColor = ImperialGold)
        .border(
          width = 1.5.dp,
          brush = Brush.horizontalGradient(listOf(NeonPink, ImperialGold, RoseGold, Violet, NeonPink)),
          shape = androidx.compose.ui.graphics.RectangleShape
        )
    ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(
              Brush.radialGradient(listOf(ImperialGold, NeonPink, RoseGold))
            ),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "👑", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "NO SLEEP STILL RICH",
              fontSize = 12.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 1.sp,
              color = TextHighLuxury
            )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
              color = NeonPink,
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "MORE IS MORE",
                color = Color.White,
                fontSize = 7.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
          Text(
            text = "Dubai Storyboards & Haute Couture",
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGoldGlow
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        // HeyDog Studio quick button
        Surface(
          color = CardSurface,
          shape = RoundedCornerShape(10.dp),
          border = androidx.compose.foundation.BorderStroke(1.dp, RoseGold),
          modifier = Modifier
            .clickable { onOpenHeyDog() }
            .testTag("top_heydog_badge")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = "🎬", fontSize = 10.sp)
            Spacer(modifier = Modifier.width(3.dp))
            Text(
              text = "HEYDOG",
              fontSize = 9.sp,
              fontWeight = FontWeight.ExtraBold,
              color = RoseGoldGlow,
              letterSpacing = 0.5.sp
            )
          }
        }

        // Shop open button
        Surface(
          color = CardSurface,
          shape = RoundedCornerShape(10.dp),
          border = androidx.compose.foundation.BorderStroke(1.dp, ImperialGold),
          modifier = Modifier
            .clickable { onOpenShop() }
            .testTag("top_grand_opening_badge")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = "🎉", fontSize = 10.sp)
            Spacer(modifier = Modifier.width(3.dp))
            Text(
              text = "SHOP",
              fontSize = 9.sp,
              fontWeight = FontWeight.ExtraBold,
              color = ImperialGold,
              letterSpacing = 0.5.sp
            )
          }
        }
      }
    }
  }
  }
}

@Composable
fun CoutureNavigationBar(
  activeTab: StudioTab,
  onTabSelected: (StudioTab) -> Unit
) {
  NavigationBar(
    containerColor = DeepVioletSurface,
    tonalElevation = 8.dp,
    modifier = Modifier
      .fillMaxWidth()
      .navigationBarsPadding()
      .shadow(16.dp, spotColor = Violet, ambientColor = NeonPink)
      .border(
        width = 1.5.dp,
        brush = Brush.horizontalGradient(listOf(Violet, ImperialGold, NeonPink, RoseGold, Violet)),
        shape = androidx.compose.ui.graphics.RectangleShape
      )
      .testTag("couture_navigation_bar")
  ) {
    StudioTab.values().forEach { tab ->
      val isSelected = activeTab == tab
      NavigationBarItem(
        selected = isSelected,
        onClick = { onTabSelected(tab) },
        icon = {
          Text(
            text = tab.icon,
            fontSize = if (isSelected) 18.sp else 15.sp
          )
        },
        label = {
          Text(
            text = tab.title,
            fontSize = 9.sp,
            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
            maxLines = 1
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = ImperialGold,
          selectedTextColor = ImperialGold,
          indicatorColor = CardSurface,
          unselectedIconColor = Lila,
          unselectedTextColor = Lila
        ),
        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
      )
    }
  }
}
