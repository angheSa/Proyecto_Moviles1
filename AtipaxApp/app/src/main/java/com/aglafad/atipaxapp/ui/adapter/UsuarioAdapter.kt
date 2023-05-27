package com.aglafad.atipaxapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aglafad.atipaxapp.databinding.ActivityMainBinding
import com.aglafad.atipaxapp.entity.Usuario

class UsuarioAdapter(val lista : List<Usuario>, val onclick : (Usuario) -> Unit) :
    Adapter<UsuarioViewHolder>() {


    var listaUsuarios : List<Usuario> = listOf(
        Usuario(10, "1021A"),
        Usuario(11, "132B" )

    )
    /*pViewModel.productos.observe(viewLifecycleOwner){ lista ->
        println("Informacion actual de la Lista ${lista.size}")
        for(p in lista){
            println(p.toString())
        }
    }*/
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