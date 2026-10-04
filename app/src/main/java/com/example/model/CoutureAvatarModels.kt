package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

/**
 * Signature Base Editions of the American Pitbull Terrier Avatar
 */
enum class BaseEdition(
  val id: String,
  val title: String,
  val subtitle: String,
  @DrawableRes val imageRes: Int,
  val tag: String
) {
  LEO_GLAM(
    id = "leo_glam",
    title = "Maximalist Leo Glam",
    subtitle = "Broad head, leopard fur, pink strass & chunky diamond gold chain",
    imageRes = R.drawable.img_pitbull_leo_glam,
    tag = "Signature Haute Couture"
  ),
  PUNK_ROCK(
    id = "punk_rock",
    title = "Rebel Rockstar Chic",
    subtitle = "Black leather jacket, neon pink patches, lila mohawk & piercings",
    imageRes = R.drawable.img_pitbull_punk_rock,
    tag = "Punk-Rock Luxury"
  ),
  BOSSY_CHIC(
    id = "bossy_chic",
    title = "Bossy Chic Slender",
    subtitle = "Slender silhouette, crystal bow, violet silk scarf & soft bokeh",
    imageRes = R.drawable.img_pitbull_bossy_chic,
    tag = "High-Fashion Slender"
  ),
  SHOP_FLAGSHIP(
    id = "shop_flagship",
    title = "Official Shop Flagship Emblem",
    subtitle = "Half 24K Baroque filigree gold, half black leopard with diamond eyes & spiked collar",
    imageRes = R.drawable.app_shop_icon,
    tag = "Shop Icon Signature"
  )
}

/**
 * 10 Leo Patterns requested by user
 */
enum class LeoPattern(
  val id: String,
  val displayName: String,
  val primaryHex: Long,
  val spotHex: Long,
  val description: String
) {
  GOLD_LEO(
    id = "gold_leo",
    displayName = "Imperial Gold Leo",
    primaryHex = 0xFFFFD700,
    spotHex = 0xFF8B5A00,
    description = "Authentic royal gold base with deep amber leopard rosettes"
  ),
  PINK_LEO(
    id = "pink_leo",
    displayName = "Neon Pink Leo",
    primaryHex = 0xFFFF1493,
    spotHex = 0xFF88004B,
    description = "Electric neon pink fur with fuchsia gradient leopard spots"
  ),
  VIOLET_LEO(
    id = "violet_leo",
    displayName = "Ultraviolet Leo",
    primaryHex = 0xFF8A2BE2,
    spotHex = 0xFF4B0082,
    description = "Deep violet velvet fur with midnight amethyst rosettes"
  ),
  NEON_CYBER_LEO(
    id = "neon_cyber_leo",
    displayName = "Cyber Neon Leo",
    primaryHex = 0xFFFF007F,
    spotHex = 0xFF00F0FF,
    description = "Fluorescent magenta fur with electrified cyan panther rosettes"
  ),
  MIDNIGHT_BLACK_LEO(
    id = "midnight_black_leo",
    displayName = "Midnight Panther Leo",
    primaryHex = 0xFF181520,
    spotHex = 0xFF352E46,
    description = "Glossy shadow panther fur with obsidian holographic spots"
  ),
  ROSE_GOLD_LEO(
    id = "rose_gold_leo",
    displayName = "Rose Gold Leo",
    primaryHex = 0xFFB76E79,
    spotHex = 0xFF632B34,
    description = "Metallic champagne rose fur with bronze rhinestone edges"
  ),
  ICED_DIAMOND_LEO(
    id = "iced_diamond_leo",
    displayName = "Iced Diamond Leo",
    primaryHex = 0xFFE0F7FA,
    spotHex = 0xFF80DEEA,
    description = "Glacier crystal white fur with prismatic diamond rosettes"
  ),
  EMERALD_NEON_LEO(
    id = "emerald_neon_leo",
    displayName = "Viper Emerald Leo",
    primaryHex = 0xFF00FA9A,
    spotHex = 0xFF8A2BE2,
    description = "Luxe neon viper green fur with royal violet rosettes"
  ),
  PLATINUM_SILVER_LEO(
    id = "platinum_silver_leo",
    displayName = "Liquid Platinum Leo",
    primaryHex = 0xFFE5E4E2,
    spotHex = 0xFF70757A,
    description = "High-shine liquid chrome fur with dark platinum leopard spots"
  ),
  HOLO_RAINBOW_LEO(
    id = "holo_rainbow_leo",
    displayName = "Holographic Glam Leo",
    primaryHex = 0xFFFF69B4,
    spotHex = 0xFF7A00E6,
    description = "Opalescent multi-spectrum rosettes with shifting iridescence"
  )
}

