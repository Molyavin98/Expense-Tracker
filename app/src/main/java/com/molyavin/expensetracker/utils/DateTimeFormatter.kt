package com.molyavin.expensetracker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateTimeFormatter {
    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.getDefault())

    fun formatDateTime(calendar: Calendar): String {
        return dateFormat.format(calendar.time)
    }
}
