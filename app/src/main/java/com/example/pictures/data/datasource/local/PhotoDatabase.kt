package com.example.pictures.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pictures.data.datasource.local.dao.PhotoDao
import com.example.pictures.data.datasource.local.entities.PhotoEntity


@Database(entities = [PhotoEntity::class], version = 1)
abstract class PhotoDatabase : RoomDatabase(){

    abstract fun getPhotoDao():PhotoDao

}