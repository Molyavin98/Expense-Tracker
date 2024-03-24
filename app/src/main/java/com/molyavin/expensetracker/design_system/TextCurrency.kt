package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CurrencyText(
    modifier: Modifier = Modifier,
    euro: String,
    dollar: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.M)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.s2,
            color = AppTheme.colors.onBackground.primary,
            text = dollar
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.s2,
            color = AppTheme.colors.onBackground.primary,
            text = euro
        )
    }
}