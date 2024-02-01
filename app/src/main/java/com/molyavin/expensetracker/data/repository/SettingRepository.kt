package com.molyavin.expensetracker.data.repository

interface SettingRepository {

    fun saveSetting(key: String, setting: Boolean)
    fun readSetting(key: String, defValue: Boolean): Boolean
}