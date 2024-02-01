package com.molyavin.expensetracker.presentation.viewmodel.setting

import com.molyavin.expensetracker.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.auth.AuthorizationScreen
import com.molyavin.expensetracker.presentation.viewmodel.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    navigator: Navigator, toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    fun logOut() {
        setStatusRememberMeUseCase.execute(false)
        nextScreen(AuthorizationScreen::class.java)
    }
}