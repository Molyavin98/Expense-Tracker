package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.domain.model.NewUser
import com.molyavin.expensetracker.domain.usecase.IUseCase
import javax.inject.Inject

class ValidateRegisterUserInfoUseCase @Inject constructor() : IUseCase<NewUser, Any?> {

    override fun execute(income: NewUser): Boolean{
        return income.email.isNotEmpty() || income.password.isNotEmpty() || !income.passwordConfirm.isNullOrEmpty()
    }
}