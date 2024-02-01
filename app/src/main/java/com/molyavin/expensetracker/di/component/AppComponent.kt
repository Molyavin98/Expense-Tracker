package com.molyavin.expensetracker.di.component

import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import com.molyavin.expensetracker.di.module.AppModule
import com.molyavin.expensetracker.di.module.ToasterModule
import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.presentation.viewmodel.add_transaction.AddTransactionViewModel
import com.molyavin.expensetracker.presentation.viewmodel.auth.AuthorizationViewModel
import com.molyavin.expensetracker.presentation.viewmodel.auth.RegistrationViewModel
import com.molyavin.expensetracker.presentation.viewmodel.home.HomeViewModel
import com.molyavin.expensetracker.presentation.viewmodel.setting.SettingViewModel
import com.molyavin.expensetracker.presentation.viewmodel.spalsh.SplashViewModel
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
}