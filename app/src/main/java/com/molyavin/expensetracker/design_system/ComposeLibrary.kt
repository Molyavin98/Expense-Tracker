package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.domain.model.mapCategories


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
fun DefaultTwoTextBox(
    modifier: Modifier = Modifier,
    textTitle: String,
    textNumber: String,
    onClick: () -> Unit = {}
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .padding(Spacing.M)
        .clip(AppTheme.shapes.small)
        .clickable { onClick() },
) {
    Column {
        Text(
            style = AppTheme.typography.h4,
            color = Color.White,
            text = textTitle
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            style = AppTheme.typography.h1,
            color = Color.White,
            text = textNumber
        )
    }
}

@Composable
fun colorBackgroundCategoryImage(): Color {
    val isDarkTheme = isSystemInDarkTheme()
    return if (isDarkTheme)
        AppTheme.colors.highlight.lightMintGreene
    else AppTheme.colors.highlight.extraLightOrange
}


@Composable
fun TransactionsItem(
    modifier: Modifier = Modifier,
    sum: String,
    categoryId: Int = 0,
    isAllTab: Boolean = false,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit = {},
    onShowClick: () -> Unit = {},
    textStyle: TextStyle = AppTheme.typography.s3
) {
    var isExpanded by remember { mutableStateOf(false) }
    val categoryList = mapCategories()
    val isDarkTheme = isSystemInDarkTheme()


    val colorText = if (isDarkTheme)
        AppTheme.colors.highlight.yellow
    else AppTheme.colors.highlight.lightOrange

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(AppTheme.shapes.small)
        .clickable { isExpanded = !isExpanded }
        .background(AppTheme.colors.onBackground.modal))
    {
        Row(
            modifier = Modifier.height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(38.dp)
                    .clip(AppTheme.shapes.large)
                    .background(colorBackgroundCategoryImage())
                    .padding(Spacing.S),
                painter = painterResource(id = categoryList[categoryId].imageResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(Spacing.M))
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = categoryList[categoryId].name),
                style = AppTheme.typography.s2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(end = Spacing.S),
                text = sum,
                color = colorText,
                style = textStyle
            )

            if (isExpanded) {
                Row {
                    if (isAllTab) {
                        Button(
                            modifier = Modifier
                                .width(80.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = AppTheme.colors.highlight.mediumSeaGreen,
                            ),
                            onClick = {
                                onShowClick()
                                isExpanded = false
                            },
                            content = {
                                Text(
                                    text = "Show",
                                    style = AppTheme.typography.s3,
                                    color = Color.White
                                )
                            },
                        )
                    } else {
                        Button(
                            modifier = Modifier
                                .width(80.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = AppTheme.colors.highlight.mediumSeaGreen,
                            ),
                            onClick = {
                                onEditClick()
                                isExpanded = false
                            },
                            content = {
                                Text(
                                    text = stringResource(id = R.string.text_btn_edit),
                                    style = AppTheme.typography.s3,
                                    color = Color.White
                                )
                            },
                        )
                    }

                    Spacer(modifier = Modifier.size(Spacing.XS))

                    Button(
                        modifier = Modifier
                            .width(80.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppTheme.colors.secondary,
                        ),
                        onClick = {
                            onDeleteClick()
                            isExpanded = false
                        },
                        content = {
                            Text(
                                text = stringResource(id = R.string.text_btn_delete),
                                style = AppTheme.typography.s3,
                                color = Color.White
                            )
                        },
                    )
                }
            }
        }
        Divider()
    }
}

@Composable
fun TabSwitcher(
    firstTitle: String,
    secondTitle: String,
    state: Boolean,
    onStateChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Spacing.M, vertical = Spacing.S)
            .fillMaxWidth()
            .height(40.dp)
            .clip(AppTheme.shapes.button)
            .background(color = AppTheme.colors.onBackground.mediumGrey)
            .padding(Spacing.XXS)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(AppTheme.shapes.button)
                .clickable(
                    onClick = { onStateChanged(false) },
                    indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .run {
                    if (!state) {
                        background(
                            color = AppTheme.colors.onSurface.primary,
                            shape = AppTheme.shapes.button
                        )
                    } else {
                        this
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstTitle,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = AppTheme.colors.onSurface.modal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(AppTheme.shapes.button)
                .clickable(
                    onClick = { onStateChanged(true) },
                    indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .run {
                    if (state) {
                        background(
                            color = AppTheme.colors.onSurface.primary,
                            shape = AppTheme.shapes.button
                        )
                    } else {
                        this
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = secondTitle,
                style = AppTheme.typography.s2,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.onSurface.modal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}