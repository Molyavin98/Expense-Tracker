package com.molyavin.expensetracker.presentation

import ExpenseTrackerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.Scaffold
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.navigation.Navigation

class BaseScreen : ComponentActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.inject(this)
        viewModel = Injector.INSTANCE.provideBaseViewModel()
        setContent {
            ExpenseTrackerTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

                        ComposableLifecycle { lifecycleOwner, event ->
                            when (event) {
                                Lifecycle.Event.ON_CREATE -> {
                                    viewModel.onCreate(lifecycleOwner)
                                }

                                Lifecycle.Event.ON_START -> {
                                    viewModel.onStart(lifecycleOwner)
                                }

                                Lifecycle.Event.ON_RESUME -> {
                                    viewModel.onResume(lifecycleOwner)
                                }

                                Lifecycle.Event.ON_PAUSE -> {
                                    viewModel.onPause(lifecycleOwner)
                                }

                                Lifecycle.Event.ON_STOP -> {
                                    viewModel.onStop(lifecycleOwner)
                                }

                                Lifecycle.Event.ON_DESTROY -> {
                                    viewModel.onDestroy(lifecycleOwner)
                                }

                                else -> {}
                            }
                        }

                        val isLoading by viewModel.isLoading.collectAsState()

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = AppTheme.colors.onBackground.grey
                            )
                        } else {
                            Navigation()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun <viewModel : LifecycleObserver> viewModel.ObserveLifecycleEvents(lifecycle: Lifecycle) {
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(this@ObserveLifecycleEvents)
        onDispose {
            lifecycle.removeObserver(this@ObserveLifecycleEvents)
        }
    }
}

@Composable
fun BaseSettingsScreen(isLoading: Boolean, content: @Composable () -> Unit) {
    ExpenseTrackerTheme {
        Scaffold {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = AppTheme.colors.onBackground.grey
                    )
                } else {
                    content()
                }
            }
        }
    }
}

