package com.aglafad.atipaxapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "tb_proveedor")
class Proveedor  (@PrimaryKey @ColumnInfo(name = "nom_proveedor" ) val nombre: String,
                  @ColumnInfo(name = "direc_proveedor" ) val direc: String?,
                  @ColumnInfo(name = "correo_proveedor" ) val correo: String?,
                  @ColumnInfo(name = "telefono_proveedor" ) val telefono: Int?): Serializable {
}