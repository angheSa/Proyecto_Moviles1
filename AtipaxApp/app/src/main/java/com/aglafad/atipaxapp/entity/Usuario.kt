package com.aglafad.atipaxapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_usuario")
class Usuario  (@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id_usuario")val id_usuario: Int = 0,
                @ColumnInfo(name = "psw_usuario") val psw_usuario: String): Serializable {
    override fun toString(): String {
        return "Usuario(id=$id_usuario, psw_usuario='$psw_usuario')"
    }
}