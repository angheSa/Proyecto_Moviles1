package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aglafad.atipaxapp.repository.TourRepository
import com.aglafad.atipaxapp.room.HotelDao
import com.aglafad.atipaxapp.room.TourDao

class TourViewModelFactory(val repositoryTour: TourRepository, val tourDao: TourDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourViewModel::class.java)){
            return TourViewModel(repositoryTour, tourDao) as T
        }
        return super.create(modelClass)
    }
}