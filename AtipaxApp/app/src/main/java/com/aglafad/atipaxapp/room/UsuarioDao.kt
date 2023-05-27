package com.aglafad.atipaxapp.room
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete

import androidx.room.Update
import com.aglafad.atipaxapp.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM tb_usuario")
    fun getUsuarios(): Flow<List<Usuario>>
    @Query("SELECT * FROM tb_usuario WHERE id_usuario = :usuario AND psw_usuario = :contraseña")
    suspend fun getUsuarioLogin(usuario: String, contraseña: String): Usuario?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)
}