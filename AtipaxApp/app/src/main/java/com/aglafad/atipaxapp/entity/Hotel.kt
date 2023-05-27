package com.aglafad.atipaxapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_hotel")
class Hotel  (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id" ) val id: Int = 0,
              @ColumnInfo(name = "nombre_hotel" ) val nombre: String?,
              @ColumnInfo(name = "categoria_hotel" ) val categoria: String?,
              @ColumnInfo(name = "precio_hotel" ) val precio: Double?): Serializable {
}