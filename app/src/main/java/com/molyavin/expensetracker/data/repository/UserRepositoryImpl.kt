package com.molyavin.expensetracker.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.expensetracker.data.network.dto.NewUserDTO
import com.molyavin.expensetracker.data.network.dto.UserDTO
import com.molyavin.expensetracker.data.storage.DBSharedPreference
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireBaseAunt: FirebaseAuth,
    private val dbSharedPreference: DBSharedPreference,
) : UserRepository {

    override fun registerUser(data: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.createUserWithEmailAndPassword(data.email, data.password)
    }

    override fun loginUser(data: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.signInWithEmailAndPassword(data.email, data.password)
    }

    override suspend fun saveUser(itemDto: UserDTO) {
        dbSharedPreference.saveData(key = "email", data = itemDto.email)
        dbSharedPreference.saveData(key = "password", data = itemDto.password)
    }

    override suspend fun getUser(): UserDTO {
        val email = dbSharedPreference.getData(key = "email")
        val password = dbSharedPreference.getData(key = "password")
        return UserDTO(email = email!!, password = password!!, passwordConfirm = password)
    }


    override suspend fun checkUserStatus(): Boolean {
        return false
    }

}