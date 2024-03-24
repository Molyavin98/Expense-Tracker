package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.domain.model.NewUser


data class NewUserDTO(val email: String, val password: String, val passwordConfirm: String?)

fun NewUser.toDTO(): NewUserDTO {
    return NewUserDTO(email = email, password = password, passwordConfirm = passwordConfirm)
}