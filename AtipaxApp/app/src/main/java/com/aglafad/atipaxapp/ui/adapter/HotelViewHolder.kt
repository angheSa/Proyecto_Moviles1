package com.aglafad.atipaxapp.ui.adapter


import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.entity.Hotel
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class HotelViewHolder (private val binding: ItemHotelBinding) : ViewHolder(binding.root){

   fun bind(hotel: Hotel) {

      //  binding.lblCodigoHotel.text = hotel.id.toString()
        binding.lblDestinoHotel.text = hotel.destinoHotel.toString()
       binding.lblDescripcionHotel.text = hotel.descripcion.toString()
        binding.lblPrecioHotel.text = hotel.precio.toString()
       binding.lblProveedorHotel.text = hotel.idProveedor.toString()

    }
}