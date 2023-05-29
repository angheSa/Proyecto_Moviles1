package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.TourRepository
import kotlinx.coroutines.launch

class TourViewModel (private val repository: TourRepository) : ViewModel() {

    val tours: LiveData<List<Tour>> = repository.listaTours.asLiveData()

    fun insertar(tour: Tour){
        viewModelScope.launch {
            repository.guardar(tour)
        }
    }
    fun actualizar(tour: Tour){
        viewModelScope.launch {
            repository.actualizar(tour)
        }
    }
    fun eliminar(id: Int){
        viewModelScope.launch {
            repository.eliminar(id)
        }
    }
}