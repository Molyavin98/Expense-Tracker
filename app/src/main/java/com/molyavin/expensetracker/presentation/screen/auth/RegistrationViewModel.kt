package com.molyavin.expensetracker.presentation.screen.auth

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.molyavin.expensetracker.domain.model.NewUser
import com.molyavin.expensetracker.domain.usecase.auth.RegisterUserUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.presentation.navigation.Screen
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private var _email = MutableStateFlow(TextFieldValue())
    var email: StateFlow<TextFieldValue> = _email

    private var _passwordOne = MutableStateFlow(TextFieldValue())
    var password: StateFlow<TextFieldValue> = _passwordOne

    private var _passwordTwo = MutableStateFlow(TextFieldValue())
    var passwordConfirm: StateFlow<TextFieldValue> = _passwordTwo

    fun setPhone(phone: TextFieldValue) {
        _email.value = phone
    }

    fun setPasswordOne(password: TextFieldValue) {
        _passwordOne.value = password
    }

    fun setPasswordTwo(password: TextFieldValue) {
        _passwordTwo.value = password
    }

    fun startAuthScreen(navController: NavController) {
        navController.navigate(Screen.AuthScreen.route)
    }

    fun registration(navController: NavController) {

        val user = NewUser(
            email = email.value.text,
            password = password.value.text,
            passwordConfirm = passwordConfirm.value.text
        )

        viewModelScope.launch {
            startCoroutine(runnable = {
                if (registerUserUseCase.execute(user)) {
                    navController.navigate(Screen.AuthScreen.route)
                }
            }, onError = { exception ->
                showMessage("${exception?.message}")
            })
        }
    }

}
