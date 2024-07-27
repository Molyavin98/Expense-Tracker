package com.molyavin.expensetracker.presentation.screen.transaction

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.transaction.EditTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionByIdUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.utils.DateTimeFormatter
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.dataTimeFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class EditTransactionViewModel @Inject constructor(
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val editTransactionUseCase: EditTransactionUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private var _id = MutableStateFlow(String())
    val id: StateFlow<String> = _id

    private var _label = MutableStateFlow(TextFieldValue())
    val label: StateFlow<TextFieldValue> = _label

    private var _amount = MutableStateFlow(TextFieldValue())
    val amount: StateFlow<TextFieldValue> = _amount

    private var _isIncome = MutableStateFlow(true)
    val isIncome: StateFlow<Boolean> = _isIncome

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        initializeWithTransactionData()
    }

    fun editTransaction() {
        viewModelScope.launch {
            setLoading(true)
            editTransactionUseCase.execute(
                Transaction(
                    id = id.value,
                    title = label.value.text,
                    amount = amount.value.text.toDouble(),
                    income = isIncome.value,
                    date = dataTimeFormatter()
                )
            )
            setLoading(false)
        }
    }

    fun setId(newId: String) {
        _id.value = newId
    }

    fun setLabel(newLabel: TextFieldValue) {
        _label.value = newLabel
    }

    fun setAmount(newAmount: TextFieldValue) {
        _amount.value = newAmount
    }

    fun setIsIncome(value: Boolean) {
        _isIncome.value = value
    }

    fun goToHomeScreen(navController: NavController) = navController.navigate(Screen.HomeScreen.route)

    private fun initializeWithTransactionData() {
        viewModelScope.launch {
            val transactionData = getTransactionByIdUseCase.execute(id.value)
            setLabel(TextFieldValue(transactionData?.title.orEmpty()))
            setAmount(TextFieldValue(transactionData?.amount.toString()))
            setIsIncome(transactionData?.income ?: false)
        }
    }

}