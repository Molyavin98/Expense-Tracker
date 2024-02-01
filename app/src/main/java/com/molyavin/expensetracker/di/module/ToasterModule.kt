package com.molyavin.expensetracker.di.module

import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.utils.Toaster
import com.molyavin.expensetracker.utils.ToasterImpl
import dagger.Module
import dagger.Provides

@Module
class ToasterModule {

    @Provides
    @AppScope
    fun provideToaster(toasterImpl: ToasterImpl): Toaster = toasterImpl
}