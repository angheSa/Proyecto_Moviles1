package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.ProveedorRepository
import com.aglafad.atipaxapp.repository.TourRepository
import com.aglafad.atipaxapp.repository.UsuarioRepository

class ViewModelFactory (private val repository: UsuarioRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)){
            return UsuarioViewModel(repository) as T
        }



        return super.create(modelClass)
    }
}