package com.molyavin.expensetracker.design_system.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.design_system.AppShapes

internal val expenseTrackerShapes: AppShapes
    @Composable
    get() = AppShapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(24.dp),
        button = RoundedCornerShape(50.dp),
        textButton = RoundedCornerShape(8.dp)
    )