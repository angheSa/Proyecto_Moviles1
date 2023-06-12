package com.aglafad.atipaxapp.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
      //_binding = FragmentInicioBinding.inflate(inflater, container, false)

       // return binding.root


          /* val inicioViewModel =
                ViewModelProvider(this).get(InicioViewModel::class.java)*/

            _binding = FragmentInicioBinding.inflate(inflater, container, false)
            val root: View = binding.root

          /*  val textView: TextView = binding.textDashboard
            dashboardViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }*/
            return root


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
        binding.imgBtnFullDay.setOnClickListener {
            val accion = InicioFragmentDirections.actionInicioFragmentToConsultFullDayFragment()
            findNavController().navigate(accion)
        }
        binding.imgBtnPaquete.setOnClickListener{
            val accion = InicioFragmentDirections.actionInicioFragmentToConsultPaquetesFragment()
            findNavController().navigate(accion)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}