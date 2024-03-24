package com.molyavin.expensetracker.presentation.screen.auth

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.model.NewUser
import com.molyavin.expensetracker.domain.usecase.auth.LoginUserUseCase
import com.molyavin.expensetracker.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.presentation.screen.home.HomeScreen
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    navigator: Navigator,
    toaster: Toaster
) : BaseViewModel(navigator, toaster) {

    private var _email = MutableStateFlow(TextFieldValue())
    val email: StateFlow<TextFieldValue> = _email

    private var _password = MutableStateFlow(TextFieldValue())
    var password: StateFlow<TextFieldValue> = _password

    private var _statusCheckBox = MutableStateFlow(false)
    var statusCheckBox: StateFlow<Boolean> = _statusCheckBox

    fun setPhone(phone: TextFieldValue) {
        _email.value = phone
    }

    fun setPassword(password: TextFieldValue) {
        _password.value = password
    }

    fun setStatusCheckBox(status: Boolean) {
        _statusCheckBox.value = status
    }

    fun startRegistration() {
        nextScreen(RegistrationScreen::class.java)
    }

    fun login() {
        val user = NewUser(email = email.value.text, password = password.value.text)
        viewModelScope.launch {
            startCoroutine(runnable = {
                if (loginUserUseCase.execute(user)) {
                    _isLoading.value = true
                    statusRememberMe()
                    nextScreen(HomeScreen::class.java)
                    _isLoading.value = false
                }
            }, onError = { exception ->
                showMessage("${exception?.message}")
                exception?.printStackTrace()
            })
        }
    }

    private fun statusRememberMe() {
        setStatusRememberMeUseCase.execute(statusCheckBox.value)
    }

}
