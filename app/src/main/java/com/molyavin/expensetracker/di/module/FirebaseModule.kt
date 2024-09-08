package com.molyavin.expensetracker.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.molyavin.expensetracker.data.repository.BudgetRepository
import com.molyavin.expensetracker.data.repository.BudgetRepositoryImpl
import com.molyavin.expensetracker.data.repository.CurrencyRepository
import com.molyavin.expensetracker.data.repository.CurrencyRepositoryImpl
import com.molyavin.expensetracker.data.repository.NewsLinkRepository
import com.molyavin.expensetracker.data.repository.NewsLinkRepositoryImpl
import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.data.repository.TransactionRepositoryImpl
import com.molyavin.expensetracker.data.repository.UserRepository
import com.molyavin.expensetracker.data.repository.UserRepositoryImpl
import com.molyavin.expensetracker.data.storage.DBSharedPreference
import com.molyavin.expensetracker.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    @AppScope
    fun provideTransactionRepository(
        database: DatabaseReference,
        auth: FirebaseAuth
    ): TransactionRepository {
        return TransactionRepositoryImpl(database, auth)
    }

    @Provides
    @AppScope
    fun provideBudgetRepository(
        database: DatabaseReference,
        auth: FirebaseAuth
    ): BudgetRepository {
        return BudgetRepositoryImpl(database, auth)
    }

    @Provides
    @AppScope
    fun provideCurrencyRepository(
        database: DatabaseReference,
        auth: FirebaseAuth
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(database, auth)
    }

    @Provides
    @AppScope
    fun provideNewsLinkRepository(
        database: DatabaseReference,
    ): NewsLinkRepository {
        return NewsLinkRepositoryImpl(database)
    }

    @Provides
    @AppScope
    fun provideFirebaseAunt() = FirebaseAuth.getInstance()

    @Provides
    @AppScope
    fun provideDatabaseReference(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @Provides
    @AppScope
    fun provideDataBase(
        firebaseAuth: FirebaseAuth,
        dbSharedPreference: DBSharedPreference
    ): UserRepository =
        UserRepositoryImpl(fireBaseAunt = firebaseAuth, dbSharedPreference = dbSharedPreference)
}