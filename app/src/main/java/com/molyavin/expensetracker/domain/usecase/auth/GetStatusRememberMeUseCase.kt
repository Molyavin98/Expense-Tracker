package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.repository.SettingRepository
import com.molyavin.expensetracker.domain.usecase.IUseCase
import javax.inject.Inject

class GetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Any?, Boolean> {

    override fun execute(income: Any?): Boolean {
        return settingRepository.readSetting("RememberMe", false)
    }
}