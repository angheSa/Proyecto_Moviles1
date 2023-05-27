package com.aglafad.atipaxapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aglafad.atipaxapp.entity.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AlmacenDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    companion object{
        private var INSTANCE: AlmacenDatabase? = null

        fun getInstance(context: Context): AlmacenDatabase{
            return INSTANCE?: synchronized(this){
                val obj = Room.databaseBuilder(context, AlmacenDatabase::class.java, "BDAtipaxGroup").allowMainThreadQueries().build()
                INSTANCE = obj
                obj
            }
        }
    }
}