package com.aglafad.atipaxapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.entity.Tour

@Database(entities = [Usuario::class, Proveedor::class,Tour::class, Hotel::class], version = 1 )
abstract class AlmacenDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun hotelDao(): HotelDao

    abstract fun tourDao(): TourDao

    abstract fun proveedorDao(): ProveedorDao
    companion object{
        private var INSTANCE: AlmacenDatabase? = null

        fun getInstance(context: Context): AlmacenDatabase{
            return INSTANCE?: synchronized(this){
                val obj = Room.databaseBuilder(context, AlmacenDatabase::class.java, "BDAtipaxGroup")

                    .allowMainThreadQueries()
                    .build()

                INSTANCE = obj
                obj
            }
        }


    }
}