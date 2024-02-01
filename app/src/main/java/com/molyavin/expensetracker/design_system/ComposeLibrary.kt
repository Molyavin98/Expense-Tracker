package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier
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
        .height(100.dp)
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
    sum: Double,
    profit: Boolean = false,
    colorTitle: Color = AppTheme.colors.onBackground.primary,
    colorSum: Color = if (profit) AppTheme.colors.primaryHover else AppTheme.colors.error,
    textStyle: TextStyle = AppTheme.typography.s3
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.small)
            .height(50.dp)
            .background(AppTheme.colors.onBackground.lightGrey)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            text = text,
            color = colorTitle,
            style = textStyle
        )
        Text(
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically),
            text = if (profit) "+$sum" else "-$sum",
            color = colorSum,
            style = textStyle
        )
    }
}


/*@Composable
fun ListElementItemButton(
    iconImage: ImageVector,
    colorTint: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onClick: () -> Unit,
    buttonImage: ImageVector,
    buttonColorTint: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.LightGray),
                onClick = onClick
            )
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 15.dp)
                .size(40.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                //.background(color = colorResource(id = R.color.background_button_profile))
                .padding(10.dp),
            imageVector = iconImage,
            tint = colorTint,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            text = text,
            color = textColor,
            style = textStyle
        )

        Icon(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically),
            imageVector = buttonImage,
            tint = buttonColorTint,
            contentDescription = null
        )
    }
}*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCenterAlignedTopAppBar(
    modifier: Modifier,
    textTitle: String,
    titleContentColor: Color,
    textStyleTitle: TextStyle,
    navigationOnClick: () -> Unit,
    navigationIcon: ImageVector,
    navigationIconTint: Color,
    actionOnClick: () -> Unit,
    actionIcon: ImageVector? = null,
    actionIconTint: Color? = null,
    containerColor: Color
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = textTitle,
                color = titleContentColor,
                style = textStyleTitle,
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationOnClick) {
                Icon(
                    imageVector = navigationIcon,
                    tint = navigationIconTint,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = actionOnClick) {
                if (actionIcon != null && actionIconTint != null) {
                    Icon(
                        imageVector = actionIcon,
                        tint = actionIconTint,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
        )
    )
}*/


/*@Composable
fun DefaultGlideImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    urlImage: String,
) {
    GlideImage(
        modifier = modifier,
        model = urlImage,
        contentScale = contentScale,
        contentDescription = null,
        loading = placeholder {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(id = R.color.default_button_color)
                )
            }
        }
    ) {

        it.error("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png")

    }

}*/


/*
@Composable
fun LazyList(
    modifier: Modifier,
    words: List<WordVM>,
    modifierItem: Modifier,
    containerColor: Int,
    textStyleEngWord: TextStyle = MaterialTheme.typography.h3,
    textStyleUAWord: TextStyle = MaterialTheme.typography.h4,
    buttonModifier: Modifier,
    modifierText: Modifier,
    btnDeleteText: String,
    btnDeleteClick: (Int) -> Unit,
    btnEditText: String,
    btnEditClick: (Int) -> Unit

) {
    val selectedItem = remember { mutableStateOf<WordVM?>(null) }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(words) { index, word ->

            val isItemSelected = word == selectedItem.value

            Card(
                modifier = modifierItem
                    .clickable {
                        if (isItemSelected) {
                            selectedItem.value = null
                        } else {
                            selectedItem.value = word
                        }
                    },
                colors = CardDefaults.cardColors(containerColor = colorResource(id = containerColor)),
            ) {
                Row() {

                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            modifier = modifierText,
                            text = "\uD83C\uDDEC\uD83C\uDDE7 ${word.eng}",
                            style = textStyleEngWord
                        )
                        Text(
                            modifier = modifier,
                            text = "\uD83C\uDDFA\uD83C\uDDE6 ${word.ua}",
                            style = textStyleUAWord
                        )

                    }

                    val isFavorite = remember { mutableStateOf(false) }

                    IconButton(modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            isFavorite.value = !isFavorite.value
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }

                if (isItemSelected) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        DefaultButton(
                            modifier = buttonModifier,
                            border = BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = colorResource(id = R.color.default_button_text_color)
                            ),
                            contentPadding = PaddingValues(0.dp),
                            text = btnDeleteText,
                            onClick = { btnDeleteClick(index) })

                        DefaultButton(
                            modifier = buttonModifier,
                            border = BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = colorResource(id = R.color.default_button_text_color)
                            ),
                            contentPadding = PaddingValues(0.dp),
                            text = btnEditText,
                            onClick = { btnEditClick(index) })
                    }
                }
            }
        }
    }
}

*/









