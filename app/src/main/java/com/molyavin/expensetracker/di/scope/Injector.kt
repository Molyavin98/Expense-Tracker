package com.molyavin.expensetracker.di.scope

import android.content.Context
import com.molyavin.expensetracker.di.component.AppComponent
import com.molyavin.expensetracker.di.component.DaggerAppComponent
import com.molyavin.expensetracker.di.module.AppModule

object Injector {

    lateinit var INSTANCE: AppComponent

    fun inject(context: Context) {
        INSTANCE = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }
}