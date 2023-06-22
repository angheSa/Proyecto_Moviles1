package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.TourRepository
import com.aglafad.atipaxapp.room.TourDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TourViewModel(private val repository: TourRepository, private val tourDao: TourDao) : ViewModel() {

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
    suspend fun buscarToursXFiltro(destino_tour : String, tipo_tour : String, descripcion_tour : String): List<Tour> {
        return withContext(Dispatchers.IO) {
            tourDao.getToursXFiltro(destino_tour, tipo_tour, descripcion_tour)
        }
    }
    /*fun buscarToursXFiltro(destino_tour : String, tipo_tour : String, descripcion_tour : String): List<Tour> {
        return tourDao.getToursXFiltro(destino_tour, tipo_tour, descripcion_tour)
    }*/
}