package com.molyavin.expensetracker.di.module

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.expensetracker.data.repository.SettingRepository
import com.molyavin.expensetracker.data.repository.SettingRepositoryImpl
import com.molyavin.expensetracker.data.repository.UserRepository
import com.molyavin.expensetracker.data.repository.UserRepositoryImpl
import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.storage.DBSharedPreference
import com.molyavin.expensetracker.di.scope.AppScope
import com.molyavin.expensetracker.presentation.navigation.DefaultNavigator
import com.molyavin.expensetracker.presentation.navigation.Navigator
import com.molyavin.expensetracker.utils.AppDispatchers
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun provideContext() = context

    @Provides
    @AppScope
    fun provideNavigator(context: Context): Navigator {
        return DefaultNavigator(context)
    }

    @Provides
    @AppScope
    fun provideFirebaseAunt() = FirebaseAuth.getInstance()

    @Provides
    @AppScope
    fun provideDataBase(
        firebaseAuth: FirebaseAuth,
        dbSharedPreference: DBSharedPreference
    ): UserRepository =
        UserRepositoryImpl(fireBaseAunt = firebaseAuth, dbSharedPreference = dbSharedPreference)

    @Provides
    @AppScope
    fun provideAppDispatchers() = AppDispatchers()

    @Provides
    @AppScope
    fun provideDBSharedPreference(): DBSharedPreference =
        DBSharedPreference(context)

    @Provides
    @AppScope
    fun provideOnBoardingRepository(dbSharedPreference: DBSharedPreference): SettingRepository =
        SettingRepositoryImpl(dbSharedPreference)

    @Provides
    @AppScope
    fun provideMainDB(): DBRoom {
        return Room.databaseBuilder(context, DBRoom::class.java, "main.db").build()
    }
}