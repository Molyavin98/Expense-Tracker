package com.molyavin.expensetracker.design_system.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.molyavin.expensetracker.design_system.AppTypography
import com.molyavin.expensetracker.R

internal val expenseTrackerTypography: AppTypography
    @Composable
    get() = AppTypography(
        extraLarge = LalafoTextStyles.ExtraLarge,
        h1 = LalafoTextStyles.H1,
        h2 = LalafoTextStyles.H2,
        h3 = LalafoTextStyles.H3,
        h4 = LalafoTextStyles.H4,
        h5 = LalafoTextStyles.H5,
        s1 = LalafoTextStyles.S1,
        s2 = LalafoTextStyles.S2,
        s3 = LalafoTextStyles.S3,
        s4 = LalafoTextStyles.S4,
        s5 = LalafoTextStyles.S5,
        s6 = LalafoTextStyles.S6,
        p1 = LalafoTextStyles.P1,
        p2 = LalafoTextStyles.P2,
        caption1 = LalafoTextStyles.Caption1,
        caption2 = LalafoTextStyles.Caption2,
        buttonS = LalafoTextStyles.ButtonS,
        buttonM = LalafoTextStyles.ButtonM,
        buttonL = LalafoTextStyles.ButtonL,
        hint = LalafoTextStyles.Hint
    )

private object ExpenseTrackerFontFamilies {

    val UbuntuBold = FontFamily(
        Font(R.font.ubuntu_bold, weight = FontWeight.Bold)
    )

    val UbuntuMedium = FontFamily(
        Font(R.font.ubuntu_medium, weight = FontWeight.Medium)
    )

    val UbuntuRegular = FontFamily(
        Font(R.font.ubuntu_regular, weight = FontWeight.Normal)
    )

    val CaveatBold = FontFamily(
        Font(R.font.caveat_bold, weight = FontWeight.Bold)
    )
}

private object LalafoTextStyles {

    val ExtraLarge = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 40.sp,
        lineHeight = 60.sp,
        letterSpacing = 0.26.sp
    )

    val H1 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.26.sp
    )

    val H2 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 26.sp,
        lineHeight = 39.sp
    )

    val H3 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 30.sp
    )

    val H4 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 30.sp
    )

    val H5 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuRegular,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 25.sp
    )

    val S1 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val S2 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val S3 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuBold,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    val S4 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    val S5 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

    val S6 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
        lineHeight = 15.sp
    )

    val P1 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuRegular,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val P2 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuRegular,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    val Caption1 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuRegular,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

    val Caption2 = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuRegular,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
        lineHeight = 15.sp
    )

    val ButtonL = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val ButtonM = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    val ButtonS = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.UbuntuMedium,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

    val Hint = TextStyle(
        fontFamily = ExpenseTrackerFontFamilies.CaveatBold,
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )
}