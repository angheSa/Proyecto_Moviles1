package com.aglafad.atipaxapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aglafad.atipaxapp.databinding.ActivityMainBinding
import com.aglafad.atipaxapp.entity.Usuario

class UsuarioViewHolder (private val binding: ActivityMainBinding) : ViewHolder(binding.root){
    fun bind(usuario: Usuario){
        /*binding.txtUsuario.setText("ID: "+ usuario.id_usuario)
        binding.txtPsw.setText("CÓDIGO:  " + usuario.psw_usuario)

         */
    }
}