package com.example.pictures

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pictures.data.datasource.local.PhotoDatabase

class PicturesApplication : Application(){
    companion object{
        lateinit var db : PhotoDatabase
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        db = Room.databaseBuilder(applicationContext, PhotoDatabase::class.java, "photos").build()
    }
}