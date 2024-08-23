package com.molyavin.expensetracker.domain.model

import androidx.compose.runtime.Composable
import com.molyavin.expensetracker.R

data class Category(val name: Int, val imageResId: Int)

fun mapCategories(): List<Category> {
    return listOf(
        Category(R.string.category_1, R.drawable.ic_category_1),
        Category(R.string.category_2, R.drawable.ic_category_2),
        Category(R.string.category_3, R.drawable.ic_category_3),
        Category(R.string.category_4, R.drawable.ic_category_4),
        Category(R.string.category_5, R.drawable.ic_category_5),
        Category(R.string.category_6, R.drawable.ic_category_6),
        Category(R.string.category_7, R.drawable.ic_category_7),
        Category(R.string.category_8, R.drawable.ic_category_8),
        Category(R.string.category_9, R.drawable.ic_category_9),
        Category(R.string.category_10, R.drawable.ic_category_10)
    )
}