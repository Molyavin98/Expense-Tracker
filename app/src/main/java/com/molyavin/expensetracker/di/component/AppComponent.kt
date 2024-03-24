package com.molyavin.expensetracker.di.component

import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import com.molyavin.expensetracker.di.module.AppModule
import com.molyavin.expensetracker.di.module.ToasterModule
import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.presentation.screen.transaction.AddTransactionViewModel
import com.molyavin.expensetracker.presentation.screen.transaction.EditTransactionViewModel
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationViewModel
import com.molyavin.expensetracker.presentation.screen.auth.RegistrationViewModel
import com.molyavin.expensetracker.presentation.screen.home.HomeViewModel
import com.molyavin.expensetracker.presentation.screen.setting.SettingViewModel
import com.molyavin.expensetracker.presentation.screen.splash.SplashViewModel
import com.molyavin.expensetracker.presentation.screen.statistics.StatisticsViewModel
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ToasterModule::class])
interface AppComponent {

    fun inject(activity: HomeScreen)

    fun provideSplashViewModel(): SplashViewModel
    fun provideAuthorizationViewModel(): AuthorizationViewModel
    fun provideRegistrationViewModel(): RegistrationViewModel
    fun provideHomeViewModel(): HomeViewModel
    fun provideAddTransactionViewModel(): AddTransactionViewModel
    fun provideSettingViewModel(): SettingViewModel
    fun provideStatisticsViewModel(): StatisticsViewModel
    fun provideEditTransactionViewModel(): EditTransactionViewModel
}