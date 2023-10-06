package com.example.pictures.data.datasource.local.dao

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pictures.data.datasource.local.entities.PhotoEntity

@Dao
interface PhotoDao {

    //todo: Se crean las consultas que se requieren en este caso

    @Query("SELECT * FROM photo_table")
    suspend fun getPhotos() : List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos : List<PhotoEntity>)

    @Query("DELETE FROM photo_table WHERE id = :id")
    suspend fun deletePhotoById(id : Int)

    @Query("DELETE FROM photo_table")
    suspend fun deleteAllPhotos()
}