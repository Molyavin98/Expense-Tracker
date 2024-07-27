package com.molyavin.expensetracker.presentation.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.ButtonBack
import com.molyavin.expensetracker.design_system.CurrencyText
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents

private val viewModel: SettingViewModel = Injector.INSTANCE.provideSettingViewModel()

@Composable
fun SettingScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val isLoading by viewModel.isLoading.collectAsState()

    BaseSettingsScreen(isLoading = isLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            ButtonBack(
                modifier = Modifier.padding(Spacing.M),
                onClick = { navController.popBackStack() }
            )

            CurrencyText(
                dollar = viewModel.currencyDollar.collectAsState().value,
                euro = viewModel.currencyEuro.collectAsState().value
            )

            Box(modifier = Modifier.fillMaxHeight()) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.M)
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.exit_from_account),
                    onClick = { viewModel.logOut(navController = navController) }
                )
            }
        }
    }
}