package com.molyavin.expensetracker.domain.usecase.splash

import com.molyavin.expensetracker.domain.usecase.auth.GetStatusRememberMeUseCase
import com.molyavin.expensetracker.domain.usecase.IUseCase
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import javax.inject.Inject

class StartScreenUseCase @Inject constructor(
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
) : IUseCase<Any?, String> {

    override fun execute(income: Any?): String {
        val statusRememberMe = getStatusRememberMeUseCase.execute(null)
        return when {
            statusRememberMe -> Screen.HomeScreen.route
            else -> Screen.AuthScreen.route
        }
    }
}


