package uk.ac.tees.mad.stayfinder

import android.content.Context
import androidx.core.content.edit

class PreferenceManager(context: Context) {

    private val sharedPref =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPref.edit { putBoolean(KEY_IS_LOGGED_IN, isLoggedIn) }
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

}