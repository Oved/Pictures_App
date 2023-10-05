package com.example.pictures.data.repositories

import com.example.pictures.data.datasource.remote.PhotosRemoteDatasource
import com.example.pictures.data.models.PhotoModel
import com.example.pictures.domain.repository.MainRepository

class MainRepositoryImpl : MainRepository {

    private val dataSource = PhotosRemoteDatasource()

    override suspend fun getPhotos(): List<PhotoModel> {
        //TODO validar
        return dataSource.getPhotos()
    }

    override suspend fun getPhotosDB(): List<PhotoModel> {
        return dataSource.getPhotos()
    }

    override suspend fun deletePhoto(id: Int) {

    }

}