package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.onBackground.primary,
        contentColor = AppTheme.colors.onBackground.modal,
    ),
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke? = null,
    elevation: ButtonElevation? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 40.dp, minWidth = 100.dp),
        onClick = onClick,
        enabled = enabled,
        border = border,
        elevation = elevation,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = if (trailingIcon.isNotNull()) Modifier.weight(1f) else Modifier,
                text = text ?: "",
                style = textStyle,
            )

            trailingIcon?.invoke()
        }
    }
}

@Composable
fun DefaultSocialAuthButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.onBackground.primary
    ),
    imageId: Int,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke = BorderStroke(1.dp, AppTheme.colors.onBackground.grey)
) {
    Button(
        modifier = modifier
            .padding(5.dp)
            .size(50.dp),
        colors = colors,
        shape = shape,
        border = border,
        onClick = {},
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
        )
    }
}

@Composable
fun AuthFooter(
    modifier: Modifier,
    text: String,
    textButton: String,
    onClick: () -> Unit,
    styleText: TextStyle = AppTheme.typography.s4,
    color: Color = AppTheme.colors.onBackground.primary
) = Row(modifier = modifier) {
    Text(
        text = text,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        color = color,
        style = styleText,
    )
    Text(
        text = textButton,
        style = styleText,
        color = Color.Blue,
        modifier = Modifier
            .padding(start = 2.dp, top = 8.dp, bottom = 8.dp)
            .clickable { onClick.invoke() }
    )
}

@Composable
fun ButtonTransparent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    border: BorderStroke = BorderStroke(1.dp, AppTheme.colors.onBackground.primary),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent,
        contentColor = AppTheme.colors.onBackground.primary,
    ),
    text: String,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    DefaultButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Spacing.S, end = Spacing.XS, bottom = Spacing.S)
            .size(50.dp),
        text = text,
        textStyle = AppTheme.typography.buttonM,
        colors = colors,
        border = border,
        onClick = onClick,
        trailingIcon = trailingIcon
    )
}


@Composable
fun ButtonClose(modifier: Modifier = Modifier, onClick: () -> Unit) = Icon(
    modifier = modifier
        .size(40.dp)
        .clip(AppTheme.shapes.button)
        .clickable { onClick() },
    imageVector = Icons.Default.Close,
    tint = AppTheme.colors.onBackground.primary,
    contentDescription = null
)

@Composable
fun ButtonBack(modifier: Modifier = Modifier, onClick: () -> Unit) = Icon(
    modifier = modifier
        .size(40.dp)
        .clip(AppTheme.shapes.button)
        .clickable { onClick() },
    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
    tint = AppTheme.colors.onBackground.primary,
    contentDescription = null
)



