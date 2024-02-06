package com.molyavin.expensetracker.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DefaultCheckBox(
    modifier: Modifier = Modifier,
    checkBoxState: Boolean,
    onValueChange: (Boolean) -> Unit,
    styleText: TextStyle = AppTheme.typography.p2,
    checkedColor: Color = AppTheme.colors.onBackground.primary,
    uncheckedColor: Color = AppTheme.colors.onBackground.mediumGrey,
    checkMarkColor: Color = AppTheme.colors.onBackground.modal,
    text: String,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically,
) {
    Checkbox(
        checked = checkBoxState,
        onCheckedChange = onValueChange,
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            checkmarkColor = checkMarkColor,
            uncheckedColor = uncheckedColor
        )
    )
    Text(
        text = text,
        color = AppTheme.colors.onBackground.primary,
        style = styleText,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DefaultRadioButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean,
    styleText: TextStyle = AppTheme.typography.p2,
    checkedColor: Color = AppTheme.colors.onBackground.primary,
    uncheckedColor: Color = AppTheme.colors.onBackground.mediumGrey,
    text: String,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically,
) {
    RadioButton(
        selected = isSelected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = checkedColor,
            unselectedColor = uncheckedColor,
        )
    )
    Text(
        text = text,
        color = AppTheme.colors.onBackground.primary,
        style = styleText,
        textAlign = TextAlign.Center
    )
}



