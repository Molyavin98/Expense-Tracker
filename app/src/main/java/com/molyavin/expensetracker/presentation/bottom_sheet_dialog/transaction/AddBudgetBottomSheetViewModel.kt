package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.model.Budget
import com.molyavin.expensetracker.domain.usecase.home.GetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.home.SetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddBudgetBottomSheetViewModel @Inject constructor(
    private val setBudgetUseCase: SetBudgetUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseViewModel(toaster = toaster) {

    private val _currency = MutableStateFlow("")
    val currency: StateFlow<String> = _currency

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            _budget.value = TextFieldValue(getBudgetUseCase.execute(Unit).budget.toString())
            _currency.value = getCurrencyUseCase.execute(Unit)
        }
    }

    private var _budget = MutableStateFlow(TextFieldValue())
    val budget: StateFlow<TextFieldValue> = _budget

    fun addBudget() {
        viewModelScope.launch {
            setBudgetUseCase.execute(Budget(budget.value.text.toDouble()))
        }
    }

    fun setBudget(newAmount: TextFieldValue) {
        _budget.value = newAmount
    }
}