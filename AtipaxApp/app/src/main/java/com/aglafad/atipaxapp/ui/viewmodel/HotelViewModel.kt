package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.repository.HotelRepository
import kotlinx.coroutines.launch

class HotelViewModel(private val repository: HotelRepository) : ViewModel() {

    val hoteles: LiveData<List<Hotel>> = repository.listaHoteles.asLiveData()

    fun insertar(hotel: Hotel){
        viewModelScope.launch {
            repository.guardar(hotel)
        }
    }
    fun actualizar(hotel: Hotel){
        viewModelScope.launch {
            repository.actualizar(hotel)
        }
    }
    fun eliminar(id: Int){
        viewModelScope.launch {
            repository.eliminar(id)
        }
    }
}