/**
 * 15 Jacket Colors requested by user
 */
enum class JacketColor(
  val id: String,
  val displayName: String,
  val colorHex: Long,
  val textureType: String
) {
  NEON_PINK("neon_pink", "Neon Pink #FF1493", 0xFFFF1493, "High-Gloss Patent"),
  VIOLENT_PURPLE("violet", "Violet #8A2BE2", 0xFF8A2BE2, "Royal Plush Velvet"),
  LILA("lila", "Lila #C8A2C8", 0xFFC8A2C8, "Metallic Satin"),
  JET_REBEL_BLACK("black_rebel", "Jet Rebel Black", 0xFF121118, "Biker Studded Leather"),
  METALLIC_GOLD("gold", "Imperial Gold #FFD700", 0xFFFFD700, "24K Gold Foil"),
  ELECTRIC_MAGENTA("magenta", "Electric Magenta", 0xFFE0115F, "Metallic Croc Finish"),
  CYBER_PURPLE("cyber_purple", "Cyber Purple", 0xFF7B1FA2, "Matte Quilted Leather"),
  ROYAL_AMETHYST("amethyst", "Royal Amethyst", 0xFF9932CC, "Crushed Velvet"),
  GLOSSY_ROSE("glossy_rose", "Glossy Rose #FF69B4", 0xFFFF69B4, "Latex High Shine"),
  PUNK_LAVENDER("lavender", "Punk Lavender", 0xFFDDA0DD, "Distressed Denim Leather"),
  DEEP_VELVET_WINE("velvet_wine", "Deep Velvet Wine", 0xFF4A0E2E, "Embossed Velvet"),
  DIAMOND_SILVER("diamond_silver", "Diamond Silver", 0xFFE0E6ED, "Reflective Chrome"),
  MIDNIGHT_NAVY("midnight_navy", "Midnight Navy Luxe", 0xFF0D1B2A, "Silk Twill"),
  ACID_LIME_NEON("acid_neon", "Acid Lime Neon", 0xFF39FF14, "Glow Fluorescent"),
  ROSE_GOLD_CHAMPAGNE("rose_gold", "Rose Gold Glam", 0xFFB76E79, "Glitter Brocade")
}

/**
 * Accessory Categories
 */
enum class AccessoryCategory(val label: String) {
  HEAD("Headwear & Tiaras"),
  NECK("Chains & Collars"),
  FACE("Eyewear & Makeup"),
  BODY("Patches & Harness"),
  BLING("Diamonds & Piercings")
}

/**
 * Exact 20 Luxury Accessories:
 * - gold chains with pink diamonds
 * - piercings with pink diamonds
 * - minimal gold necklace
 * - oversized sunglasses
 * - designer dog collars
 * - jeweled tiaras
 * - and signature accessories
 */
