package com.example.pictures.data.repositories

import com.example.pictures.data.datasource.local.PhotosLocalDatasource
import com.example.pictures.data.datasource.remote.PhotosRemoteDatasource
import com.example.pictures.data.models.PhotoModel
import com.example.pictures.data.models.toEntity
import com.example.pictures.data.models.toModel
import com.example.pictures.domain.repository.MainRepository
import com.example.pictures.tools.SharedPref.clearIdList
import com.example.pictures.tools.SharedPref.getIdListToDelete
import com.example.pictures.tools.SharedPref.saveIdToDelete

class MainRepositoryImpl : MainRepository {

    private val remoteDataSource = PhotosRemoteDatasource()
    private val localDataSource = PhotosLocalDatasource()

    override suspend fun getPhotos(): List<PhotoModel> {

        //todo: Realizo la validación para traer la fotos y guardarlas localmente
        val response = remoteDataSource.getPhotosApi()
        return if (response.isNotEmpty()) {
            localDataSource.deleteAllPhotos()
            val data = response.map { it.toEntity() }
            localDataSource.insertPhotos(data)
            response
        } else {
            val responseDB = localDataSource.getPhotos()
            val listPhotosDB =
                if (responseDB.isNotEmpty()) responseDB.map { it.toModel() } else emptyList()
            listPhotosDB
        }

    }

    override suspend fun deletePhoto(id: Int) {
        //todo: Se elimina la foto del servicio y localmente, sino se almacena el id de la foto a borrar
        val isDeleted = remoteDataSource.isDeletedPhoto(id)
        if (!isDeleted)
            saveIdToDelete(id.toString())
        localDataSource.deletePhotoById(id)
    }

    override suspend fun deletePhotosSaved() {
        //todo: Obtengo los id que no fueron eliminados en la consulta
        val idList = getIdListToDelete().map { it.toInt() }
        idList.filter { remoteDataSource.isDeletedPhoto(it) }.forEach {
            clearIdList(it.toString())
        }
    }

}