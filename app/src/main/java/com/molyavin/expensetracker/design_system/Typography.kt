package com.molyavin.expensetracker.design_system

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppTypography(
    val extraLarge: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val s1: TextStyle,
    val s2: TextStyle,
    val s3: TextStyle,
    val s4: TextStyle,
    val s5: TextStyle,
    val s6: TextStyle,
    val p1: TextStyle,
    val p2: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
    val buttonS: TextStyle,
    val buttonM: TextStyle,
    val buttonL: TextStyle,
    val hint: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        extraLarge = TextStyle.Default,
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        h4 = TextStyle.Default,
        h5 = TextStyle.Default,
        s1 = TextStyle.Default,
        s2 = TextStyle.Default,
        s3 = TextStyle.Default,
        s4 = TextStyle.Default,
        s5 = TextStyle.Default,
        s6 = TextStyle.Default,
        p1 = TextStyle.Default,
        p2 = TextStyle.Default,
        caption1 = TextStyle.Default,
        caption2 = TextStyle.Default,
        buttonS = TextStyle.Default,
        buttonM = TextStyle.Default,
        buttonL = TextStyle.Default,
        hint = TextStyle.Default
    )
}