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
import androidx.room.Room
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.databinding.FragmentConsultFullDayBinding

import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.ui.adapter.HotelAdapter
import com.aglafad.atipaxapp.ui.viewmodel.HotelViewModel
import com.aglafad.atipaxapp.ui.viewmodel.HotelViewModelFactory

import kotlinx.coroutines.launch

class ConsultFullDayFragment : Fragment() {

    private var _binding: FragmentConsultFullDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaHotel : List<Hotel>


    private lateinit var hotelAdapter : HotelAdapter

    private val hotelViewModel : HotelViewModel by viewModels {
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .build()
        val hotelDao = database.hotelDao()
        val repositoryHotel = requireContext().applicationContext as AtipaxApplication
        HotelViewModelFactory(repositoryHotel.repositoryHotel, hotelDao)
    }
    //For Tour

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConsultFullDayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelViewModel.hotels.observe(viewLifecycleOwner) { list ->
            hotelAdapter.listasHoteles(list)
        }

        binding.btnVolverInicio.setOnClickListener {
            val accion = ConsultFullDayFragmentDirections.actionConsultFullDayFragmentToInicioFragment()
            findNavController().navigate(accion)
        }

        hotelAdapter = HotelAdapter(
            onclick = {selectedHotel ->
            }
        )

        var concatAdapters = hotelAdapter
        binding.rvHoteles.adapter = concatAdapters
        fun buscarFiltrados(){
            var textoIngresado : String = binding.txtBuscar.text.toString()
            if(textoIngresado.isEmpty()){
                listaHotel = listOf()
                hotelAdapter.listas.clear()
                hotelViewModel.hotels.observe(viewLifecycleOwner) { list ->
                    hotelAdapter.listasHoteles(list)
                }

                hotelAdapter.listasHoteles(listaHotel)
                hotelAdapter.notifyDataSetChanged()

                concatAdapters = hotelAdapter
                binding.rvHoteles.adapter = concatAdapters

            }else {
                hotelAdapter.listas.clear()
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


            }
        }


        binding.txtBuscar.addTextChangedListener{
            buscarFiltrados()
        }
    }
}