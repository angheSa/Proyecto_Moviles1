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
import com.aglafad.atipaxapp.ui.adapter.HotelAdapter
import com.aglafad.atipaxapp.ui.adapter.TourAdapter
import com.aglafad.atipaxapp.ui.viewmodel.HotelViewModel
import com.aglafad.atipaxapp.ui.viewmodel.HotelViewModelFactory
import com.aglafad.atipaxapp.ui.viewmodel.TourViewModel
import com.aglafad.atipaxapp.ui.viewmodel.TourViewModelFactory
import androidx.recyclerview.widget.ConcatAdapter
import androidx.room.Room
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.room.AlmacenDatabase
import kotlinx.coroutines.launch


class ConsultPaquetesFragment : Fragment() {
    private var _binding: FragmentConsultPaquetesBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaHotel : List<Hotel>
    private lateinit var listaTour : List<Tour>

    //For Hotel
    private lateinit var hotelAdapter : HotelAdapter

    private val hotelViewModel : HotelViewModel by viewModels {
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .build()
        val hotelDao = database.hotelDao()
        val repositoryHotel = requireContext().applicationContext as AtipaxApplication
        HotelViewModelFactory(repositoryHotel.repositoryHotel, hotelDao)
    }
    //For Tour
    private lateinit var tourAdapter: TourAdapter
    private val tourViewModel : TourViewModel by viewModels{
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
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
        hotelViewModel.hotels.observe(viewLifecycleOwner) { list ->
            hotelAdapter.listasHoteles(list)
        }
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
        hotelAdapter = HotelAdapter(
            onclick = {selectedHotel ->
            }
        )

        var concatAdapters = ConcatAdapter(tourAdapter,hotelAdapter)
        binding.rvPaquetes.adapter = concatAdapters
        fun buscarFiltrados(){
            var textoIngresado : String = binding.txtBuscar.text.toString()
            if(textoIngresado.isEmpty()){
                listaHotel = listOf()
                listaTour = listOf()
                hotelAdapter.listas.clear()
                tourAdapter.listas.clear()
                hotelViewModel.hotels.observe(viewLifecycleOwner) { list ->
                    hotelAdapter.listasHoteles(list)
                }
                tourViewModel.tours.observe(viewLifecycleOwner){list ->
                    tourAdapter.listasTours(list)
                }
                hotelAdapter.listasHoteles(listaHotel)
                hotelAdapter.notifyDataSetChanged()
                tourAdapter.listasTours(listaTour)
                concatAdapters = ConcatAdapter(tourAdapter,hotelAdapter)
                binding.rvPaquetes.adapter = concatAdapters

            }else {
                hotelAdapter.listas.clear()
                tourAdapter.listas.clear()
                lifecycleScope.launch {
                    val listaHotelesF = hotelViewModel.buscarHotelesXFiltro(
                        textoIngresado,
                        textoIngresado,
                        textoIngresado
                    )
                    println("LA LISTA EL EN FRAGMENT ES: " + listaHotelesF)
                    // Hacer algo con la lista de hoteles filtrados
                    if (listaHotelesF != null) {
                        hotelAdapter.actualizarDatos(listaHotelesF)
                    }
                    hotelAdapter.notifyDataSetChanged()

                }
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
