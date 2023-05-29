package com.aglafad.atipaxapp.repository

import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.room.HotelDao
import com.aglafad.atipaxapp.room.TourDao
import kotlinx.coroutines.flow.Flow

class TourRepository(private val tourDao: TourDao) {


    val listaTours : Flow<List<Tour>> = tourDao.getTours()

    suspend fun guardar(producto: Tour) {
        tourDao.insert(producto)
    }
    suspend fun actualizar(producto: Tour) {
        tourDao.update(producto)
    }
    suspend fun eliminar(id: Int) {
        tourDao.delete(id)
    }
}