package com.aglafad.atipaxapp

import android.app.Application
import com.aglafad.atipaxapp.repository.UsuarioRepository
import com.aglafad.atipaxapp.room.AlmacenDatabase
import androidx.room.Room
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.ProveedorRepository
import com.aglafad.atipaxapp.repository.TourRepository

class AtipaxApplication : Application(){
    val database by lazy { AlmacenDatabase.getInstance(this) }
    val repository by lazy { UsuarioRepository(database.usuarioDao())}
    val repositoryHotel by lazy { HotelRepository(database.hotelDao())}
    val repositoryProvedor by lazy { ProveedorRepository(database.proveedorDao()) }
    val repositoryTour by lazy { TourRepository(database.tourDao()) }


    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AlmacenDatabase::class.java,
            "BDAtipaxGroup" // Nombre de la base de datos
        ).build()
    }
}