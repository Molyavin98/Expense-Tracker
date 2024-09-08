package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.currency

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.setting.SetCurrencyUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.currencyToFlag
import com.molyavin.expensetracker.utils.flagToCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyBottomSheetViewModel @Inject constructor(
    private val setCurrencyUseCase: SetCurrencyUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private val _currency = MutableStateFlow("")
    val currency: StateFlow<String> = _currency

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        fetchingCurrency()
    }

    fun saveCurrency(currency: String) {
        viewModelScope.launch {
            val currencyWithoutFlag = currency.flagToCurrency()
            setCurrencyUseCase.execute(currencyWithoutFlag)
            fetchingCurrency()
        }
    }

    private fun fetchingCurrency() {
        viewModelScope.launch {
            val currency = getCurrencyUseCase.execute(null)
            _currency.value = currency.currencyToFlag()
        }
    }

}
