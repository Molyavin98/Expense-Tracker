package com.molyavin.expensetracker.presentation.screen.setting

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.expensetracker.domain.usecase.home.GetExchangeRateUseCase
import com.molyavin.expensetracker.domain.usecase.setting.GetCurrencyUseCase
import com.molyavin.expensetracker.domain.usecase.setting.SetCurrencyUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val appDispatcher: AppDispatchers,
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private val _currencyDollar = MutableStateFlow("")
    val currencyDollar: StateFlow<String> = _currencyDollar

    private val _currencyEuro = MutableStateFlow("")
    val currencyEuro: StateFlow<String> = _currencyEuro

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        loadCurrency()
    }

    fun logOut(navController: NavController) {
        setStatusRememberMeUseCase.execute(false)
        navController.navigate(Screen.AuthScreen.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun loadCurrency() {
        viewModelScope.launch(appDispatcher.io) {
            startCoroutine(runnable = {
                val currencyInfoList = getExchangeRateUseCase.execute(null)

                currencyInfoList.filter { it.currencyCodeA == 840 && it.currencyCodeB == 980 }
                    .onEach {
                        val currency =
                            "\uD83C\uDDFA\uD83C\uDDF8 ${
                                String.format(
                                    "%.2f",
                                    it.rateBuy
                                )
                            } / ${String.format("%.2f", it.rateSell)}"
                        _currencyDollar.emit(currency)
                    }

                currencyInfoList.filter { it.currencyCodeA == 978 && it.currencyCodeB == 980 }
                    .onEach {
                        val currency =
                            "\uD83C\uDDEA\uD83C\uDDFA ${
                                String.format(
                                    "%.2f",
                                    it.rateBuy
                                )
                            } / ${String.format("%.2f", it.rateSell)}"
                        _currencyEuro.emit(currency)
                    }
            }, onError = { exception ->
                exception?.printStackTrace()
            })
        }
    }

}