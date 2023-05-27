package com.aglafad.atipaxapp.repository

import androidx.room.Query
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.room.UsuarioDao
import kotlinx.coroutines.flow.Flow

class UsuarioRepository (val usuarioDao : UsuarioDao){

    val listaUsuarios : Flow<List<Usuario>> = usuarioDao.getUsuarios()

    suspend fun insertar(usuario: Usuario){
        usuarioDao.insert(usuario)
    }

    suspend fun actualizar(usuario: Usuario){
        usuarioDao.update(usuario)
    }

    suspend fun eliminar(usuario: Usuario){
        usuarioDao.delete(usuario)
    }
}