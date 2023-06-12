package com.aglafad.atipaxapp.ui.views.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentHotelAgregarBinding
import com.aglafad.atipaxapp.entity.Hotel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.navigation.fragment.findNavController
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.repository.ProveedorRepository
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.ProveedorDao
import com.aglafad.atipaxapp.ui.adapter.ProveedorAdapter
import com.aglafad.atipaxapp.ui.adapter.ProveedorAutoCompleteAdapter
import com.aglafad.atipaxapp.ui.viewmodel.*

import com.aglafad.atipaxapp.ui.views.hotel.HotelAgregarFragmentArgs
import com.aglafad.atipaxapp.ui.views.hotel.HotelAgregarFragmentDirections
import com.aglafad.atipaxapp.ui.views.proveedor.ManteProveedorFragmentDirections
import com.aglafad.atipaxapp.ui.views.proveedor.ProveedorAgregarFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HotelAgregarFragment : Fragment() {
  private var _binding: FragmentHotelAgregarBinding? = null
    private val binding get() = _binding!!

    private val hViewModel: HotelViewModel by activityViewModels {
     //   val repository = (requireActivity().application as AtipaxApplication).repository
       // ViewModelFactory(repository)
       val repositoryHote = requireContext().applicationContext as AtipaxApplication
       HotelViewModelFactory(repositoryHote.repositoryHotel)
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


        val autoCompletePoveedor: AutoCompleteTextView = binding.autoComProveedor

        var proveedorList = mutableListOf<Proveedor>()
        val proveedorAdapter = ProveedorAutoCompleteAdapter(requireContext(), proveedorList)
        autoCompletePoveedor.setAdapter(proveedorAdapter)
        val hotelSelecc = args.hoteles;

        if (hotelSelecc == null){

            binding.btnAgregar.visibility = View.VISIBLE
            binding.btnEditar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE
            binding.btnVolverMante.visibility = View.VISIBLE
        } else {



        //    val co = hotelSelecc?.id.toString()
            val des = hotelSelecc?.destinoHotel.toString()
            val descr = hotelSelecc?.descripcion.toString()
            val pre = hotelSelecc?.precio.toString()
       //     val nombreProveedor = hotelSelecc?.idProveedor.toString()

          //  binding.txtCodigoEdit.setText(co)

            binding.txtDestinoEdit.setText(des)
            binding.txtDescripcionEdit.setText(descr)
            binding.txtPrecioEdit.setText(pre)
            //binding.txtProveedorEdit.setText(nombreProveedor)


            binding.btnAgregar.visibility = View.GONE
            binding.btnEditar.visibility = View.VISIBLE
            binding.btnEliminar.visibility = View.VISIBLE
            binding.btnVolverMante.visibility = View.VISIBLE

        }

        binding.btnAgregar.setOnClickListener {
         //  val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
           // val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
     //       val objHotel = Hotel(id,destin,descri,preci.toDouble(),prov.toInt())
       //     hViewModel.insertar(objHotel)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar))
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

        binding.btnEditar.setOnClickListener{
            //Declaramos los datos a actualizar
          //  val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
          //  val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
       //     val objHotel = Hotel(id,destin,descri,preci.toDouble(),prov.toInt())
         //   hViewModel.insertar(objHotel)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_actualizacion))
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()

        }

        binding.btnEliminar.setOnClickListener {
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
        binding.btnVolverMante.setOnClickListener {
            val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
            findNavController().navigate(action)
        }


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}