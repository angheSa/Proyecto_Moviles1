package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel (private val repository: UsuarioRepository) : ViewModel(){

    val productos: LiveData<List<Usuario>> = repository.listaUsuarios.asLiveData()

    fun insertar(usuario: Usuario){
        viewModelScope.launch {
            repository.insertar(usuario)
        }
    }
    fun actualizar(usuario: Usuario){
        viewModelScope.launch {
            repository.actualizar(usuario)
        }
    }
    fun eliminar(usuario: Usuario){
        viewModelScope.launch {
            repository.eliminar(usuario)
        }
    }
}