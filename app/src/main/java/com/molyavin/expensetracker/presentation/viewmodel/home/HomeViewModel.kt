package com.molyavin.expensetracker.presentation.viewmodel.home

import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.setting.SettingScreen
import com.molyavin.expensetracker.presentation.screen.transaction.AddTransactionScreen
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import javax.inject.Inject

class HomeViewModel @Inject constructor(navigator: Navigator, toaster: Toaster) :
    BaseViewModel(navigator, toaster) {


    fun addTransaction() = nextScreen(AddTransactionScreen::class.java)

    fun startSetting() = nextScreen(SettingScreen::class.java)
}