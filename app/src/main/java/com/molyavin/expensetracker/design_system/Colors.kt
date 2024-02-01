package com.molyavin.expensetracker.design_system

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        primary = Color.Unspecified,
        primaryHover = Color.Unspecified,
        secondary = Color.Unspecified,
        secondaryHover = Color.Unspecified,
        tertiary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = OnBackgroundColors(),
        surface = Color.Unspecified,
        onSurface = OnSurfaceColors(),
        error = Color.Unspecified,
        highlight = HighlightColors(),
        isLight = true
    )
}

@Immutable
data class AppColors(
    val primary: Color = Color.Unspecified,
    val primaryHover: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val secondaryHover: Color = Color.Unspecified,
    val tertiary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: OnBackgroundColors,
    val surface: Color = Color.Unspecified,
    val onSurface: OnSurfaceColors,
    val error: Color = Color.Unspecified,
    val highlight: HighlightColors,
    val isLight: Boolean
)

@Immutable
data class OnBackgroundColors(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val mediumGrey: Color = Color.Unspecified,
    val grey: Color = Color.Unspecified,
    val lightGrey: Color = Color.Unspecified,
    val extraLightGrey: Color = Color.Unspecified,
    val modal: Color = Color.Unspecified
)

@Immutable
data class OnSurfaceColors(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val mediumGrey: Color = Color.Unspecified,
    val grey: Color = Color.Unspecified,
    val lightGrey: Color = Color.Unspecified,
    val modal: Color = Color.Unspecified
)

@Immutable
data class HighlightColors(
    val purple: Color = Color.Unspecified,
    val lightPurple: Color = Color.Unspecified,
    val extraLightPurple: Color = Color.Unspecified,

    val darkBlue: Color = Color.Unspecified,
    val lightDarkBlue: Color = Color.Unspecified,
    val extraLightDarkBlue: Color = Color.Unspecified,

    val blue: Color = Color.Unspecified,
    val lightBlue: Color = Color.Unspecified,
    val extraLightBlue: Color = Color.Unspecified,

    val mintGreen: Color = Color.Unspecified,
    val lightMintGreene: Color = Color.Unspecified,
    val extraLightMintGreene: Color = Color.Unspecified,

    val grassGreen: Color = Color.Unspecified,
    val lightGrassGreen: Color = Color.Unspecified,
    val extraLightGrassGreen: Color = Color.Unspecified,

    val yellow: Color = Color.Unspecified,
    val lightYellow: Color = Color.Unspecified,
    val extraLightYellow: Color = Color.Unspecified,

    val orange: Color = Color.Unspecified,
    val lightOrange: Color = Color.Unspecified,
    val extraLightOrange: Color = Color.Unspecified,

    val red: Color = Color.Unspecified,
    val lightRed: Color = Color.Unspecified,
    val extraLightRed: Color = Color.Unspecified,

    val barbiePink: Color = Color.Unspecified,
    val lightBarbiePink: Color = Color.Unspecified,
    val extraLightBarbiePink: Color = Color.Unspecified,

    val darkBlueHover: Color = Color.Unspecified
)