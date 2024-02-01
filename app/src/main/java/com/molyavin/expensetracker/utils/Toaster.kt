package com.molyavin.expensetracker.utils

interface Toaster {
    fun show(message: String)

    fun showLong(message: String)
}