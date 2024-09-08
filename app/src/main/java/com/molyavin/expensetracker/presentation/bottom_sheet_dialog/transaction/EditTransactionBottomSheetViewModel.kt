package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.EditTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionByIdUseCase
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.dataTimeFormatter
import com.molyavin.expensetracker.utils.dateFormatter
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTransactionBottomSheetViewModel @Inject constructor(
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val editTransactionUseCase: EditTransactionUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseTransactionBottomSheetViewModel(toaster) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        initializeWithTransactionData()

        viewModelScope.launch {
            _currency.value = getCurrencyUseCase.execute(null)
        }
    }

    fun editTransaction() {
        viewModelScope.launch {
            editTransactionUseCase.execute(
                Transaction(
                    id = id.value,
                    title = label.value.text,
                    amount = amount.value.text.toDouble(),
                    date = dateFormatter(),
                    dataTime = dataTimeFormatter(),
                    categoryId = _selectedCategory.value
                )
            )
        }
    }

    private fun initializeWithTransactionData() {
        viewModelScope.launch {
            val transactionData = getTransactionByIdUseCase.execute(id.value)
            setLabel(TextFieldValue(transactionData?.title.orEmpty()))
            setAmount(TextFieldValue(transactionData?.amount.toString()))
            setDataTime(transactionData?.dataTime.orEmpty())
        }
    }

}
