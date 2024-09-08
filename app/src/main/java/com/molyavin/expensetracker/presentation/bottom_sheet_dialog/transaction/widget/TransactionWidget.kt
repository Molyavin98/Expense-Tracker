package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.IconSize
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.colorBackgroundCategoryImage
import com.molyavin.expensetracker.domain.model.mapCategories

@Composable
fun CategoryBlock(
    isActive: Boolean = true,
    selectedCategory: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val categoryList = mapCategories()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.M)
            .clickable {
                if (isActive) expanded = true else expanded = false
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(AppTheme.shapes.large)
                .size(IconSize.L)
                .background(colorBackgroundCategoryImage())
                .padding(Spacing.S),
            painter = painterResource(id = categoryList[selectedCategory].imageResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(Spacing.M))
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = categoryList[selectedCategory].name),
            style = AppTheme.typography.h5,
            color = AppTheme.colors.onSurface.primary
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }

    DropdownMenu(
        offset = DpOffset(100.dp, 0.dp),
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        categoryList.forEachIndexed { index, category ->
            DropdownMenuItem(
                onClick = {
                    onCategorySelected(index)
                    expanded = false
                }) {
                Image(
                    modifier = Modifier
                        .size(IconSize.M)
                        .clip(AppTheme.shapes.large)
                        .background(colorBackgroundCategoryImage())
                        .padding(Spacing.S),
                    painter = painterResource(id = category.imageResId),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(Spacing.L))
                Text(
                    text = stringResource(id = category.name),
                    style = AppTheme.typography.s2
                )
            }
        }
    }
}
