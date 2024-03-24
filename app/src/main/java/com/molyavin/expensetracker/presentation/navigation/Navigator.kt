package com.molyavin.expensetracker.presentation.navigation

interface Navigator {
    fun navigateTo(destination: Class<*>)

    fun navigateTo(destination: Class<*>, params: Map<String, Any>)

    fun navigateBack()

    fun exitFromAccount(authScreenClass: Class<*>)
}