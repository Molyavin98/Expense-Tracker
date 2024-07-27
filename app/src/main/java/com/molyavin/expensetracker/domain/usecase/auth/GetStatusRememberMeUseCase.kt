package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.repository.SharedPreferenceRepository
import com.molyavin.expensetracker.domain.usecase.IUseCase
import javax.inject.Inject

class GetStatusRememberMeUseCase @Inject constructor(private val sharedPreferenceRepository: SharedPreferenceRepository) :
    IUseCase<Any?, Boolean> {

    override fun execute(income: Any?): Boolean {
        return sharedPreferenceRepository.readSetting("RememberMe", false)
    }
}