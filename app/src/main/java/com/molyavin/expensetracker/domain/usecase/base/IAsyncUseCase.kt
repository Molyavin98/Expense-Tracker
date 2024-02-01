package com.molyavin.expensetracker.domain.usecase.base

interface IAsyncUseCase<T, R> {

    suspend fun execute(income: T): R
}