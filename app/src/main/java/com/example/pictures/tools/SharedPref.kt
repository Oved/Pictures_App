package com.example.pictures.tools

import android.content.Context
import com.example.pictures.PicturesApplication.Companion.appContext

object SharedPref {

    private val PREF_NAME = "shared"
    private val KEY_ID_LIST = "key_id_list"

    //todo: Guardo en una sharedpreference el id que no se envió en la petición
    fun saveIdToDelete(id: String) {
        val sharedPreferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val idList = getIdListToDelete()
        idList.add(id)
        editor.putStringSet(KEY_ID_LIST, idList.toSet())
        editor.apply()
    }

    fun getIdListToDelete(): MutableList<String> {
        val sharedPreferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val idSet = sharedPreferences.getStringSet(KEY_ID_LIST, setOf()) ?: setOf()
        return idSet.toMutableList()
    }

    fun clearIdList(id : String) {
        val sharedPreferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val idList = getIdListToDelete()

        idList.remove(id)

        editor.putStringSet(KEY_ID_LIST, idList.toSet())
        editor.apply()
    }
}