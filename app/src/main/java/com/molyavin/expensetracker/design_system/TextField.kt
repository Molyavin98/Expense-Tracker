package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.expensetracker.R

@Composable
private fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    hint: String,
    focusColor: Color,
    unFocusColor: Color,
    textForgotPassword: String? = null,
    fieldTextStyle: TextStyle = AppTheme.typography.p1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    styleText: TextStyle = AppTheme.typography.s3,
    textColor: Color = AppTheme.colors.onBackground.primary,
    fieldShape: Shape = RoundedCornerShape(8.dp),
    fieldBackgroundColor: Color = AppTheme.colors.onBackground.lightGrey,
    trailingIcon: Int? = null,
    fieldModifier: Modifier = Modifier,
    hintStyle: TextStyle = AppTheme.typography.s4,
    modifierText: Modifier = Modifier,
    trailingIconModifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
    ) {
        Row {
            Text(
                text = label,
                style = styleText,
                color = AppTheme.colors.onBackground.mediumGrey,
                modifier = modifierText,
            )

            textForgotPassword?.let {
                Text(
                    text = it,
                    textDecoration = TextDecoration.combine(listOf(TextDecoration.Underline)),
                    style = styleText,
                    color = AppTheme.colors.onBackground.mediumGrey,
                    modifier = modifierText,
                    textAlign = TextAlign.End,
                )
            }
        }

        var fieldFocus by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = fieldModifier
                .fillMaxWidth()
                .background(
                    color = fieldBackgroundColor,
                    shape = fieldShape
                )
                .onFocusChanged { fieldFocus = it.isFocused },
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            textStyle = fieldTextStyle,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    style = hintStyle,
                    color = AppTheme.colors.onBackground.mediumGrey
                )
            },
            shape = fieldShape,
            trailingIcon = {
                trailingIcon?.let {
                    Icon(
                        modifier = trailingIconModifier,
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = if (fieldFocus) focusColor else Color.Gray
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = focusColor,
                unfocusedBorderColor = unFocusColor,
                focusedLabelColor = focusColor,
                unfocusedLabelColor = Color.Gray,
                cursorColor = focusColor,
                textColor = textColor
            ),
        )
    }
}

@Composable
fun DefaultEmailField(
    modifier: Modifier = Modifier,
    email: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    hint: String,
    focusColor: Color = AppTheme.colors.onBackground.primary,
    unFocusColor: Color = AppTheme.colors.onBackground.grey
) = DefaultTextField(
    value = email,
    onValueChange = onValueChange,
    label = label,
    hint = hint,
    focusColor = focusColor,
    unFocusColor = unFocusColor,
    visualTransformation = VisualTransformation.None,
    trailingIcon = R.drawable.email_icon,
    modifierText = modifier,
)


@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String = "",
    hint: String,
    focusColor: Color = AppTheme.colors.onBackground.primary,
    unFocusColor: Color = AppTheme.colors.onBackground.grey
) = DefaultTextField(
    value = value,
    onValueChange = onValueChange,
    label = label,
    hint = hint,
    focusColor = focusColor,
    unFocusColor = unFocusColor,
    visualTransformation = VisualTransformation.None,
    modifierText = modifier,
)


@Composable
fun DefaultPasswordField(
    modifier: Modifier = Modifier,
    password: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    textForgotPassword: String? = null,
    label: String,
    hint: String,
    focusColor: Color = AppTheme.colors.onBackground.primary,
    unFocusColor: Color = AppTheme.colors.onBackground.grey
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    DefaultTextField(
        modifierText = modifier,
        value = password,
        onValueChange = onValueChange,
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        textForgotPassword = textForgotPassword,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIconModifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
        trailingIcon = if (passwordVisibility) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
    )
}