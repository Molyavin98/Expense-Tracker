package com.molyavin.expensetracker.presentation

import ExpenseTrackerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.Scaffold
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.navigation.Navigation
import com.molyavin.expensetracker.presentation.navigation.Screen

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
fun BottomNavigationBar(navController: NavController, onShowBottomSheet: (Boolean) -> Unit) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier.clip(RoundedCornerShape(topStart = Spacing.M, topEnd = Spacing.M)),
        backgroundColor = AppTheme.colors.onBackground.primary,
        contentColor = AppTheme.colors.onBackground.modal,
    ) {
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.ic_wallet), contentDescription = null) },
            selected = currentRoute == Screen.HomeScreen.route,
            onClick = { navController.navigate(Screen.HomeScreen.route) }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Add, contentDescription = null
                )
            },
            selected = false,
            onClick = { onShowBottomSheet(true) }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_statistics),
                    contentDescription = null
                )
            },
            selected = currentRoute == Screen.StatisticsScreen.route,
            onClick = { navController.navigate(Screen.StatisticsScreen.route) }
        )

    }
}

@Composable
fun BaseSettingsScreen(
    isLoading: Boolean,
    navController: NavController = rememberNavController(),
    showBottomNavBar: Boolean = true,
    onShowBottomSheet: (Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    ExpenseTrackerTheme {
        Scaffold(
            bottomBar = {
                if (showBottomNavBar) BottomNavigationBar(navController, onShowBottomSheet)
            }
        ) {
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

