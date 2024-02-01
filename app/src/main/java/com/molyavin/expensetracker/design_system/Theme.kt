package com.molyavin.expensetracker.design_system

import ExpenseTrackerTheme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalTheme = staticCompositionLocalOf { Theme.EXPENSE_TRACKER }

enum class Theme {
    EXPENSE_TRACKER
}

@Composable
fun AppTheme(
    theme: Theme = Theme.EXPENSE_TRACKER,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTheme provides theme
    ) {
        when (AppTheme.theme) {
            Theme.EXPENSE_TRACKER -> ExpenseTrackerTheme(darkTheme, content)
        }
    }
}

object AppTheme {
    val theme: Theme
        @Composable
        get() = LocalTheme.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
}