package com.molyavin.expensetracker.presentation.screen.splash

import ExpenseTrackerTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.DefaultImageLogo
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.Surface
import com.molyavin.expensetracker.di.scope.Injector

private val viewModel: SplashViewModel = Injector.INSTANCE.provideSplashViewModel()

@Composable
fun SplashScreen(navController: NavController) {
    ExpenseTrackerTheme {
        LaunchedEffect(Unit) {
            viewModel.startApp(navController)
        }
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                DefaultImageLogo(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(Spacing.L),
                    idImage = R.drawable.app_icon
                )
                DefaultText(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.app_name)
                )
            }
        }
    }
}