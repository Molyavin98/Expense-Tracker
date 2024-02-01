package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.repository.SettingRepository
import com.molyavin.expensetracker.domain.usecase.base.IUseCase
import javax.inject.Inject

class SetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Boolean, Unit> {

    override fun execute(income: Boolean) {
        settingRepository.saveSetting("RememberMe", income)
    }
}