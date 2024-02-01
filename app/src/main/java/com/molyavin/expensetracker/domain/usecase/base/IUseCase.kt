package com.molyavin.expensetracker.domain.usecase.base

interface IUseCase<T, R> {
    fun execute(income: T): R
}