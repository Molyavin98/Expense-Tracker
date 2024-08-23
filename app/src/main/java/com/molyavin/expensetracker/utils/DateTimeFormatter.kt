package com.molyavin.expensetracker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateTimeFormatter {
    @SuppressLint("ConstantLocale")
    private val dateTimeFormat = SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.getDefault())

    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun formatDateTime(calendar: Calendar): String {
        return dateTimeFormat.format(calendar.time)
    }

    fun formatDate(calendar: Calendar): String {
        return dateFormat.format(calendar.time)
    }
}

fun dataTimeFormatter(): String {
    val currentDateTime = Calendar.getInstance()
    return DateTimeFormatter.formatDateTime(currentDateTime)
}

fun dateFormatter(): String {
    val currentDateTime = Calendar.getInstance()
    return DateTimeFormatter.formatDate(currentDateTime)
}
