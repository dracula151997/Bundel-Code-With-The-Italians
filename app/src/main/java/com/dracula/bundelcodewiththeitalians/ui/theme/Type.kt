package com.dracula.bundelcodewiththeitalians.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dracula.bundelcodewiththeitalians.R

private val interBold = Font(R.font.inter_bold, weight = FontWeight.Bold)
private val interMedium = Font(R.font.inter_medium, weight = FontWeight.Medium)
private val interRegular = Font(R.font.inter_regular, weight = FontWeight.Normal)

private val inter = FontFamily(
    listOf(
        interBold,
        interMedium,
        interRegular,
    ),
)

private val podkovaBold = Font(R.font.podkova_bold, weight = FontWeight.Bold)
private val podkovaExtraBold = Font(R.font.podkova_extrabold, weight = FontWeight.ExtraBold)
private val podkovaMedium = Font(R.font.podkova_medium, weight = FontWeight.Medium)
private val podkovaRegular = Font(R.font.podkova_regular, weight = FontWeight.Normal)
private val podkovaSemiBold = Font(R.font.podkova_semibold, weight = FontWeight.SemiBold)

private val podkova = FontFamily(
    listOf(
        podkovaBold,
        podkovaExtraBold,
        podkovaMedium,
        podkovaRegular,
        podkovaSemiBold,
    ),
)

val BundelTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = podkova,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)
