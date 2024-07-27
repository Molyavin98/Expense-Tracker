package com.molyavin.expensetracker.presentation.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object AuthScreen : Screen("auth_screen")
    data object RegistrationScreen : Screen("registration_screen")
    data object HomeScreen : Screen("home_screen")
    data object SettingScreen : Screen("settings_screen")
    data object EditTransactionScreen : Screen("edit_transaction_screen/{id}")
    data object AddTransactionScreen : Screen("add_transaction_screen")
    data object EditProfileScreen : Screen("edit_profile_screen")
    data object StatisticsScreen : Screen("statistics_screen")
}
