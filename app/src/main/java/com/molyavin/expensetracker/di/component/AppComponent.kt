package com.molyavin.expensetracker.di.component

import com.molyavin.expensetracker.di.module.AppModule
import com.molyavin.expensetracker.di.module.FirebaseModule
import com.molyavin.expensetracker.di.module.ToasterModule
import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.currency.CurrencyBottomSheetViewModel
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.AddBudgetBottomSheetViewModel
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.AddTransactionBottomSheetViewModel
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.EditTransactionBottomSheetViewModel
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationViewModel
import com.molyavin.expensetracker.presentation.screen.auth.RegistrationViewModel
import com.molyavin.expensetracker.presentation.screen.home.HomeViewModel
import com.molyavin.expensetracker.presentation.screen.news.NewsViewModel
import com.molyavin.expensetracker.presentation.screen.setting.SettingViewModel
import com.molyavin.expensetracker.presentation.screen.splash.SplashViewModel
import com.molyavin.expensetracker.presentation.screen.statistics.StatisticsViewModel
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ToasterModule::class, FirebaseModule::class])
interface AppComponent {
    fun provideBaseViewModel(): BaseViewModel
    fun provideSplashViewModel(): SplashViewModel
    fun provideAuthorizationViewModel(): AuthorizationViewModel
    fun provideRegistrationViewModel(): RegistrationViewModel
    fun provideHomeViewModel(): HomeViewModel
    fun provideSettingViewModel(): SettingViewModel
    fun provideStatisticsViewModel(): StatisticsViewModel
    fun provideBaseTransactionBottomSheetViewModel(): BaseViewModel
    fun provideNewsViewModel(): NewsViewModel

    // Bottom sheet dialogs
    fun provideEditTransactionViewModel(): EditTransactionBottomSheetViewModel
    fun provideAddTransactionViewModel(): AddTransactionBottomSheetViewModel
    fun provideAddBudgetViewModel(): AddBudgetBottomSheetViewModel
    fun provideCurrencyBottomSheetViewModel(): CurrencyBottomSheetViewModel

}