package com.molyavin.expensetracker.utils

import com.molyavin.expensetracker.presentation.screen.setting.Currency

fun String.currencyToFlag(): String {
    return when {
        this == Currency.USD -> Currency.USD_WITH_FLAG
        this == Currency.EUR -> Currency.EUR_WITH_FLAG
        this == Currency.UAH -> Currency.UAH_WITH_FLAG
        else -> ""
    }
}

fun String.flagToCurrency(): String {
    return when {
        this.contains(Currency.USD_WITH_FLAG) -> Currency.USD
        this.contains(Currency.EUR_WITH_FLAG) -> Currency.EUR
        this.contains(Currency.UAH_WITH_FLAG) -> Currency.UAH
        else -> ""
    }
}

fun String.toCurrencySymbol(): String {
    return when {
        this == Currency.USD -> "$"
        this == Currency.EUR -> "€"
        this == Currency.UAH -> "₴"
        else -> ""
    }
}
