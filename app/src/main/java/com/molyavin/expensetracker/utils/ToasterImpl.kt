package com.molyavin.expensetracker.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToasterImpl @Inject constructor(
    private val context: Context,
    private val dispatcher: AppDispatchers
) : Toaster {

    override fun show(message: String) {
        CoroutineScope(dispatcher.main).launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLong(message: String) {
        CoroutineScope(dispatcher.main).launch {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

}