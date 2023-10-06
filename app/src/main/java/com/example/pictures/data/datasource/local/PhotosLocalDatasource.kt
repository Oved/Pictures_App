package com.example.pictures.data.datasource.local

import com.example.pictures.PicturesApplication
import com.example.pictures.data.datasource.local.entities.PhotoEntity

class PhotosLocalDatasource {

    suspend fun getPhotos(): List<PhotoEntity> {
        val data = PicturesApplication.db.getPhotoDao().getPhotos()
        return data.ifEmpty { emptyList() }
    }

    suspend fun insertPhotos(data: List<PhotoEntity>) {
        PicturesApplication.db.getPhotoDao().insertPhotos(data)
    }

    suspend fun deleteAllPhotos() {
        PicturesApplication.db.getPhotoDao().deleteAllPhotos()
    }

    suspend fun deletePhotoById(id: Int) {
        PicturesApplication.db.getPhotoDao().deletePhotoById(id)
    }
}