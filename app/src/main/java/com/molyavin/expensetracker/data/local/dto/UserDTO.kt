package com.molyavin.expensetracker.data.local.dto

import com.molyavin.expensetracker.domain.model.UserVM


data class UserDTO(val email: String, val password: String, val passwordConfirm: String?)

fun UserVM.toDTO(): NewUserDTO {
    return NewUserDTO(email = email, password = password, passwordConfirm = passwordConfirm)
}
