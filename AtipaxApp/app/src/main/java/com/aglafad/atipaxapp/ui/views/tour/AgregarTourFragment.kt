package com.aglafad.atipaxapp.ui.views.tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentAgregarTourBinding
import com.aglafad.atipaxapp.databinding.FragmentProveedorAgregarBinding
import com.aglafad.atipaxapp.ui.views.proveedor.ProveedorAgregarFragmentDirections


class AgregarTourFragment : Fragment() {

    private var _binding: FragmentAgregarTourBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_tour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverMante.setOnClickListener {
            val action = AgregarTourFragmentDirections.actionAgregarTourFragmentToManteTourFragment()
            findNavController().navigate(action)
        }
    }

}