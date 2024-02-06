package com.molyavin.expensetracker.presentation.screen

import ExpenseTrackerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.Scaffold
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel

abstract class BaseScreen : ComponentActivity() {

    protected abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.inject(this)
        setContent {
            ExpenseTrackerTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {

                        val isLoading by viewModel.isLoading.collectAsState()

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = AppTheme.colors.onBackground.grey
                            )
                        } else {
                            Content()
                        }
                    }
                }
            }
        }
    }

    @Composable
    protected abstract fun Content()

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}