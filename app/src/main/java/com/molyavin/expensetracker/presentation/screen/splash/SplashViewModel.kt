package com.molyavin.expensetracker.presentation.screen.splash

import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.usecase.splash.StartScreenUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val appDispatcher: AppDispatchers,
    private val startScreenUseCase: StartScreenUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    fun startApp(navController: NavController) {
        CoroutineScope(appDispatcher.main).launch {
            delay(3000)
            navController.navigate(startScreenUseCase.execute(null)){
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }

}