package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.model.Budget
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.model.asDomain
import com.molyavin.expensetracker.domain.usecase.home.GetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.home.SetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.AddTransactionUseCase
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.dataTimeFormatter
import com.molyavin.expensetracker.utils.dateFormatter
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTransactionBottomSheetViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val setBudgetUseCase: SetBudgetUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseTransactionBottomSheetViewModel(toaster) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            _currency.value = getCurrencyUseCase.execute(null)
        }
    }

    fun addTransactionItem() {
        viewModelScope.launch {
            val item = Transaction(
                id = "",
                title = label.value.text,
                amount = amount.value.text.toDouble(),
                date = dateFormatter(),
                dataTime = dataTimeFormatter(),
                categoryId = _selectedCategory.value
            )
            addTransactionUseCase.execute(item.asDomain())

            val budget = getBudgetUseCase.execute(Unit).budget - amount.value.text.toDouble()
            setBudgetUseCase.execute(Budget(budget))

            resetTextFields()
        }
    }

    private fun resetTextFields() {
        setAmount(TextFieldValue(""))
        setLabel(TextFieldValue(""))
    }

}