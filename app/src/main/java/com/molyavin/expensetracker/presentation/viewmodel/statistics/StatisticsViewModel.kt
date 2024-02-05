package com.molyavin.expensetracker.presentation.viewmodel.statistics

import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(navigator: Navigator, toaster: Toaster) :
    BaseViewModel(navigator, toaster) {


}