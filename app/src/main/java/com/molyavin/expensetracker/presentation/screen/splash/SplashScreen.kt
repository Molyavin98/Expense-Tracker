package com.molyavin.expensetracker.presentation.screen.splash

import ExpenseTrackerTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.DefaultImageLogo
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.Surface
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseScreen() {

    override val viewModel: SplashViewModel by lazy {
        Injector.INSTANCE.provideSplashViewModel()
    }

    @Composable
    override fun Content() {
        ExpenseTrackerTheme {
            Surface {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    DefaultImageLogo(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(24.dp),
                        idImage = R.drawable.app_icon
                    )
                    DefaultText(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.app_name)
                    )
                }
            }
        }
        viewModel.startApp()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}