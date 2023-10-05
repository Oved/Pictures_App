package com.example.pictures.presentation.viewmodel

import com.example.pictures.data.models.PhotoModel

sealed class UiStates {

    object Progress : UiStates()
    data class Success (val listPhotos : List<PhotoModel>): UiStates()
    data class Failure (val message : String): UiStates()
}