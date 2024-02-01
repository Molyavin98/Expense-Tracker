package com.molyavin.expensetracker.presentation.viewmodel.add_transaction

import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import javax.inject.Inject

class AddTransactionViewModel @Inject constructor(navigator: Navigator, toaster: Toaster) :
    BaseViewModel(navigator, toaster) {

}