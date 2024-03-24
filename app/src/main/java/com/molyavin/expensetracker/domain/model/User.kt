package com.molyavin.expensetracker.domain.model

class User(val email: String, val password: String, val passwordConfirm: String? = null)