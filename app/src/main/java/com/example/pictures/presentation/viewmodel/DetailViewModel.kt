package com.example.pictures.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictures.data.repositories.MainRepositoryImpl
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = MainRepositoryImpl()

    fun deletePhoto(id:Int) {
        viewModelScope.launch {
            repository.deletePhoto(id)
        }
    }
}