enum class AccessoryItem(
  val id: String,
  val displayName: String,
  val category: AccessoryCategory,
  val description: String,
  val iconGlyph: String,
  val colorAccent: Long
) {
  // 1-4: Head & Tiaras
  JEWELED_TIARA(
    id = "jeweled_tiara",
    displayName = "Jeweled Tiara",
    category = AccessoryCategory.HEAD,
    description = "Crown of marquise-cut pink diamonds and solid platinum arches",
    iconGlyph = "👑",
    colorAccent = 0xFFFF1493
  ),
  PINK_DIAMOND_CROWN(
    id = "pink_diamond_crown",
    displayName = "Pink Diamond Crown",
    category = AccessoryCategory.HEAD,
    description = "Solid gold royal crown crusted with large pink diamonds & rubies",
    iconGlyph = "👑",
    colorAccent = 0xFFFFD700
  ),
  LILA_MOHAWK_GLITTER(
    id = "lila_mohawk",
    displayName = "Lila Mohawk Glitter",
    category = AccessoryCategory.HEAD,
    description = "Spun sugar punk-rock mohawk infused with violet and silver sparkle",
    iconGlyph = "⚡",
    colorAccent = 0xFFC8A2C8
  ),
  LIGHT_PINK_BOW(
    id = "pink_bow_strass",
    displayName = "Light Pink Bow with Strass",
    category = AccessoryCategory.HEAD,
    description = "Silk satin blush bow accented by an emerald-cut crystal centerpiece",
    iconGlyph = "🎀",
    colorAccent = 0xFFFFB6C1
  ),

  // 5-9: Neck & Chains & Collars
  GOLD_CHAINS_PINK_DIAMONDS(
    id = "gold_chains_diamonds",
    displayName = "Gold Chains with Pink Diamonds",
    category = AccessoryCategory.NECK,
    description = "Heavy Miami Cuban links in 24k gold with pavé pink diamond clasps",
    iconGlyph = "⛓️",
    colorAccent = 0xFFFFD700
  ),
  MINIMAL_GOLD_NECKLACE(
    id = "minimal_gold_necklace",
    displayName = "Minimal Gold Necklace",
    category = AccessoryCategory.NECK,
    description = "Dainty dual-strand gold chain with a micro CGE engraved medallion",
    iconGlyph = "🪙",
    colorAccent = 0xFFFFD700
  ),
  DESIGNER_DOG_COLLAR(
    id = "designer_dog_collar",
    displayName = "Designer Dog Collar",
    category = AccessoryCategory.NECK,
    description = "Neon pink padded Italian leather collar with gold monogram hardware",
    iconGlyph = "✨",
    colorAccent = 0xFFFF1493
  ),
  SPIKED_GOLD_COLLAR(
    id = "spiked_gold_collar",
    displayName = "Spiked Gold Diamond Collar",
    category = AccessoryCategory.NECK,
    description = "24K solid gold collar loaded with pyramidal spikes and inset diamonds",
    iconGlyph = "🌟",
    colorAccent = 0xFFFFD700
  ),
  VIOLET_SILK_SCARF(
    id = "violet_silk_scarf",
    displayName = "Violet Silk Scarf",
    category = AccessoryCategory.NECK,
    description = "Parisian couture hand-rolled silk twill scarf in radiant violet",
    iconGlyph = "🧣",
    colorAccent = 0xFF8A2BE2
  ),

  // 10-13: Eyewear & Face
  OVERSIZED_SUNGLASSES(
    id = "oversized_sunglasses",
    displayName = "Oversized Sunglasses",
    category = AccessoryCategory.FACE,
    description = "Black gradient shield sunglasses with pavé diamond gold temples",
    iconGlyph = "🕶️",
    colorAccent = 0xFFFFD700
  ),
  SUNGLASSES_ON_HEAD(
    id = "sunglasses_head",
    displayName = "Sunglasses on Head",
    category = AccessoryCategory.HEAD,
    description = "Oversized gold aviator shades pushed casually up onto the forehead",
    iconGlyph = "🕶️",
    colorAccent = 0xFFFFD700
  ),
  RETRO_CAT_EYE_SHADES(
    id = "retro_cat_eye",
    displayName = "Retro Cat-Eye Sunglasses",
    category = AccessoryCategory.FACE,
    description = "Neon pink rhinestone-framed vintage cat-eye designer sunglasses",
    iconGlyph = "👓",
    colorAccent = 0xFFFF1493
  ),
  STRASS_FOREHEAD_MAKEUP(
    id = "strass_forehead",
    displayName = "Strass Forehead Makeup",
    category = AccessoryCategory.FACE,
    description = "Delicate pink rhinestone strass bindi & starburst crystals on brow",
    iconGlyph = "✨",
    colorAccent = 0xFFFF69B4
  ),

  // 14-17: Piercings & Bling
  PIERCINGS_PINK_DIAMONDS(
    id = "piercings_pink_diamonds",
    displayName = "Piercings with Pink Diamonds",
    category = AccessoryCategory.BLING,
    description = "Triple hoop ear piercings in solid gold with dangling pink diamond droplets",
    iconGlyph = "💎",
    colorAccent = 0xFFFF1493
  ),
  RHINESTONE_EAR_CUFFS(
    id = "rhinestone_ear_cuffs",
    displayName = "Rhinestone Ear Cuffs",
    category = AccessoryCategory.BLING,
    description = "Curved ear-contour clips loaded with graduated magenta rhinestones",
    iconGlyph = "✨",
    colorAccent = 0xFFFF69B4
  ),
  GOLD_FANG_GRILL(
    id = "gold_fang_grill",
    displayName = "Diamond Canine Fang Grill",
    category = AccessoryCategory.FACE,
    description = "Custom single-tooth diamond cap glistening with pavé crystals",
    iconGlyph = "🦷",
    colorAccent = 0xFFFFD700
  ),
  DIAMOND_NOSE_STUD(
    id = "diamond_nose_stud",
    displayName = "Pink Diamond Nose Stud",
    category = AccessoryCategory.FACE,
    description = "A glittering 2-carat pink diamond solitaire perched on the nose",
    iconGlyph = "💎",
    colorAccent = 0xFFFF1493
  ),

  // 18-20: Body & Couture
  NEON_PINK_PATCHES(
    id = "neon_pink_patches",
    displayName = "Neon Pink Couture Patches",
    category = AccessoryCategory.BODY,
    description = "Embroidered neon panther badge & lightning heart patches on jacket",
    iconGlyph = "🐆",
    colorAccent = 0xFFFF1493
  ),
  PEARL_GOLD_HARNESS(
    id = "pearl_gold_harness",
    displayName = "Pearl & Gold Royal Harness",
    category = AccessoryCategory.BODY,
    description = "Baroque freshwater pearls linked by filigree 18k yellow gold chains",
    iconGlyph = "📿",
    colorAccent = 0xFFF5F5DC
  ),
  VIP_BACKSTAGE_PASS(
    id = "vip_pass",
    displayName = "VIP Couture Backstage Pass",
    category = AccessoryCategory.NECK,
    description = "Gold lamé lanyard with glowing holographic backstage fashion pass",
    iconGlyph = "🎫",
    colorAccent = 0xFFFFD700
  )
}

