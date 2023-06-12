package com.aglafad.atipaxapp.ui.views.tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentManteProveedorBinding
import com.aglafad.atipaxapp.databinding.FragmentManteTourBinding
import com.aglafad.atipaxapp.ui.views.hotel.ManteHotelFragmentDirections
import com.aglafad.atipaxapp.ui.views.proveedor.ManteProveedorFragmentDirections


class ManteTourFragment : Fragment() {
    private var _binding : FragmentManteTourBinding? = null
    val binding get() = _binding !!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManteTourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAgregarTour.setOnClickListener {
            val accion = ManteTourFragmentDirections.actionManteTourFragmentToAgregarTourFragment(null)
            findNavController().navigate(accion)
        }
        binding.btnVolverInicio.setOnClickListener {
            val accion = ManteTourFragmentDirections.actionManteTourFragmentToInicioFragment()
            findNavController().navigate(accion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}