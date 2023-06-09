package com.aglafad.atipaxapp.ui.views.tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.databinding.FragmentConsultPaquetesBinding

import com.aglafad.atipaxapp.ui.adapter.TourAdapter

import com.aglafad.atipaxapp.ui.viewmodel.TourViewModel
import com.aglafad.atipaxapp.ui.viewmodel.TourViewModelFactory
import androidx.room.Room
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.room.AlmacenDatabase
import kotlinx.coroutines.launch


class ConsultPaquetesFragment : Fragment() {
    private var _binding: FragmentConsultPaquetesBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaTour : List<Tour>

   // CONSULTAS DE PAQUETES Y FULL DAYS

    private lateinit var tourAdapter: TourAdapter
    private val tourViewModel : TourViewModel by viewModels{
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .fallbackToDestructiveMigration()
            .build()
        val tourDao = database.tourDao()
        val repositoryTour = requireContext().applicationContext as AtipaxApplication
        TourViewModelFactory(repositoryTour.repositoryTour, tourDao)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConsultPaquetesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tourViewModel.tours.observe(viewLifecycleOwner){list ->
            tourAdapter.listasTours(list)
        }

        binding.btnVolverInicio.setOnClickListener {
            val accion = ConsultPaquetesFragmentDirections.actionConsultPaquetesFragmentToInicioFragment()
            findNavController().navigate(accion)
        }

        tourAdapter = TourAdapter(
            onclick = {selectedTour ->
            }
        )


        var concatAdapters = tourAdapter
        binding.rvPaquetes.adapter = concatAdapters
        fun buscarFiltrados(){
            var textoIngresado : String = binding.txtBuscar.text.toString()
            if(textoIngresado.isEmpty()){

                listaTour = listOf()

                tourAdapter.listas.clear()

                tourViewModel.tours.observe(viewLifecycleOwner){list ->
                    tourAdapter.listasTours(list)
                }

                tourAdapter.listasTours(listaTour)
                concatAdapters = tourAdapter
                binding.rvPaquetes.adapter = concatAdapters

            }else {
                tourAdapter.listas.clear()

                lifecycleScope.launch {
                    val listaToursF = tourViewModel.buscarToursXFiltro(
                        textoIngresado,
                        textoIngresado,
                        textoIngresado
                    )
                    println("LA LISTA EL EN FRAGMENT ES: " + listaToursF)
                    // Hacer algo con la lista de hoteles filtrados
                    if (listaToursF != null) {
                        tourAdapter.actualizarDatos(listaToursF)
                    }
                    tourAdapter.notifyDataSetChanged()

                }

            }
        }


        binding.txtBuscar.addTextChangedListener{
            buscarFiltrados()
        }
    }
}
