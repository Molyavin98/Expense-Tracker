package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .padding(start = startPadding, end = endPadding)
        .height(1.dp)
        .background(AppTheme.colors.onBackground.grey)
)

@Composable
fun DividerOr() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = Spacing.S, bottom = Spacing.SM)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .weight(40f)
                .background(Color.LightGray)
        )
        Text(
            text = stringResource(id = R.string.text_or),
            modifier = Modifier
                .padding(start = Spacing.S, end = Spacing.S),
            style = TextStyle(Color.Gray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .weight(40f)
                .background(Color.LightGray)
        )
    }
}