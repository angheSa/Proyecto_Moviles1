package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.room.Room
import com.aglafad.atipaxapp.databinding.ActivityMainBinding
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.UsuarioDao

class UsuarioAdapter(val lista : List<Usuario>, val onclick : (Usuario) -> Unit) :
    Adapter<UsuarioViewHolder>() {
    private lateinit var usuarioDao: UsuarioDao

    /*pViewModel.productos.observe(viewLifecycleOwner){ lista ->
        println("Informacion actual de la Lista ${lista.size}")
        for(p in lista){
            println(p.toString())
        }
    }*/
    var listaUsuarios : List<Usuario> = listOf(
        Usuario(10, "1021A"),
        Usuario(11, "132B" )

    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ActivityMainBinding.inflate(inflater, parent, false)

        return UsuarioViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return listaUsuarios.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val prod = listaUsuarios[position]
        holder.bind(prod)
        holder.itemView.setOnClickListener {
            onclick(prod)
        }
    }

}