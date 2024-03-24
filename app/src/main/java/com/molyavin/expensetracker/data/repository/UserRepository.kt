package com.molyavin.expensetracker.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.molyavin.expensetracker.data.network.dto.NewUserDTO
import com.molyavin.expensetracker.data.network.dto.UserDTO


interface UserRepository {

    fun registerUser(data: NewUserDTO): Task<AuthResult>
    fun loginUser(data: NewUserDTO): Task<AuthResult>

    suspend fun saveUser(itemDto: UserDTO)
    suspend fun getUser(): UserDTO

    suspend fun checkUserStatus(): Boolean

}