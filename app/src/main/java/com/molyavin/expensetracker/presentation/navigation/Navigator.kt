package com.molyavin.expensetracker.presentation.navigation

import com.molyavin.expensetracker.domain.model.TransactionVM

interface Navigator {
    fun navigateTo(destination: Class<*>)

    fun navigateTo(destination: Class<*>, params: Map<String, Any>)

    fun navigateBack()
}