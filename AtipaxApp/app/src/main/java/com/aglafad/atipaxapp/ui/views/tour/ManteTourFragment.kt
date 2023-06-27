package com.aglafad.atipaxapp.ui.views.tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.databinding.FragmentManteTourBinding
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.HotelDao
import com.aglafad.atipaxapp.ui.adapter.TourAdapter
import com.aglafad.atipaxapp.ui.viewmodel.TourViewModel
import com.aglafad.atipaxapp.ui.viewmodel.TourViewModelFactory


class ManteTourFragment : Fragment() {
    private var _binding : FragmentManteTourBinding? = null
    val binding get() = _binding !!

    private lateinit var tourAdapt: TourAdapter
    private val tViewModel: TourViewModel by viewModels {
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .fallbackToDestructiveMigration().build()
        val tourDao = database.tourDao()
        val repositoryTu = requireContext().applicationContext as AtipaxApplication
        TourViewModelFactory(repositoryTu.repositoryTour, tourDao)
    }

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
        tourAdapt = TourAdapter(

            onclick = { tourSeleccionado ->
                val accion = ManteTourFragmentDirections.actionManteTourFragmentToAgregarTourFragment(tourSeleccionado)
                findNavController().navigate(accion)
            }
        )

        binding.rvTours.adapter = tourAdapt

        tViewModel.tours.observe(viewLifecycleOwner) { lista ->
            tourAdapt.listasTours(lista)
        }

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