package com.molyavin.expensetracker.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    private val toaster: Toaster
) : ViewModel(), DefaultLifecycleObserver {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    fun showMessage(message: String) {
        toaster.show(message)
    }

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
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