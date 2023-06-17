package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aglafad.atipaxapp.databinding.ItemTourBinding
import com.aglafad.atipaxapp.entity.Tour

class TourAdapter(val onclick : (Tour) -> Unit) : RecyclerView.Adapter<TourViewHolder>(){
   private var listas = mutableListOf<Tour>()

    fun listasTours(lista: List<Tour>){
        listas.addAll(lista)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TourViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTourBinding.inflate(inflater, parent, false)
        return TourViewHolder(binding)
    }

    override fun getItemCount(): Int { // espera retornar un valor entero
        return listas.size
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val t = listas[position]
        holder.bind(t)
        holder.itemView.setOnClickListener {
            onclick(t)
        }
    }

}