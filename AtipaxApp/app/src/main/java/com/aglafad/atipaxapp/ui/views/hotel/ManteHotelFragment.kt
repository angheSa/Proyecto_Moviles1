package com.aglafad.atipaxapp.ui.views.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels


import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.databinding.FragmentManteHotelBinding
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.HotelDao

import com.aglafad.atipaxapp.ui.adapter.HotelAdapter
import com.aglafad.atipaxapp.ui.viewmodel.*


class ManteHotelFragment : Fragment() {

    private var _binding: FragmentManteHotelBinding? = null
    private val binding get() = _binding!!

    private lateinit var hotelAdapt: HotelAdapter
    private val hViewModel: HotelViewModel by viewModels {
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .fallbackToDestructiveMigration().build()
        val hotelDao = database.hotelDao()
        val repositoryHo = requireContext().applicationContext as AtipaxApplication
        HotelViewModelFactory(repositoryHo.repositoryHotel, hotelDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManteHotelBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hotelAdapt = HotelAdapter(
            /* onclickDelete = { proveedorEliminado ->
                 proveedorAdapt.eliminarProveedor(proveedorEliminado) // Actualizar la lista en el adaptador
                 MaterialAlertDialogBuilder(requireContext())
                     .setTitle(resources.getString(R.string.alerta_p))
                     .setMessage(resources.getString(R.string.mensaje_eliminar_p))
                     .setPositiveButton(resources.getString(R.string.aceptar_p), null)
                     .show()
             },*/
            onclick = { hotelSeleccionado ->
                val accion = ManteHotelFragmentDirections.actionManteHotelFragmentToHotelAgregarFragment(hotelSeleccionado)
                findNavController().navigate(accion)
            }
        )

        binding.rvHoteles.adapter = hotelAdapt

        hViewModel.hotels.observe(viewLifecycleOwner) { lista ->
            hotelAdapt.listasHoteles(lista)
        }

        binding.btnAgregarHotel.setOnClickListener {
            val accion = ManteHotelFragmentDirections.actionManteHotelFragmentToHotelAgregarFragment(null)
            findNavController().navigate(accion)
        }
        binding.btnVolverInicio.setOnClickListener {
            val accion = ManteHotelFragmentDirections.actionManteHotelFragmentToInicioFragment()
            findNavController().navigate(accion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}