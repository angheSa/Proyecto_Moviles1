package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.entity.Hotel

class HotelAdapter(val lista : List<Hotel>, val onclick : (Hotel) -> Unit) : RecyclerView.Adapter<HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotelBinding.inflate(inflater, parent, false)
       return HotelViewHolder(binding)
    }

    override fun getItemCount(): Int { // espera retornar un valor entero
        return lista.size
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hote = lista[position]
        holder.bind(hote)
        //   holder.itemView.setOnClickListener {
        onclick(hote)
    }

}