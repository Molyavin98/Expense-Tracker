package com.molyavin.expensetracker.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class NavigatorManager(private val context: Context) : Navigator {


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
        context.startActivity(Intent(context, context.javaClass))
        (context as? Activity)?.finish()
        (context as? Activity)?.overridePendingTransition(0, 0)
    }

    override fun exitFromAccount(authScreenClass: Class<*>) {
        val intent = Intent(context, authScreenClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(0, 0)
        (context as? Activity)?.finish()
    }

}