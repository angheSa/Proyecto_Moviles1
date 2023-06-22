package com.aglafad.atipaxapp.repository

import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.room.HotelDao
import kotlinx.coroutines.flow.Flow

class HotelRepository(private val hotelDao : HotelDao) {


    var listaHoteles : Flow<List<Hotel>> = hotelDao.getHoteles()

    suspend fun guardar(producto: Hotel) {
        hotelDao.insert(producto)
    }
    suspend fun actualizar(producto: Hotel) {
        hotelDao.update(producto)
    }
    suspend fun eliminar(id: Int) {
        hotelDao.delete(id)
    }
}