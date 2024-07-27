package com.molyavin.expensetracker.di.module

import android.content.Context
import com.molyavin.expensetracker.data.network.api.ApiManager
import com.molyavin.expensetracker.data.network.api.ApiManagerType
import com.molyavin.expensetracker.data.network.api.ApiService
import com.molyavin.expensetracker.data.repository.SharedPreferenceRepository
import com.molyavin.expensetracker.data.repository.SharedPreferenceRepositoryImpl
import com.molyavin.expensetracker.data.storage.DBSharedPreference
import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.utils.AppDispatchers
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun provideContext() = context

    @Provides
    @AppScope
    fun provideAppDispatchers() = AppDispatchers()

    @Provides
    @AppScope
    fun provideDBSharedPreference(): DBSharedPreference = DBSharedPreference(context)

    @Provides
    @AppScope
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.monobank.ua/bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @AppScope
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @AppScope
    fun provideApiManager(apiService: ApiService): ApiManagerType =
        ApiManager(apiService)

    @Provides
    @AppScope
    fun provideSharedPreferenceRepository(dbSharedPreference: DBSharedPreference): SharedPreferenceRepository =
        SharedPreferenceRepositoryImpl(dbSharedPreference)


}