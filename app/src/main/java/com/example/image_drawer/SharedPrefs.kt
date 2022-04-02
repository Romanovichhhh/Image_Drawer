package com.example.image_drawer

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs {

    class SharedPrefs() {

        var sharedPref : SharedPreferences? = null;


        fun getPrefs(context: Context): SharedPreferences {
            return if (sharedPref == null) {
                sharedPref = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                sharedPref
            } else {
                sharedPref
            }!!
        }

        fun saveSession(context: Context) {
            getPrefs(context).edit().putBoolean("BOOLEAN_KEY", true).apply()
        }

        fun isSession(context: Context) : Boolean{
            return getPrefs(context).getBoolean("BOOLEAN_KEY", false)
        }


    }
}