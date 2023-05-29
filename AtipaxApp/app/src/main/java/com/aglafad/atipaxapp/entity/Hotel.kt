package com.aglafad.atipaxapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import androidx.room.ForeignKey

@Entity(tableName = "tb_hotel",
    foreignKeys = [
        ForeignKey(entity = Proveedor::class, parentColumns = ["id_provee"], childColumns = ["idProvee"])
    ]

    )
class Hotel  (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_hotel" ) val id: Int = 0,
              @ColumnInfo(name = "destino_hotel" ) val destinoHotel: String?,
              @ColumnInfo(name = "descripcion_hotel" ) val descripcion: String?,
              @ColumnInfo(name = "precio_hotel" ) val precio: Double?,
              @ColumnInfo(name = "idProvee" ) val idProveedor: Int?): Serializable {
}