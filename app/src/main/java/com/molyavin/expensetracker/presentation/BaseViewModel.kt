package com.molyavin.expensetracker.presentation

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

    fun nextScreen(destination: Class<*>, params: Map<String, Any>) {
        navigator.navigateTo(destination, params)
    }

    fun navigateBack() {
        navigator.navigateBack()
    }

    fun exitFromAccount(destination: Class<*>) {
        navigator.exitFromAccount(destination)
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

    open fun onCreate() {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {}
}