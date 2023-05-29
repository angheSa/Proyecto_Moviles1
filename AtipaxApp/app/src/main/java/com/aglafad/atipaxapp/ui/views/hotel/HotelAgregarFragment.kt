package com.aglafad.atipaxapp.ui.views.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs

import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentHotelAgregarBinding
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.ui.viewmodel.HotelViewModel
import com.aglafad.atipaxapp.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.ui.views.proveedor.ProveedorAgregarFragmentDirections

//import com.aglafad.atipaxapp.ui.views.HotelAgregarFragmentArgs
//import com.aglafad.atipaxapp.ui.views.HotelAgregarFragmentDirections

class HotelAgregarFragment : Fragment() {
   private var _binding: FragmentHotelAgregarBinding? = null
    private val binding get() = _binding!!

    private val hViewModel: HotelViewModel by activityViewModels {
        val repository = (requireActivity().application as AtipaxApplication).repository
        ViewModelFactory(repository)
    }

    private val args: HotelAgregarFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHotelAgregarBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelSelecc = args.hoteles

        if (hotelSelecc == null){

            binding.btnAdd.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.GONE
            binding.btnDelete.visibility = View.GONE
        } else {


            val co = hotelSelecc?.id.toString()
            val des = hotelSelecc?.destinoHotel.toString()
            val descr = hotelSelecc?.descripcion.toString()
            val pre = hotelSelecc?.precio.toString()
            val provee = hotelSelecc?.idProveedor.toString()

            binding.txtCodigoEdit.setText(co)

            binding.txtDestinoEdit.setText(des)
            binding.txtDescripcionEdit.setText(descr)
            binding.txtPrecioEdit.setText(pre)
            binding.txtProveedorEdit.setText(provee)

            binding.btnAdd.visibility = View.GONE
            binding.btnUpdate.visibility = View.VISIBLE
            binding.btnDelete.visibility = View.VISIBLE

        }

        binding.btnAdd.setOnClickListener {
            val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
            val objHotel = Hotel(codig.toInt(),destin,descri,preci.toDouble(),prov.toInt())
            hViewModel.insertar(objHotel)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar))
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

        binding.btnUpdate.setOnClickListener{
            //Declaramos los datos a actualizar
            val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
            val objHotel = Hotel(codig.toInt(),destin,descri,preci.toDouble(),prov.toInt())
            hViewModel.insertar(objHotel)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_actualizacion))
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()

        }

        binding.btnDelete.setOnClickListener {
            val co = hotelSelecc?.id
            if (co != null) {
                hViewModel.eliminar(co)
            }

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_eliminar))
                .setPositiveButton(resources.getString(R.string.aceptar)){ _, _ ->
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()
        }



    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }




}