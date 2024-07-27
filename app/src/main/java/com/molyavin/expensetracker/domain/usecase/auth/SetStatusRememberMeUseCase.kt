package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.repository.SharedPreferenceRepository
import com.molyavin.expensetracker.domain.usecase.IUseCase
import javax.inject.Inject

class SetStatusRememberMeUseCase @Inject constructor(private val sharedPreferenceRepository: SharedPreferenceRepository) :
    IUseCase<Boolean, Unit> {

    override fun execute(income: Boolean) {
        sharedPreferenceRepository.saveSetting("RememberMe", income)
    }
}