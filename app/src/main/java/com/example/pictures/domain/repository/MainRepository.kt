package com.example.pictures.domain.repository

import com.example.pictures.data.models.PhotoModel

interface MainRepository {

    suspend fun getPhotos(): List<PhotoModel>

    suspend fun getPhotosDB(): List<PhotoModel>

    suspend fun deletePhoto(id: Int) : List<PhotoModel>
    suspend fun deletePhotosSaved()
}