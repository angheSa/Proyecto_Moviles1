package com.aglafad.atipaxapp.ui.views.proveedor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.aglafad.atipaxapp.databinding.FragmentManteProveedorBinding
import com.aglafad.atipaxapp.ui.viewmodel.ProveedorViewModel

import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.ui.adapter.ProveedorAdapter
import com.aglafad.atipaxapp.ui.viewmodel.ProveedorViewModelFactory
import com.aglafad.atipaxapp.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ManteProveedorFragment : Fragment() {


    private var _binding : FragmentManteProveedorBinding? = null
    val binding get() = _binding !!
    private lateinit var proveedorAdapt: ProveedorAdapter
    private val proViewModel: ProveedorViewModel by viewModels {
        val repositoryProvee = requireContext().applicationContext as AtipaxApplication
        ProveedorViewModelFactory(repositoryProvee.repositoryProvedor)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManteProveedorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         proveedorAdapt = ProveedorAdapter(
           /* onclickDelete = { proveedorEliminado ->
                proveedorAdapt.eliminarProveedor(proveedorEliminado) // Actualizar la lista en el adaptador
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.alerta_p))
                    .setMessage(resources.getString(R.string.mensaje_eliminar_p))
                    .setPositiveButton(resources.getString(R.string.aceptar_p), null)
                    .show()
            },*/
            onclick = { proveedorSeleccionado ->
                val accion =
                    ManteProveedorFragmentDirections.actionManteProveedorFragmentToProveedorAgregarFragment(proveedorSeleccionado)
                findNavController().navigate(accion)
            }
        )

        binding.rvProveedores.adapter = proveedorAdapt

        proViewModel.proveedores.observe(viewLifecycleOwner) { lista ->
            proveedorAdapt.listasProveedores(lista)
        }

        binding.btnAgregarProveedor.setOnClickListener {
            val directions =
                ManteProveedorFragmentDirections.actionManteProveedorFragmentToProveedorAgregarFragment(null)
            findNavController().navigate(directions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}