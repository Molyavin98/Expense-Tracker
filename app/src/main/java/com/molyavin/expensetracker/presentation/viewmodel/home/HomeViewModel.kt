package com.molyavin.expensetracker.presentation.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.data.room.TransactionEntity
import com.molyavin.expensetracker.domain.model.TransactionVM
import com.molyavin.expensetracker.domain.usecase.transaction.DeleteTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.setting.SettingScreen
import com.molyavin.expensetracker.presentation.screen.statistics.StatisticsScreen
import com.molyavin.expensetracker.presentation.screen.transaction.AddTransactionScreen
import com.molyavin.expensetracker.presentation.screen.transaction.EditTransactionScreen
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private val _itemsList = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val itemsList: StateFlow<List<TransactionEntity>> = _itemsList

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

    fun deleteItem(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(200)
            deleteTransactionUseCase.execute(transactionEntity)
            _isLoading.value = false
        }
    }

    fun nextScreenEdit(transactionVM: TransactionVM) {
        val params = mutableMapOf<String, Any>()
        params["id"] = transactionVM.id ?: 0
        params["label"] = transactionVM.label
        params["amount"] = transactionVM.amount
        params["isIncome"] = transactionVM.isIncome

        nextScreen(EditTransactionScreen::class.java, params)
    }

    fun nextScreenAddTransaction() = nextScreen(AddTransactionScreen::class.java)

    fun nextScreenSetting() = nextScreen(SettingScreen::class.java)

    fun nextScreenStatistics() = nextScreen(StatisticsScreen::class.java)

}