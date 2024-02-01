package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.domain.model.NewUserVM
import com.molyavin.expensetracker.domain.usecase.base.IUseCase
import javax.inject.Inject

class ValidateLoginUserInfoUseCase @Inject constructor() : IUseCase<NewUserVM, Any?> {
    override fun execute(income: NewUserVM): Boolean {
        return income.email.isNotEmpty() || income.password.isNotEmpty()
    }

}