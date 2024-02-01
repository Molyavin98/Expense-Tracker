package com.molyavin.expensetracker.data.repository

import com.molyavin.expensetracker.data.storage.DBSharedPreference


class SettingRepositoryImpl (private val sharedPreference: DBSharedPreference) :
    SettingRepository {

    override fun saveSetting(key: String, setting: Boolean) {
        sharedPreference.saveValue(key = key, value = setting)
    }

    override fun readSetting(key: String, defValue: Boolean): Boolean {
        return sharedPreference.getValue(key = key, defValue = defValue)
    }

}