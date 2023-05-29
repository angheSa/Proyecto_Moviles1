package com.aglafad.atipaxapp.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imgBtnProveedores.setOnClickListener {
            val accion = InicioFragmentDirections.actionInicioFragmentToManteProveedorFragment()
            findNavController().navigate(accion)

        }

        binding.imgBtnHotel.setOnClickListener {
            val accion = InicioFragmentDirections.actionInicioFragmentToManteHotelFragment()
            findNavController().navigate(accion)
        }

        binding.imgBtnTour.setOnClickListener {
            val accion = InicioFragmentDirections.actionInicioFragmentToManteTourFragment()
            findNavController().navigate(accion)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}