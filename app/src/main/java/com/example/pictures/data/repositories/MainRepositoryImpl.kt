package com.example.pictures.data.repositories

import com.example.pictures.PicturesApplication.Companion.db
import com.example.pictures.data.datasource.remote.PhotosRemoteDatasource
import com.example.pictures.data.models.PhotoModel
import com.example.pictures.data.models.toEntity
import com.example.pictures.data.models.toModel
import com.example.pictures.domain.repository.MainRepository
import com.example.pictures.tools.SharedPref.clearIdList
import com.example.pictures.tools.SharedPref.getIdListToDelete
import com.example.pictures.tools.SharedPref.saveIdToDelete

class MainRepositoryImpl : MainRepository {

    private val dataSource = PhotosRemoteDatasource()

    override suspend fun getPhotos(): List<PhotoModel> {

        val response = dataSource.getPhotosApi()
        return if (response.isNotEmpty()) {
            db.getPhotoDao().deleteAllPhotos()
            val data = response.map { it.toEntity() }
            db.getPhotoDao().insertPhotos(data)
            response
        } else {
            val responseDB = db.getPhotoDao().getPhotos()
            val listPhotosDB =
                if (responseDB.isNotEmpty()) responseDB.map { it.toModel() } else emptyList()
            listPhotosDB
        }

    }

    override suspend fun getPhotosDB(): List<PhotoModel> {
        val response = db.getPhotoDao().getPhotos()
        return response.map { it.toModel() }
    }

    override suspend fun deletePhoto(id: Int): List<PhotoModel> {
        val isDeleted = dataSource.isDeletedPhoto(id)
        if (!isDeleted)
            saveIdToDelete(id.toString())
        db.getPhotoDao().deletePhotoById(id)
        return db.getPhotoDao().getPhotos().map { it.toModel() }
    }

    override suspend fun deletePhotosSaved() {
        val idList = getIdListToDelete().map { it.toInt() }
        idList.filter { dataSource.isDeletedPhoto(it) }.forEach {
            clearIdList(it.toString())
        }
    }

}