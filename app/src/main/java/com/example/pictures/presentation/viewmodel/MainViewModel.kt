package com.example.pictures.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictures.data.repositories.MainRepositoryImpl
import com.example.pictures.tools.InternetConnection.isNetworkAvailable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepositoryImpl()

    private val _uiStates = MutableLiveData<UiStates>()

    val uiStates: LiveData<UiStates> get() = _uiStates

    var jobs: Job? = null

    fun getPhotos() {

        jobs?.cancel()
        jobs = viewModelScope.launch {

            _uiStates.value = UiStates.Progress

            val photosResponse = repository.getPhotos()
            if (photosResponse.isNotEmpty()) {
                _uiStates.value = UiStates.Success(photosResponse)
            } else {
                _uiStates.value = UiStates.Failure("Error al traer la información")
            }

        }
    }

    fun deletePhoto(id:Int) {
        viewModelScope.launch {
            //todo: Elimino la foto del servicio y de manera local
            repository.deletePhoto(id)
        }
    }

    fun deletePhotosSaved(){
        //todo: Se realiza la petición de la fotos que no se realizó el delete
        viewModelScope.launch {
            repository.deletePhotosSaved()
        }
    }

}