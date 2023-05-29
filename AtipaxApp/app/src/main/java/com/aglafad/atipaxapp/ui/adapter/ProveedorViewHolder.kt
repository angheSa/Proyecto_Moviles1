package com.aglafad.atipaxapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aglafad.atipaxapp.databinding.ItemProveedorBinding
import com.aglafad.atipaxapp.entity.Proveedor

class ProveedorViewHolder (private val binding: ItemProveedorBinding) : ViewHolder(binding.root){

    fun pro(proveedor: Proveedor) {

         binding.lblCodigoProveedor.text = proveedor.id_provee.toString()
         binding.lblNombreProveedor.text = proveedor.nombre.toString()
        binding.lblDireccionProveedor.text = proveedor.direc.toString()
        binding.lblCorreoProveedor.text = proveedor.correo.toString()
        binding.lblTelefonoProveedor.text = proveedor.telefono.toString()

     }


}