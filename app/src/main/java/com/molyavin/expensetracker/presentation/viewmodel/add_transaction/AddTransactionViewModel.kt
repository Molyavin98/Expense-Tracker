package com.molyavin.expensetracker.presentation.viewmodel.add_transaction

import androidx.compose.ui.text.input.TextFieldValue
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AddTransactionViewModel @Inject constructor(navigator: Navigator, toaster: Toaster) :
    BaseViewModel(navigator, toaster) {

    private var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    private var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    fun setLabel(newLabel: TextFieldValue) {
        _label.value = newLabel
    }

    fun setAmount(newAmount: TextFieldValue) {
        _amount.value = newAmount
    }

}