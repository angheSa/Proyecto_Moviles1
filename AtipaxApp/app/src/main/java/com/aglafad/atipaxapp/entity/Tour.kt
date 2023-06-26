package com.aglafad.atipaxapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "tb_tour",
    foreignKeys = [
        ForeignKey(entity = Proveedor::class, parentColumns = ["nom_proveedor"], childColumns = ["idProvee"])
    ]

)
class Tour (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_tour" ) val id: Int = 0,
            @ColumnInfo(name = "destino_tour" ) val destino: String?,
            @ColumnInfo(name = "tipo_tour" ) val tipo: String?,
            @ColumnInfo(name = "descripcion_tour" ) val descripcion: String?,
            @ColumnInfo(name = "precio_tour" ) val precio: Double?,
            @ColumnInfo(name = "idProvee" ) val idProveedor: String?): Serializable {
}