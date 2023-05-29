package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.ProveedorRepository
import com.aglafad.atipaxapp.repository.TourRepository
import com.aglafad.atipaxapp.repository.UsuarioRepository

class HotelViewModelFactory (val repositoryHote: HotelRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {



        if (modelClass.isAssignableFrom(HotelViewModel::class.java)){
            return HotelViewModel(repositoryHote) as T
        }


        return super.create(modelClass)
    }
}