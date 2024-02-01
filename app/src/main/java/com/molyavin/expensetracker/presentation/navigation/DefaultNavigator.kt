package com.molyavin.expensetracker.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent

class DefaultNavigator(private val context: Context) : Navigator {


    override fun navigateTo(destination: Class<*>) {
        context.startActivity(Intent(context, destination))
    }

    override fun navigateBack() {
        (context as? Activity)?.finish()
    }
}