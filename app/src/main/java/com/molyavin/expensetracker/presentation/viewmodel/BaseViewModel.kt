package com.molyavin.expensetracker.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun nextScreen(screen: Class<*>) {
        navigator.navigateTo(screen)
    }

    fun navigateBack() {
        navigator.navigateBack()
    }

    fun showMessage(message: String) {
        toaster.show(message)
    }

    protected suspend fun startCoroutine(
        runnable: suspend () -> Unit,
        onError: ((Throwable?) -> Unit)? = null,
    ) {
        try {
            runnable()
        } catch (t: Throwable) {
            onError?.invoke(t)
        }
    }
}