/**
 * Dog Luxury Fashion Outfits & Icon Designer Models
 */
enum class OutfitSilhouette(
  val displayName: String,
  val description: String,
  val iconGlyph: String
) {
  MOTO_BIKER("Biker Moto Jacket", "Asymmetrical gold zippers with pink diamond lapels", "🧥"),
  ROYAL_CAPE("Imperial Gala Cape", "Velvet coronation cape with bullion fringe & diamond brooch", "👑"),
  HAUTE_PUFFER("Haute Couture Puffer", "Glossy patent quilted puffer with oversized collar", "🦺"),
  SILK_TRENCH("Parisian Silk Trench", "Hand-tailored silk twill coat with gold monogram belt", "👔"),
  SMOKING_TUX("Bespoke Smoking Tuxedo", "Satin shawl lapels and diamond stud buttons", "🎩"),
  DIAMOND_HARNESS("Diamond Harness Vest", "Jeweled strapping over structured leather bodice", "💎")
}

enum class OutfitFabric(
  val displayName: String,
  val textureNote: String
) {
  PATENT_LEATHER("High-Gloss Patent", "Reflective mirror shine finish"),
  CRUSHED_VELVET("Crushed Royal Velvet", "Deep light-catching plush texture"),
  METALLIC_LAME("24K Metallic Lamé", "Liquid gold metallic shimmer"),
  CROC_EMBOSSED("Exotic Croc Emboss", "Sculpted reptile high-fashion texture"),
  DIAMOND_QUILT("Diamond Padded Quilt", "Puff quilted diamond lattice")
}

