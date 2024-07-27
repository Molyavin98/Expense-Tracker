package com.molyavin.expensetracker.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.molyavin.expensetracker.data.repository.FirebaseRepository
import com.molyavin.expensetracker.data.repository.FirebaseRepositoryImpl
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
    fun provideFirebaseRepository(
        database: DatabaseReference,
        auth: FirebaseAuth
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(database, auth)
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