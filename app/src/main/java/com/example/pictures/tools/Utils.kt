package com.example.pictures.tools

import android.util.Log
import com.example.pictures.data.models.PhotoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class Utils {
    companion object{
        fun deserializeJsonPhotos(jsonString: String): List<PhotoModel> {
            try{
                Log.e("ERROR2", "getPhotos: ${jsonString}",)
                val gson = Gson()
                val listType = object : TypeToken<List<PhotoModel>>() {}.type

                Log.e("ERROR2", "getPhotos: ${listType}",)
                return gson.fromJson(jsonString, listType)
            }catch (e:Exception){
                Log.e("ERROR4", "getPhotos: ${e.message}", )
                return emptyList()
            }
        }

        fun mapJsonToPhotoList(json: String): List<PhotoModel> {
            val jsonArray = JSONArray(json)
            val photoList = mutableListOf<PhotoModel>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val photoModel = PhotoModel(
                    albumId = jsonObject.getInt("albumId"),
                    id = jsonObject.getInt("id"),
                    title = jsonObject.getString("title"),
                    url = jsonObject.getString("url"),
                    thumbnailUrl = jsonObject.getString("thumbnailUrl")
                )
                photoList.add(photoModel)
            }

            return photoList
        }
    }
}