enum class OutfitTrim(
  val displayName: String,
  val description: String,
  val defaultAccentHex: Long
) {
  MINK_FUR("Mink Faux Fur Lapels", "Voluminous plush collar trim", 0xFFFFF0F5),
  DIAMOND_SPIKES("Pink Diamond Studs", "Rose-cut diamond pyramid studs", 0xFFFF1493),
  GOLD_BULLION("24K Bullion Braid", "Braided military bullion gold cord", 0xFFFFD700),
  CHUNKY_ZIPPER("Heavy Miami Gold Zipper", "Mirror-finish chunky gold teeth", 0xFFFFD700),
  RHINESTONE_FRINGE("Rhinestone Cascade Fringe", "Dangling crystal strands", 0xFFE0E6ED)
}

enum class DesignerMonogram(
  val label: String,
  val subtitle: String,
  val badgeGlyph: String
) {
  GRAND_OPENING("GRAND OPENING 2026", "Official Flagship Boutique Opening Commemorative Crest", "🎉"),
  CGE_FLAGSHIP("CGE PARIS HAUTE COUTURE", "Artisan atelier monogram embroidery", "✨"),
  NO_SLEEP_RICH("NO SLEEP STILL RICH", "Exclusive billionaire canine club crest", "💰"),
  VOGUE_PAWS("VOGUE PAWS RUNWAY", "Magazine cover honorary seal", "📸"),
  ROYAL_PANTHER("CGE PANTHER COUTURE", "Crowned panther insignia", "🐆")
}

data class DogCoutureOutfit(
  val id: String,
  val name: String,
  val silhouette: OutfitSilhouette = OutfitSilhouette.MOTO_BIKER,
  val fabric: OutfitFabric = OutfitFabric.PATENT_LEATHER,
  val trim: OutfitTrim = OutfitTrim.GOLD_BULLION,
  val primaryColorHex: Long = 0xFFFF1493, // Neon Pink
  val secondaryColorHex: Long = 0xFFFFD700, // Gold
  val monogram: DesignerMonogram = DesignerMonogram.GRAND_OPENING,
  val isGrandOpeningExclusive: Boolean = true
)

/**
 * 5 Backgrounds requested by user
 */
enum class AvatarBackground(
  val id: String,
  val displayName: String,
  val colorTop: Long,
  val colorBottom: Long,
  val sparkleTint: Long,
  val hasShopScene: Boolean = false,
  val hasLeopardPattern: Boolean = false,
  @DrawableRes val sceneDrawableRes: Int? = null
) {
  VIOLET_GLITTER(
    id = "violet_glitter",
    displayName = "Violet Glitter #8A2BE2",
    colorTop = 0xFF8A2BE2,
    colorBottom = 0xFF28074A,
    sparkleTint = 0xFF9370DB
  ),
  PINK_GLITTER(
    id = "pink_glitter",
    displayName = "Neon Pink Glitter #FF1493",
    colorTop = 0xFFFF1493,
    colorBottom = 0xFF4A0027,
    sparkleTint = 0xFFFF69B4
  ),
  LILA_BOKEH(
    id = "lila_bokeh",
    displayName = "Lila Bokeh #C8A2C8",
    colorTop = 0xFFC8A2C8,
    colorBottom = 0xFF3D2543,
    sparkleTint = 0xFFE0C4E0
  ),
  LEOPARD_PRINT(
    id = "leopard_print",
    displayName = "Couture Leopard Print",
    colorTop = 0xFFFFD700,
    colorBottom = 0xFF8A2BE2,
    sparkleTint = 0xFFFF1493,
    hasLeopardPattern = true
  ),
  SHOP_INTERIOR(
    id = "shop_interior",
    displayName = "CGE Boutique Interior",
    colorTop = 0xFF5B1780,
    colorBottom = 0xFF2A0A45,
    sparkleTint = 0xFFFFD700,
    hasShopScene = true,
    sceneDrawableRes = R.drawable.img_shop_interior
  ),
  DUBAI_YACHT(
    id = "dubai_yacht",
    displayName = "Dubai Marina Superyacht",
    colorTop = 0xFFFF8DA1,
    colorBottom = 0xFF5A1E86,
    sparkleTint = 0xFFFFD700,
    sceneDrawableRes = R.drawable.img_dubai_yacht
  ),
  DUBAI_JET(
    id = "dubai_jet",
    displayName = "Gulfstream Couture Jet",
    colorTop = 0xFFFFC0B2,
    colorBottom = 0xFF521577,
    sparkleTint = 0xFFFF1493,
    sceneDrawableRes = R.drawable.img_dubai_jet
  ),
  BURJ_KHALIFA_NIGHT(
    id = "burj_khalifa_night",
    displayName = "Burj Khalifa at Night",
    colorTop = 0xFFFF1493,
    colorBottom = 0xFF350A58,
    sparkleTint = 0xFFFFD700,
    sceneDrawableRes = R.drawable.img_dubai_burj
  )
}

