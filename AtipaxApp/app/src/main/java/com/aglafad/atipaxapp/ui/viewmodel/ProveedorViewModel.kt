package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.aglafad.atipaxapp.entity.Proveedor

import com.aglafad.atipaxapp.repository.ProveedorRepository
import kotlinx.coroutines.launch

class ProveedorViewModel (private val repository: ProveedorRepository) : ViewModel() {

    val proveedores : LiveData<List<Proveedor>> = repository.listaProveedor.asLiveData()

    fun insertar(proveedor: Proveedor){
        viewModelScope.launch {
            repository.guardar(proveedor)
        }
    }
    fun actualizar(proveedor: Proveedor){
        viewModelScope.launch {
            repository.actualizar(proveedor)
        }
    }
    fun eliminar(id: Int){
        viewModelScope.launch {
            repository.eliminar(id)
        }
    }

   /* fun buscarPorNombre(nombre: String){
        viewModelScope.launch {
            repository.buscarPorNombre(nombre)
        }
    }*/
}