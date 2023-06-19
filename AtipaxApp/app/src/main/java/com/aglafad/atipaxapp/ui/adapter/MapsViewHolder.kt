package com.aglafad.atipaxapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.entity.Hotel

class MapsViewHolder(private val binding: ItemHotelBinding): ViewHolder(binding.root) {

    fun bind(hotel: Hotel) {

        binding.lblNombreHotel.text = hotel.nombreHotel.toString()

    }
}