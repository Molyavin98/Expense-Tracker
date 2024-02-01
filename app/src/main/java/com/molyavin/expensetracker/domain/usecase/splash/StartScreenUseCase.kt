package com.molyavin.expensetracker.domain.usecase.splash

import com.molyavin.expensetracker.domain.usecase.auth.GetStatusRememberMeUseCase
import com.molyavin.expensetracker.domain.usecase.base.IUseCase
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import javax.inject.Inject

class StartScreenUseCase @Inject constructor(
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
) : IUseCase<Any?, Class<*>> {

    override fun execute(income: Any?): Class<*> {
        val statusRememberMe = getStatusRememberMeUseCase.execute(null)
        return when {
            statusRememberMe -> HomeScreen::class.java
            else -> AuthorizationScreen::class.java
        }
    }
}


