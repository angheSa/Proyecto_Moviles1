package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.repository.ProveedorRepository
import com.aglafad.atipaxapp.repository.TourRepository
import com.aglafad.atipaxapp.repository.UsuarioRepository

class TourViewModelFactory ( val repositoryTour: TourRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TourViewModel::class.java)){
            return TourViewModel(repositoryTour) as T
        }


        return super.create(modelClass)
        }
}