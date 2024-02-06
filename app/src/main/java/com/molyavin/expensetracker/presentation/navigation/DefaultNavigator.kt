package com.molyavin.expensetracker.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.molyavin.expensetracker.domain.model.TransactionVM

class DefaultNavigator(private val context: Context) : Navigator {


    override fun navigateTo(destination: Class<*>) {
        context.startActivity(Intent(context, destination))
    }


    override fun navigateTo(destination: Class<*>, params: Map<String, Any>) {
        val intent = Intent(context, destination)
        val bundle = Bundle()
        for ((key, value) in params) {
            when (value) {
                is Int -> bundle.putInt(key, value)
                is String -> bundle.putString(key, value)
                is Float -> bundle.putFloat(key, value)
                is Boolean -> bundle.putBoolean(key, value)
            }
        }
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    override fun navigateBack() {
        (context as? Activity)?.finish()
    }
}