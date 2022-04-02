package com.example.api.core

import android.content.SharedPreferences
import com.example.api.helper.SingletonHolder

class SessionManager private constructor(internal val sharedPreferences: SharedPreferences) {
    companion object : SingletonHolder<SharedPreferences, SessionManager>(::SessionManager)
}
