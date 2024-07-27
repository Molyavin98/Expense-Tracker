package com.molyavin.expensetracker.data.repository

import com.molyavin.expensetracker.data.storage.DBSharedPreference


class SharedPreferenceRepositoryImpl(private val sharedPreference: DBSharedPreference) :
    SharedPreferenceRepository {

    override fun saveSetting(key: String, setting: Boolean) {
        sharedPreference.saveValue(key = key, value = setting)
    }

    override fun saveSetting(key: String, setting: String) {
        sharedPreference.saveData(key = key, data = setting)
    }

    override fun readSetting(key: String, defValue: Boolean): Boolean {
        return sharedPreference.getValue(key = key, defValue = defValue)
    }

    override fun readSetting(key: String): String {
        return sharedPreference.getData(key = key) ?: ""
    }

}