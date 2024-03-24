package com.molyavin.expensetracker.domain.usecase

interface IUseCase<T, R> {
    fun execute(income: T): R
}