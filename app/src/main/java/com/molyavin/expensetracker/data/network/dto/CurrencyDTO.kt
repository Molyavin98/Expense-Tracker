package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.domain.model.Currency
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDTO(
    @Json(name = "currencyCodeA") val currencyCodeA: Int,
    @Json(name = "currencyCodeB") val currencyCodeB: Int,
    @Json(name = "date") val date: Long,
    @Json(name = "rateSell") val rateSell: Float,
    @Json(name = "rateBuy") val rateBuy: Float,
    @Json(name = "rateCross") val reteCross: Float,
)

fun CurrencyDTO.asPresentation() = Currency(
    currencyCodeA = currencyCodeA,
    currencyCodeB = currencyCodeB,
    date = date,
    rateSell = rateSell,
    rateBuy = rateBuy,
    reteCross = reteCross
)


fun List<CurrencyDTO>.asPresentation() = map { it.asPresentation() }