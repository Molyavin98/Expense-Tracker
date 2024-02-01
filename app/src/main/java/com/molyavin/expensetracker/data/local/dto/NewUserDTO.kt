package com.molyavin.expensetracker.data.local.dto

import com.molyavin.expensetracker.domain.model.NewUserVM


data class NewUserDTO(val email: String, val password: String, val passwordConfirm: String?)

fun NewUserVM.toDTO(): NewUserDTO {
    return NewUserDTO(email = email, password = password, passwordConfirm = passwordConfirm)
}