package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO
import com.molyavin.expensetracker.domain.model.Budget
import com.molyavin.expensetracker.domain.model.Category
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.model.asDomain
import com.molyavin.expensetracker.domain.model.mapCategories
import com.molyavin.expensetracker.domain.usecase.home.GetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.home.SetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.AddTransactionUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.dataTimeFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTransactionBottomSheetViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val setBudgetUseCase: SetBudgetUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    private var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    private val _currency = MutableStateFlow("")
    val currency: StateFlow<String> = _currency

    private val _selectedCategory = MutableStateFlow(0)
    val selectedCategory: StateFlow<Int> = _selectedCategory

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            _currency.value = getCurrencyUseCase.execute(null)
        }
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


    fun addTransactionItem() {
        viewModelScope.launch {
            val item = Transaction(
                id = "",
                title = label.value.text,
                amount = amount.value.text.toDouble(),
                date = dataTimeFormatter(),
                categoryId = _selectedCategory.value
            )
            addTransactionUseCase.execute(item.asDomain())

            val budget = getBudgetUseCase.execute(Unit).budget - amount.value.text.toDouble()
            setBudgetUseCase.execute(Budget(budget))

            setAmount(TextFieldValue(""))
            setLabel(TextFieldValue(""))
        }
    }

}