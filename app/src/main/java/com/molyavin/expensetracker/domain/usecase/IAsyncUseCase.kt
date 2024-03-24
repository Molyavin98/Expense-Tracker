package com.molyavin.expensetracker.domain.usecase

interface IAsyncUseCase<T, R> {

    suspend fun execute(income: T): R
}