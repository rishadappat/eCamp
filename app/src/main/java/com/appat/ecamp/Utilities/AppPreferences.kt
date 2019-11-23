package com.appat.ecamp.Utilities

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager

object AppPreferences {

    private fun getAppPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(Utility.getContext())
    }

    fun getAppPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun setPrefValue(value: String, key: String) {
        Log.e("Saving $key", value)
        val preferences = getAppPreferences()
        val editor = preferences.edit()
        if(value == null) {
            editor.putString(key, "")
        }
        else {
            editor.putString(key, value)
        }
        editor.apply()
    }

    private fun getPrefValue(key: String): String? {
        val preferences = getAppPreferences()
        return preferences.getString(key, "")
    }

    private fun setPrefValue(value: Boolean, key: String) {
        Log.e("Saving $key", value.toString())
        val preferences = getAppPreferences()
        val editor = preferences.edit()
        if(value == null) {
            editor.putBoolean(key, false)
        }
        else {
            editor.putBoolean(key, value)
        }
        editor.apply()
    }

    private fun getBooleanPrefValue(key: String): Boolean? {
        val preferences = getAppPreferences()
        return preferences.getBoolean(key, false)
    }

    fun getIsUserLoggedIn(): Boolean
    {
        return getBooleanPrefValue("IsUserLoggedIn")!!
    }

    fun setIsUserLoggedIn(isUserLoggedIn: Boolean)
    {
        setPrefValue(isUserLoggedIn, "IsUserLoggedIn")
    }
}