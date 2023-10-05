package com.example.pictures.data.datasource.remote

import com.example.pictures.data.models.PhotoModel
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("photos")
    suspend fun getPhotos() : Response<List<PhotoModel>>

    @DELETE("photos/{id}")
    suspend fun deletePhoto(@Path("id") id: String) : Response<String>
}