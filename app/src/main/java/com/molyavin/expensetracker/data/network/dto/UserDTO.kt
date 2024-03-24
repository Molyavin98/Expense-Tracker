package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.data.network.dto.NewUserDTO
import com.molyavin.expensetracker.domain.model.User


data class UserDTO(val email: String, val password: String, val passwordConfirm: String?)

fun User.toDTO(): NewUserDTO {
    return NewUserDTO(email = email, password = password, passwordConfirm = passwordConfirm)
}
