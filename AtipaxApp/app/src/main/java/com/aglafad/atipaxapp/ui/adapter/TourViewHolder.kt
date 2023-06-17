package com.aglafad.atipaxapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.databinding.ItemTourBinding
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Tour

class TourViewHolder (private val binding: ItemTourBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(tour: Tour) {
        binding.lblDestinoTour.text = tour.destino.toString()
        binding.lblTipoTour.text = tour.tipo.toString()
        binding.lblDescripcionTour.text = tour.descripcion.toString()
        binding.lblPrecioTour.text = tour.precio.toString()
        binding.lblProveedorTour.text = tour.idProveedor.toString()


    }
}