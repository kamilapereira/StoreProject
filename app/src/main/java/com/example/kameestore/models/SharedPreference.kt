package com.example.kameestore.models

import android.content.Context
import android.preference.PreferenceManager

class SharedPreference {

    //object SaveSharedPreference {
    //    private const val PREF_USER_NAME = "username"
    //
    //    private fun getSharedPreferences(ctx: Context): SharedPreferences {
    //        return PreferenceManager.getDefaultSharedPreferences(ctx)
    //    }
    //
    //    fun setUserName(ctx: Context, userName: String) {
    //        val editor = getSharedPreferences(ctx).edit()
    //        editor.putString(PREF_USER_NAME, userName)
    //        editor.apply()
    //    }
    //
    //    fun getUserName(ctx: Context): String {
    //        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "") ?: ""
    //    }
    //}

    object SaveSharedPreference {
        private const val PREF_USER_NAME = "username"

        private fun getSharedPreference(ctx: Context) : SharedPreference {
            return PreferenceManager.getDefaultSharedPreferences(ctx)
        }
    }
}