/**
 * Canine Voice Styles for HeyDog AI Talking Avatar
 */
enum class CanineVoiceStyle(
  val displayName: String,
  val description: String,
  val pitch: Float,
  val speechRate: Float,
  val emoji: String
) {
  SASSY_DIVA("Sassy Diva", "Fierce, playful and energetic high-fashion voice", 1.35f, 1.05f, "💅"),
  ROYAL_DUCHESS("Royal Duchess", "Sophisticated, regal and elegant tone", 1.15f, 0.95f, "👑"),
  BILLIONAIRE_BOSS("Billionaire Boss", "Deep, confident VIP canine voice", 0.85f, 1.0f, "💰"),
  PLAYFUL_VIP("Playful VIP", "High-spirited, bubbly canine influencer tone", 1.50f, 1.15f, "✨")
}

/**
 * Storyboard Scenes for HeyDog Video Studio (HeyGen Double for Dogs)
 */
data class StoryboardScene(
  val id: String,
  val title: String,
  val location: String,
  val scriptPrompt: String,
  @DrawableRes val backgroundRes: Int,
  val defaultVoice: CanineVoiceStyle = CanineVoiceStyle.SASSY_DIVA,
  val captions: List<String> = emptyList(),
  val luxuryBadge: String = "VIP DUBAI"
)

/**
 * Full Avatar Configuration state
 */
data class AvatarConfig(
  val name: String = "No Sleep Cleo",
  val vipTitle: String = "Grand Duchess of Still Rich Dogs",
  val baseEdition: BaseEdition = BaseEdition.LEO_GLAM,
  val leoPattern: LeoPattern = LeoPattern.GOLD_LEO,
  val jacketColor: JacketColor = JacketColor.NEON_PINK,
  val hangingEars: Boolean = true, // Hanging floppy ears ON / OFF
  val selectedAccessories: List<String> = listOf(
    AccessoryItem.JEWELED_TIARA.id,
    AccessoryItem.GOLD_CHAINS_PINK_DIAMONDS.id,
    AccessoryItem.PIERCINGS_PINK_DIAMONDS.id,
    AccessoryItem.MINIMAL_GOLD_NECKLACE.id
  ),
  val background: AvatarBackground = AvatarBackground.VIOLET_GLITTER,
  val sparkleAnimationActive: Boolean = true,
  val sparkleDensity: Float = 1.0f,
  val customOutfit: DogCoutureOutfit? = DogCoutureOutfit(
    id = "grand_opening_flagship",
    name = "Grand Opening Gold Ribbon Moto",
    silhouette = OutfitSilhouette.MOTO_BIKER,
    fabric = OutfitFabric.PATENT_LEATHER,
    trim = OutfitTrim.GOLD_BULLION,
    primaryColorHex = 0xFFFF1493,
    secondaryColorHex = 0xFFFFD700,
    monogram = DesignerMonogram.GRAND_OPENING,
    isGrandOpeningExclusive = true
  )
)
