package com.molyavin.expensetracker.presentation.screen.setting

import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.expensetracker.domain.usecase.home.GetCurrencyUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

class SettingViewModel @Inject constructor(
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val appDispatcher: AppDispatchers,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    navigator: Navigator, toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private val _currencyDollar = MutableStateFlow("")
    val currencyDollar: StateFlow<String> = _currencyDollar

    private val _currencyEuro = MutableStateFlow("")
    val currencyEuro: StateFlow<String> = _currencyEuro


    override fun onStart() {
        super.onStart()
        loadCurrency()
    }

    fun logOut() {
        setStatusRememberMeUseCase.execute(false)
        exitFromAccount(AuthorizationScreen::class.java)
    }

    private fun loadCurrency() {
        viewModelScope.launch(appDispatcher.io) {
            val currencyInfoList = getCurrencyUseCase.execute(null)

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
        }
    }


}