package com.example.pictures.data.models

import android.os.Parcelable
import com.example.pictures.data.datasource.local.entities.PhotoEntity
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PhotoModel(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable

fun PhotoEntity.toModel() = PhotoModel(albumId,id,title,url,thumbnailUrl)
fun PhotoModel.toEntity() = PhotoEntity(id,albumId,title,url,thumbnailUrl)

