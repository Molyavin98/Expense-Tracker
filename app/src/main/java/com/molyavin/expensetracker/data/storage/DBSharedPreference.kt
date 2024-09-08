package com.molyavin.expensetracker.data.storage

import android.content.Context
import android.content.SharedPreferences

class DBSharedPreference(context: Context) {

    private var sharedPreferences: SharedPreferences = context
        .getSharedPreferences("DataBase", Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(key: String, data: String) {
        editor.putString(key, data)
        editor.apply()
    }

    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveValue(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getValue(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }
}