package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aglafad.atipaxapp.databinding.ItemHotelBinding
import com.aglafad.atipaxapp.entity.Hotel

class HotelAdapter(val onclick : (Hotel) -> Unit) : Adapter<HotelViewHolder>() {

    private var listas = mutableListOf<Hotel>()

    fun listasHoteles (lista: List<Hotel>){
        listas.addAll(lista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotelBinding.inflate(inflater, parent, false)
        return HotelViewHolder(binding)
    }

    override fun getItemCount(): Int { // espera retornar un valor entero
        return listas.size
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hot = listas[position]
        holder.bind(hot)
        holder.itemView.setOnClickListener {
            onclick(hot)
        }
        /*  holder.btnDelete.setOnClickListener {
              onclickDelete(provee)
          }*/

    }
}