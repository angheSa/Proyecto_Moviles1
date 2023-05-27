package com.aglafad.atipaxapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.entity.Hotel

class HotelViewHolder (private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(hotel: Hotel) {
      //  binding.t.text = hotel.nombre

    }
}