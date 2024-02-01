package com.molyavin.expensetracker.presentation.viewmodel.spalsh

import com.molyavin.expensetracker.domain.usecase.splash.StartScreenUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val apiDispatcher: AppDispatchers,
    private val startScreenUseCase: StartScreenUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    fun startApp() {
        CoroutineScope(apiDispatcher.main).launch {
            delay(3000)
            nextScreen(startScreenUseCase.execute(null))
        }
    }
}