package br.com.motivation.infra

import android.content.Context

class SecurityPreferences(context: Context) {
    private val security = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String){
        security.edit().putString(key, value).apply()
    }

    fun getString(key:String): String{
        return security.getString(key, "") ?: ""
    }
}