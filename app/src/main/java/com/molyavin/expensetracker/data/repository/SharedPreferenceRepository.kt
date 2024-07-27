package com.molyavin.expensetracker.data.repository

interface SharedPreferenceRepository {
    fun saveSetting(key: String, setting: Boolean)
    fun saveSetting(key: String, setting: String)
    fun readSetting(key: String, defValue: Boolean): Boolean
    fun readSetting(key: String): String
}