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

    fun getPhotos(context: Context) {

        jobs?.cancel()
        jobs = viewModelScope.launch {

            _uiStates.value = UiStates.Progress

            if (isNetworkAvailable(context)) {
                val photosResponse = repository.getPhotos()
                //TODO: guardar la fotos de manera local

                _uiStates.value = UiStates.Success(photosResponse)

            } else {
                val photosDB = repository.getPhotosDB()

                if (photosDB.isNotEmpty())
                    _uiStates.value = UiStates.Success(photosDB)

            }
        }

    }

}