package com.example.pictures.data.datasource.remote

import android.util.Log
import com.example.pictures.data.models.PhotoModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotosRemoteDatasource {

    private val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = retrofit.create(ApiService::class.java)


    suspend fun getPhotosApi(): List<PhotoModel> {

        return try {
            val response = apiService.getPhotos()
            if (response.isSuccessful && !response.body().isNullOrEmpty())
                response.body()!!
            else
                error("The response is empty")

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun isDeletedPhoto(id: Int): Boolean {
        return try {
            val response = apiService.deletePhoto(id.toString())
            if (response.isSuccessful && response.code() == 200)
                return true
            else
                error("Error en la petición")

        } catch (e: Exception) {
            false
        }
    }
}