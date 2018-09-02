package jp.co.penguin.gourmetsearch.data.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class PrefsManager(context: Context?) {
    val mContext = context

    enum class PrefsKey(val key: String) {
        KEYWORD("jp.co.penguin.gourmetsearch.data.prefs.KEYWORD"),
        AREA("jp.co.penguin.gourmetsearch.data.AREA"),
        COURSE("jp.co.penguin.gourmetsearch.data.COURSE")
    }

    fun getKeyword(): String {
        return loadString(PrefsKey.KEYWORD.key)
    }

    fun setKeyword(keyword: String) {
        saveString(PrefsKey.KEYWORD.key, keyword)
    }

    fun getArea(): Int {
        return loadInt(PrefsKey.AREA.key)
    }

    fun setArea(area: Int) {
        saveInt(PrefsKey.AREA.key, area)
    }

    fun getCourse(): Boolean {
        return loadBoolean(PrefsKey.COURSE.key)
    }

    fun setCourse(isEnable: Boolean) {
        saveBoolean(PrefsKey.COURSE.key, isEnable)
    }


    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun saveInt(key: String, value: Int) {
        if (mContext == null) { return }
        val editor = getDefaultSharedPreferences(mContext).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun saveString(key: String, value: String) {
        if (mContext == null) { return }
        val editor = getDefaultSharedPreferences(mContext).edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun saveBoolean(key: String, value: Boolean) {
        if (mContext == null) { return }
        val editor = getDefaultSharedPreferences(mContext).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun loadInt(key: String): Int {
        if (mContext == null) { return 0 }
        return getDefaultSharedPreferences(mContext).getInt(key, -1)
    }

    private fun loadString(key: String): String {
        if (mContext == null) { return "" }
        return getDefaultSharedPreferences(mContext).getString(key, "")
    }

    private fun loadBoolean(key: String): Boolean {
        if (mContext == null) { return false }
        return getDefaultSharedPreferences(mContext).getBoolean(key, false)
    }

    private fun remove(key: String) {
        if (mContext == null) { return }
        val editor = getDefaultSharedPreferences(mContext).edit()
        editor.remove(key)
        editor.apply()
    }

}