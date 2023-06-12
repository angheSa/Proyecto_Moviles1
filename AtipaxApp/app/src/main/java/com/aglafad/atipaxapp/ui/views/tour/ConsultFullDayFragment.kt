package com.aglafad.atipaxapp.ui.views.tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentConsultFullDayBinding
import com.aglafad.atipaxapp.databinding.FragmentManteHotelBinding
import com.aglafad.atipaxapp.ui.views.proveedor.ManteProveedorFragmentDirections

class ConsultFullDayFragment : Fragment() {

    private var _binding: FragmentConsultFullDayBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsultFullDayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverInicio.setOnClickListener {
            val accion = ConsultFullDayFragmentDirections.actionConsultFullDayFragmentToInicioFragment()
            findNavController().navigate(accion)
        }
    }
}