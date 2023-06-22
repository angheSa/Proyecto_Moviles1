package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.*
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.room.HotelDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HotelViewModel(private val repository: HotelRepository, private var hotelDao: HotelDao) : ViewModel() {

    var hotels: LiveData<List<Hotel>> = repository.listaHoteles.asLiveData()

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
    suspend fun buscarHotelesXFiltro(nombre: String, descripcion: String, destino: String): List<Hotel> {
        return withContext(Dispatchers.IO) {
            hotelDao.getHotelesXFiltro(nombre, descripcion, destino)
        }
    }
    /*fun buscarHotelesXFiltro(nombre : String, descripcion : String, destino : String): List<Hotel> {
        println("EN HOTELVIEWMODEL: " + nombre + ";" + descripcion + ";" + destino)
        return hotelDao.getHotelesXFiltro(nombre, descripcion, destino)
    }*/


}
