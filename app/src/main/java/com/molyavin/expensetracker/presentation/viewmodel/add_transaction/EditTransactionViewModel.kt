package com.molyavin.expensetracker.presentation.viewmodel.add_transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.data.room.TransactionEntity
import com.molyavin.expensetracker.domain.model.TransactionVM
import com.molyavin.expensetracker.domain.usecase.transaction.EditTransactionUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTransactionViewModel @Inject constructor(
    private val editTransactionUseCase: EditTransactionUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    private var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    private var _isIncome = MutableStateFlow(true)
    val isIncome: StateFlow<Boolean> = _isIncome

    fun setLabel(newLabel: TextFieldValue) {
        _label.value = newLabel
    }

    fun setAmount(newAmount: TextFieldValue) {
        _amount.value = newAmount
    }

    fun setIsIncome(value: Boolean) {
        _isIncome.value = value
    }

    fun editTransaction(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(200)
            editTransactionUseCase.execute(transactionEntity)
            _isLoading.value = false
        }
    }

    fun initializeWithTransactionData(transactionData: TransactionVM) {
        setLabel(TextFieldValue(transactionData.label))
        setAmount(TextFieldValue(transactionData.amount.toString()))
        setIsIncome(transactionData.isIncome)
    }

}