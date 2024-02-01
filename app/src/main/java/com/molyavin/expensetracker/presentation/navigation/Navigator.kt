package com.molyavin.expensetracker.presentation.navigation

interface Navigator {
    fun navigateTo(destination: Class<*>)

    fun navigateBack()
}