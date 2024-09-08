package com.molyavin.expensetracker.presentation.screen.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.home.GetBudgetUseCase
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.DeleteTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.dateFormatter
import com.molyavin.expensetracker.utils.toCurrencySymbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private val _itemsTransactionList = MutableStateFlow<List<Transaction>>(emptyList())
    val itemsTransactionList: StateFlow<List<Transaction>> = _itemsTransactionList

    private val _screenState = MutableStateFlow(HistoryMode.TODAY)
    val screenState: StateFlow<Int> = _screenState

    private var _budget = MutableStateFlow("")
    val budget: StateFlow<String> = _budget

    private val _currency = MutableStateFlow("")
    val currency: StateFlow<String> = _currency

    private var _id = MutableStateFlow(String())
    val id: StateFlow<String> = _id

    private val _showBottomSheetAddTransaction = MutableStateFlow(false)
    val showBottomSheetAddTransaction: StateFlow<Boolean> = _showBottomSheetAddTransaction

    private val _showBottomSheetAddBudget = MutableStateFlow(false)
    val showBottomSheetAddBudget: StateFlow<Boolean> = _showBottomSheetAddBudget

    private val _showBottomSheetEditTransaction = MutableStateFlow(false)
    val showBottomSheetEditTransaction: StateFlow<Boolean> = _showBottomSheetEditTransaction

    private val _showBottomSheetShowTransaction = MutableStateFlow(false)
    val showBottomSheetShowTransaction: StateFlow<Boolean> = _showBottomSheetShowTransaction

    private val _totalAmount = MutableStateFlow("")
    val totalAmount: StateFlow<String> = _totalAmount

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        load()
    }

    fun fetchReload() = load()

    private fun load() {
        viewModelScope.launch {
            startCoroutine(runnable = {
                setLoading(true)
                _budget.value = getBudgetUseCase.execute(Unit).budget.toString()
                _itemsTransactionList.value = loadSortedTransaction()
                loadCurrency()
                getTotalAmount()
                setLoading(false)
            }, onError = {
                showMessage(it?.message ?: "Error loading data")
            })
        }
    }

    fun onScreenStateChanged(isDay: Boolean) {
        viewModelScope.launch {
            if (isDay) {
                _screenState.value = HistoryMode.MONTH
            } else {
                _screenState.value = HistoryMode.TODAY
            }
        }
    }

    fun deleteItem(transaction: Transaction) {
        viewModelScope.launch {
            deleteTransactionUseCase.execute(transaction)
            load()
        }
    }

    private fun loadCurrency() {
        viewModelScope.launch {
            val currency = getCurrencyUseCase.execute(null)
            _currency.value = currency.toCurrencySymbol()
        }
    }

    private suspend fun loadSortedTransaction(): List<Transaction> {
        val transactionList = getTransactionUseCase.execute(Unit)

        return if (screenState.value == HistoryMode.TODAY) {
            transactionList.filter { it.date == dateFormatter() }
        } else {
            transactionList
        }.sortedByDescending { it.dataTime }
    }

    fun nextScreenSetting(navController: NavController) =
        navController.navigate(Screen.SettingScreen.route)

    fun setId(newId: String) {
        _id.value = newId
    }

    fun setBottomSheetAddTransaction(value: Boolean) {
        _showBottomSheetAddTransaction.value = value
    }

    fun setBottomSheetAddBudget(value: Boolean) {
        _showBottomSheetAddBudget.value = value
    }

    fun setBottomSheetEditTransaction(value: Boolean) {
        _showBottomSheetEditTransaction.value = value
    }

    fun setBottomSheetShowTransaction(value: Boolean) {
        _showBottomSheetShowTransaction.value = value
    }

    private fun getTotalAmount() {
        viewModelScope.launch {
            val totalAmount = getTransactionUseCase.execute(Unit)
                .filter { it.date == dateFormatter() }
                .sumOf { it.amount }

            _totalAmount.value = totalAmount.toString()
        }
    }

}