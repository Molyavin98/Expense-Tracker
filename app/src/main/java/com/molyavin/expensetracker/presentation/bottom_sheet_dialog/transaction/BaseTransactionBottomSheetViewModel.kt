package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.ui.text.input.TextFieldValue
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseTransactionBottomSheetViewModel(toaster: Toaster) : BaseViewModel(toaster) {

    protected var _id = MutableStateFlow(String())
    val id: StateFlow<String> = _id

    protected var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    protected var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    protected val _currency = MutableStateFlow("")
    val currency: StateFlow<String> = _currency

    protected val _selectedCategory = MutableStateFlow(0)
    val selectedCategory: StateFlow<Int> = _selectedCategory

    protected val _dataTime = MutableStateFlow("")
    val dataTime: StateFlow<String> = _dataTime

    fun setId(newId: String) {
        _id.value = newId
    }

    fun setLabel(newLabel: TextFieldValue) {
        _label.value = newLabel
    }

    fun setAmount(newAmount: TextFieldValue) {
        _amount.value = newAmount
    }

    fun setCategory(newCategory: Int) {
        _selectedCategory.value = newCategory
    }

    fun setDataTime(newDataTime: String) {
        _dataTime.value = newDataTime
    }

}