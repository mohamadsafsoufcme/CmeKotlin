package com.cme.cmekotlin.auth

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserSessionManager(private val context: Context) {
    companion object {
        private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val KEY_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun saveSession(email: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED_IN] = true
            prefs[KEY_EMAIL] = email
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun isLoggedIn(): Boolean =
        context.dataStore.data.map { it[KEY_IS_LOGGED_IN] ?: false }.first()

    suspend fun getEmail(): String? =
        context.dataStore.data.map { it[KEY_EMAIL] }.first()
}
