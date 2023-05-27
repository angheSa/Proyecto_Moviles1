package com.aglafad.atipaxapp

import android.app.Application
import com.aglafad.atipaxapp.repository.UsuarioRepository
import com.aglafad.atipaxapp.room.AlmacenDatabase
import androidx.room.Room
import androidx.room.RoomDatabase

class Application : Application(){
    val database by lazy { AlmacenDatabase.getInstance(this) }
    val repository by lazy { UsuarioRepository(database.usuarioDao())}
    val db = Room.databaseBuilder(applicationContext, AlmacenDatabase::class.java, "")
}