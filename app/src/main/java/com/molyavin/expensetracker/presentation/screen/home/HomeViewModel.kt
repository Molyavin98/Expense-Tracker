package com.molyavin.expensetracker.presentation.screen.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.transaction.DeleteTransactionUseCase
import com.molyavin.expensetracker.domain.usecase.transaction.GetTransactionUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private val _itemsList = MutableStateFlow<List<Transaction>>(emptyList())
    val itemsList: StateFlow<List<Transaction>> = _itemsList

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        load()
    }

    private fun load() {
        viewModelScope.launch {
            startCoroutine(runnable = {
                setLoading(true)
                _itemsList.value = getTransactionUseCase.execute(Unit)
                setLoading(false)
            }, onError = {
                showMessage(it?.message ?: "Error")
            })
        }
    }

    fun deleteItem(transaction: Transaction) {
        viewModelScope.launch {
            deleteTransactionUseCase.execute(transaction)
            load()
        }
    }

    fun nextScreenEdit(navController: NavController, id: String) =
        navController.navigate(Screen.EditTransactionScreen.route.replace("{id}", id))

    fun nextScreenAddTransaction(navController: NavController) =
        navController.navigate(Screen.AddTransactionScreen.route)

    fun nextScreenSetting(navController: NavController) =
        navController.navigate(Screen.SettingScreen.route)

    fun nextScreenStatistics(navController: NavController) =
        navController.navigate(Screen.StatisticsScreen.route)

}