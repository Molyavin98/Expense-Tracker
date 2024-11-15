package com.molyavin.expensetracker.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.molyavin.expensetracker.design_system.Surface
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.screen.auth.RegistrationScreen
import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import com.molyavin.expensetracker.presentation.screen.news.NewsScreen
import com.molyavin.expensetracker.presentation.screen.setting.SettingScreen
import com.molyavin.expensetracker.presentation.screen.splash.SplashScreen
import com.molyavin.expensetracker.presentation.screen.statistics.StatisticsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(Screen.AuthScreen.route) {
                AuthorizationScreen(navController = navController)
            }
            composable(Screen.RegistrationScreen.route) {
                RegistrationScreen(navController = navController)
            }
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.SettingScreen.route) {
                SettingScreen(navController = navController)
            }
            composable(Screen.StatisticsScreen.route) {
                StatisticsScreen(navController = navController)
            }
            composable(Screen.EditProfileScreen.route) {
                RegistrationScreen(navController = navController)
            }
            composable(Screen.NewsScreen.route) {
                NewsScreen(navController = navController)
            }
        }
    }
}