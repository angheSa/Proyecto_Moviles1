package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aglafad.atipaxapp.databinding.ItemProveedorBinding
import com.aglafad.atipaxapp.entity.Proveedor


class ProveedorAdapter(val onclick : (Proveedor) -> Unit) : Adapter<ProveedorViewHolder>() {

    private var listas = mutableListOf<Proveedor>()

    fun listasProveedores (lista: List<Proveedor>){
        listas.addAll(lista)
        notifyDataSetChanged()
    }
    /*fun setOnClickDelete(callback: (Proveedor) -> Unit){
        this.onclickDelete = callback
        notifyDataSetChanged()

        var onclickDelete : (Proveedor) -> Unit,
    }

    fun eliminarProveedor(proveedor: Proveedor) {
        listas.remove(proveedor)
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ProveedorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProveedorBinding.inflate(inflater, parent, false)
        return ProveedorViewHolder(binding)
    }

    override fun getItemCount(): Int { // espera retornar un valor entero
        return listas.size
    }

    override fun onBindViewHolder(holder: ProveedorViewHolder, position: Int) {
        val provee = listas[position]
        holder.pro(provee)
        holder.itemView.setOnClickListener {
            onclick(provee)
        }
      /*  holder.btnDelete.setOnClickListener {
            onclickDelete(provee)
        }*/

    }
}