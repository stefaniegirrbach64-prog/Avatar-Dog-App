package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.AccessoryItem
import com.example.model.AvatarBackground
import com.example.model.BaseEdition
import com.example.model.DesignerMonogram
import com.example.model.JacketColor
import com.example.model.LeoPattern
import com.example.model.OutfitFabric
import com.example.model.OutfitSilhouette
import com.example.model.OutfitTrim
import com.example.ui.DefaultDubaiStoryboardScenes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("No Sleep Still Rich Dogs", appName)
    assertTrue("App name must be <= 30 chars for Google Play compliance", appName.length <= 30)
  }

  @Test
  fun `verify avatar system counts`() {
    assertEquals("Must have 10 leo patterns", 10, LeoPattern.values().size)
    assertEquals("Must have 15 jacket colors", 15, JacketColor.values().size)
    assertEquals("Must have 20 accessories", 20, AccessoryItem.values().size)
    assertTrue("Must have at least 8 backgrounds including Dubai", AvatarBackground.values().size >= 8)
    assertTrue("Must have shop flagship edition", BaseEdition.values().any { it.id == "shop_flagship" })
  }

  @Test
  fun `verify grand opening outfit designer models`() {
    assertTrue("Must have luxury silhouettes", OutfitSilhouette.values().size >= 6)
    assertTrue("Must have luxury fabrics", OutfitFabric.values().size >= 5)
    assertTrue("Must have bespoke trims", OutfitTrim.values().size >= 5)
    assertTrue("Must have designer monograms", DesignerMonogram.values().size >= 5)
  }

  @Test
  fun `verify heydog dubai storyboard scenes`() {
    assertTrue("Must have Dubai storyboard scenes", DefaultDubaiStoryboardScenes.size >= 4)
    assertTrue("Must include Burj Khalifa scene", DefaultDubaiStoryboardScenes.any { it.id == "burj_khalifa_night" })
    assertTrue("Must include Dubai Yacht scene", DefaultDubaiStoryboardScenes.any { it.id == "dubai_yacht" })
    assertTrue("Must include Private Jet scene", DefaultDubaiStoryboardScenes.any { it.id == "dubai_jet" })
  }
}
