package com.molyavin.expensetracker.presentation.screen.home

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(HistoryMode.TODAY, HistoryMode.MONTH)
annotation class HistoryMode {
    companion object {
        const val TODAY = 0
        const val MONTH = 1
    }
}
