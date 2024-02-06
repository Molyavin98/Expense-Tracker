package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.expensetracker.R


@Composable
fun DefaultText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    text: String,
    styleText: TextStyle = AppTheme.typography.h3,
    color: Color = AppTheme.colors.onBackground.primary,
) = Text(
    modifier = modifier.padding(10.dp),
    textAlign = textAlign,
    text = text,
    style = styleText,
    color = color,
)


@Composable
fun DefaultImageLogo(
    modifier: Modifier = Modifier,
    idImage: Int,
) = Image(
    modifier = modifier,
    painter = painterResource(id = idImage),
    contentDescription = null,
)

@Composable
fun TopAppName(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) = Row(
    modifier = modifier.padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    DefaultImageLogo(
        modifier = Modifier.size(36.dp),
        idImage = R.drawable.app_icon
    )
    DefaultText(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
            .weight(1f),
        text = stringResource(id = R.string.app_name)
    )

    Icon(
        modifier = Modifier.clickable { onClick() },
        tint = AppTheme.colors.onBackground.primary,
        imageVector = Icons.Default.Settings,
        contentDescription = null
    )

}


@Composable
fun DefaultTwoTextBox(
    modifier: Modifier = Modifier,
    textTitle: String,
    textNumber: Double,
    colorSum: Color = AppTheme.colors.onBackground.primary
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .height(80.dp)
        .clip(AppTheme.shapes.small)
        .background(color = AppTheme.colors.onBackground.lightGrey),
    contentAlignment = Alignment.Center
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = AppTheme.typography.s3,
            color = AppTheme.colors.onBackground.mediumGrey,
            text = textTitle
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            style = AppTheme.typography.h3,
            color = colorSum,
            text = textNumber.toString()
        )
    }
}


@Composable
fun TransactionsItem(
    modifier: Modifier = Modifier,
    text: String,
    sum: Float,
    profit: Boolean = false,
    date: String,
    colorTitle: Color = AppTheme.colors.onBackground.primary,
    colorSum: Color = if (profit) AppTheme.colors.primaryHover else AppTheme.colors.error,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    textStyle: TextStyle = AppTheme.typography.s3
) {

    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(AppTheme.shapes.small)
        .clickable { isExpanded = !isExpanded }
        .background(AppTheme.colors.onBackground.lightGrey))
    {
        Row(modifier = Modifier.height(35.dp)) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = Spacing.S)
                    .align(Alignment.CenterVertically),
                text = text,
                color = colorTitle,
                style = textStyle
            )
            Text(
                modifier = Modifier
                    .padding(end = Spacing.S)
                    .align(Alignment.CenterVertically),
                text = if (profit) "+$sum" else "-$sum",
                color = colorSum,
                style = textStyle
            )
        }
        Text(
            modifier = Modifier
                .padding(start = Spacing.S, bottom = Spacing.S),
            text = date,
            color = AppTheme.colors.onBackground.mediumGrey,
            style = textStyle.copy(fontSize = 12.sp)
        )

        if (isExpanded) {
            Row {
                ButtonTransparent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.text_btn_delete),
                    onClick = onDeleteClick
                )
                ButtonTransparent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.text_btn_edit),
                    onClick = onEditClick
                )
            }
        }
    }
}