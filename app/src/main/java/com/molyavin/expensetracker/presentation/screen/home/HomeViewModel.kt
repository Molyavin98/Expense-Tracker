package com.molyavin.expensetracker.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.data.local.model.TransactionDTO
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.transaction.DeleteTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.setting.SettingScreen
import com.molyavin.expensetracker.presentation.screen.statistics.StatisticsScreen
import com.molyavin.expensetracker.presentation.screen.transaction.AddTransactionScreen
import com.molyavin.expensetracker.presentation.screen.transaction.EditTransactionScreen
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private val _itemsList = MutableStateFlow<List<TransactionDTO>>(emptyList())
    val itemsList: StateFlow<List<TransactionDTO>> = _itemsList

    override fun onStart() {
        super.onStart()
        viewModelScope.launch {
            _isLoading.value = true
            delay(500)
            load()
            _isLoading.value = false
        }
    }

    private fun load() {
        viewModelScope.launch {
            getTransactionUseCase.execute(Unit).collect { _itemsList.value = it }
        }
    }

    fun deleteItem(transactionDTO: TransactionDTO) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(200)
            deleteTransactionUseCase.execute(transactionDTO)
            _isLoading.value = false
        }
    }

    fun nextScreenEdit(transaction: Transaction) {
        val params = mutableMapOf<String, Any>()
        params["id"] = transaction.id ?: 0
        params["label"] = transaction.label
        params["amount"] = transaction.amount
        params["isIncome"] = transaction.isIncome

        nextScreen(EditTransactionScreen::class.java, params)
    }

    fun nextScreenAddTransaction() = nextScreen(AddTransactionScreen::class.java)

    fun nextScreenSetting() = nextScreen(SettingScreen::class.java)

    fun nextScreenStatistics() = nextScreen(StatisticsScreen::class.java)

}