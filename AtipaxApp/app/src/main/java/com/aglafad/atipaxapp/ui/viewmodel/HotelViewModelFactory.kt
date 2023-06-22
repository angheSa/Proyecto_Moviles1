package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aglafad.atipaxapp.repository.HotelRepository
import com.aglafad.atipaxapp.room.HotelDao

class HotelViewModelFactory (private val repositoryHote: HotelRepository, private val hotelDao: HotelDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelViewModel::class.java)){
            return HotelViewModel(repositoryHote, hotelDao) as T
        }
        return super.create(modelClass)
    }
}