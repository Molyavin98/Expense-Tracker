package com.molyavin.expensetracker.presentation.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.ButtonBack
import com.molyavin.expensetracker.design_system.CurrencyText
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen

class SettingScreen : BaseScreen() {

    override val viewModel: SettingViewModel = Injector.INSTANCE.provideSettingViewModel()

    @Composable
    override fun Content() {

        Column(modifier = Modifier.fillMaxSize()) {
            ButtonBack(
                modifier = Modifier.padding(Spacing.M),
                onClick = { viewModel.navigateBack() }
            )

            CurrencyText(
                dollar = viewModel.currencyDollar.collectAsState().value,
                euro = viewModel.currencyEuro.collectAsState().value
            )

            Box(modifier = Modifier.fillMaxHeight()) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.exit_from_account),
                    onClick = {
                        viewModel.logOut()
                    }
                )
            }
        }
    }


}