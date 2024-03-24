package com.molyavin.expensetracker.presentation.screen.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.data.local.model.TransactionDTO
import com.molyavin.expensetracker.domain.usecase.transaction.AddTransactionUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import com.molyavin.expensetracker.utils.DateTimeFormatter
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class AddTransactionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    private var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    private var _isIncome = MutableStateFlow(true)
    val isIncome: StateFlow<Boolean> = _isIncome

    private var _id = MutableStateFlow(0L)
    val id: StateFlow<Long> = _id


    fun setLabel(newLabel: TextFieldValue) {
        _label.value = newLabel
    }

    fun setAmount(newAmount: TextFieldValue) {
        _amount.value = newAmount
    }

    fun setIsIncome(value: Boolean) {
        _isIncome.value = value
    }

    fun setId(id: Long) {
        _id.value = id
    }

    fun goToHomeScreen() = nextScreen(HomeScreen::class.java)

    fun addTransactionItem() {
        viewModelScope.launch {
            val currentDateTime = Calendar.getInstance()
            val formattedDateTime = DateTimeFormatter.formatDateTime(currentDateTime)

            val item = TransactionDTO(
                title = label.value.text,
                amount = amount.value.text.toFloat(),
                isIncome = isIncome.value,
                date = formattedDateTime
            )
            addTransactionUseCase.execute(item)

        }
    }
}