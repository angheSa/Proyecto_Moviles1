package com.aglafad.atipaxapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.aglafad.atipaxapp.repository.ProveedorRepository


class ProveedorViewModelFactory ( val repositoryProvee: ProveedorRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(ProveedorViewModel::class.java)){
            return ProveedorViewModel(repositoryProvee) as T
        }

        return super.create(modelClass)
    }
}