package com.aglafad.atipaxapp.ui.views.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
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
import com.aglafad.atipaxapp.ui.adapter.ProveedorAdapter

import com.aglafad.atipaxapp.ui.views.hotel.HotelAgregarFragmentArgs
import com.aglafad.atipaxapp.ui.views.hotel.HotelAgregarFragmentDirections
import com.aglafad.atipaxapp.ui.views.proveedor.ManteProveedorFragmentDirections

class HotelAgregarFragment : Fragment() {
  /*private var _binding: FragmentHotelAgregarBinding? = null
    private val binding get() = _binding!!
   // private lateinit var proveAdap : ProveedorAdapter
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
        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtener referencia al AutoCompleteTextView
        val autoCompleteTextView: AutoCompleteTextView = binding.autoComProveedor

        // Cargar lista de proveedores desde la base de datos o cualquier otra fuente de datos


        // Configurar adapter para el AutoCompleteTextView
      //  val proveedorAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listaProveedores)
        //autoCompleteTextView.setAdapter(proveedorAdapter)
       //
        // val pA = ProveedorAdapter(android.R.layout.item_c)
        val hotelSelecc = args.hoteles;
       // var lp : List<Proveedor> =  proveAdap.listasProveedores(lp)

        /*proveedorAdapt = ProveedorAdapter(
            /* onclickDelete = { proveedorEliminado ->
                 proveedorAdapt.eliminarProveedor(proveedorEliminado) // Actualizar la lista en el adaptador
                 MaterialAlertDialogBuilder(requireContext())
                     .setTitle(resources.getString(R.string.alerta_p))
                     .setMessage(resources.getString(R.string.mensaje_eliminar_p))
                     .setPositiveButton(resources.getString(R.string.aceptar_p), null)
                     .show()
             },*/
            onclick = { proveedorSeleccionado ->
                val accion =
                    ManteProveedorFragmentDirections.actionManteProveedorFragmentToProveedorAgregarFragment(proveedorSeleccionado)
                findNavController().navigate(accion)
            }
        )

        binding.it .adapter = proveedorAdapt*/
     //   val nombreProveedor = args2.proveedor?.nombre
      //  val listaProveedores: List<Proveedor> = obtenerListaProveedores()
        if (hotelSelecc == null){

            binding.btnAdd.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.GONE
            binding.btnDelete.visibility = View.GONE
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

            binding.btnAdd.visibility = View.GONE
            binding.btnUpdate.visibility = View.VISIBLE
            binding.btnDelete.visibility = View.VISIBLE

        }

        binding.btnAdd.setOnClickListener {
         //  val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
            val objHotel = Hotel(id,destin,descri,preci.toDouble(),prov.toInt())
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
          //  val codig = binding.txtCodigo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.txtProveedor.editText?.text.toString()

            // pasamos al objeto los valores
            val objHotel = Hotel(id,destin,descri,preci.toDouble(),prov.toInt())
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



*/
}