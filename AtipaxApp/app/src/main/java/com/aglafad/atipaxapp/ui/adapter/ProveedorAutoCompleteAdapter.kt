package com.aglafad.atipaxapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.entity.Proveedor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

class ProveedorAutoCompleteAdapter(context: Context,  provee: List<Proveedor>):
    ArrayAdapter<Proveedor>(context,0,provee) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_cbo_proveedor, parent, false)
        getItem(position)?.let { proveedor ->
            view.findViewById<TextView>(R.id.textCbo).apply {
                text = proveedor.id_provee.toString()
            }
        }
        return view
    }

}