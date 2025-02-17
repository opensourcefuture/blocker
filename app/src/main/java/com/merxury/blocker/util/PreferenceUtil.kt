package com.merxury.blocker.util

import android.content.Context
import android.net.Uri
import androidx.preference.PreferenceManager
import com.merxury.blocker.R
import com.merxury.blocker.core.root.EControllerMethod
import com.merxury.blocker.data.source.OnlineSourceType
import com.merxury.blocker.ui.home.applist.SortType

object PreferenceUtil {
    fun getControllerType(context: Context): EControllerMethod {
        // Magic value, but still use it.
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return when (pref.getString(
            context.getString(R.string.key_pref_controller_type),
            context.getString(R.string.key_pref_controller_type_default_value)
        )) {
            "pm" -> EControllerMethod.PM
            "shizuku" -> EControllerMethod.SHIZUKU
            else -> EControllerMethod.IFW
        }
    }

    fun getSavedRulePath(context: Context): Uri? {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val storedPath = pref.getString(context.getString(R.string.key_pref_rule_path), null)
        if (storedPath.isNullOrEmpty()) return null
        return Uri.parse(storedPath)
    }

    fun getIfwRulePath(context: Context): Uri? {
        return getSavedRulePath(context)
            ?.buildUpon()
            ?.appendPath("ifw")
            ?.build()
    }

    fun setRulePath(context: Context, uri: Uri?) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(context.getString(R.string.key_pref_rule_path), uri?.toString())
            .apply()

    }

    fun shouldBackupSystemApps(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(context.getString(R.string.key_pref_backup_system_apps), false)
    }

    fun setShowSystemApps(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(context.getString(R.string.key_pref_show_system_apps), value)
            .apply()
    }

    fun getShowSystemApps(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(context.getString(R.string.key_pref_show_system_apps), false)
    }

    fun setShowServiceInfo(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(context.getString(R.string.key_pref_show_running_service_info), value)
            .apply()
    }

    fun getShowServiceInfo(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(
            context.getString(R.string.key_pref_show_running_service_info),
            false
        )
    }

    fun setSortType(context: Context, value: SortType?) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(context.getString(R.string.key_pref_sort_type), value?.name)
            .apply()
    }

    fun getSortType(context: Context): SortType {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val value = pref.getString(context.getString(R.string.key_pref_sort_type), null).orEmpty()
        return try {
            SortType.valueOf(value)
        } catch (e: Exception) {
            SortType.NAME_ASC
        }
    }

    fun setSearchSystemApps(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(context.getString(R.string.key_pref_search_system_apps), value)
            .apply()
    }

    fun getSearchSystemApps(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(context.getString(R.string.key_pref_search_system_apps), false)
    }

    fun setUseRegexSearch(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(context.getString(R.string.key_pref_use_regex_search), value)
            .apply()
    }

    fun getUseRegexSearch(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(context.getString(R.string.key_pref_use_regex_search), false)
    }

    fun setOnlineSourceType(context: Context, type: OnlineSourceType) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(context.getString(R.string.key_pref_online_source_type), type.name)
            .apply()
    }

    fun getOnlineSourceType(context: Context): OnlineSourceType {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val value =
            pref.getString(context.getString(R.string.key_pref_online_source_type), null).orEmpty()
        return try {
            OnlineSourceType.valueOf(value)
        } catch (e: Exception) {
            OnlineSourceType.GITEE
        }
    }

    fun setShowEnabledComponentShowFirst(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(
                context.getString(R.string.key_pref_show_enabled_component_show_first),
                value
            )
            .apply()
    }

    fun getShowEnabledComponentShowFirst(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(
            context.getString(R.string.key_pref_show_enabled_component_show_first),
            false
        )
    }
}