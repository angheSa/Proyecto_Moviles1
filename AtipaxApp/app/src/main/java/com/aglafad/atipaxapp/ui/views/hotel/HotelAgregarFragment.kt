package com.aglafad.atipaxapp.ui.views.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentHotelAgregarBinding
import com.aglafad.atipaxapp.entity.Hotel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.HotelDao
import com.aglafad.atipaxapp.ui.adapter.ProveedorAutoCompleteAdapter
import com.aglafad.atipaxapp.ui.viewmodel.*

class HotelAgregarFragment : Fragment() {
  private var _binding: FragmentHotelAgregarBinding? = null
    private val binding get() = _binding!!

    private val hViewModel: HotelViewModel by activityViewModels {

        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .build()
        val hotelDao = database.hotelDao()
        val repositoryHote = requireContext().applicationContext as AtipaxApplication
        HotelViewModelFactory(repositoryHote.repositoryHotel, hotelDao)
    }
    private val proveedorViewModel: ProveedorViewModel by viewModels {
        val repositoryProvee = requireContext().applicationContext as AtipaxApplication
        ProveedorViewModelFactory(repositoryProvee.repositoryProvedor)
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


        val autoCompleteProveedor: AutoCompleteTextView = binding.autoComProveedor

        var proveedorList = mutableListOf<Proveedor>()
    //    val proveedorAdapter = ProveedorAutoCompleteAdapter(requireContext(), proveedorList)
      //  autoCompletePoveedor.setAdapter(proveedorAdapter)


        val proveedorAdapter = ProveedorAutoCompleteAdapter(requireContext(), proveedorList)
        autoCompleteProveedor.setAdapter(proveedorAdapter)

        proveedorViewModel.proveedores.observe(viewLifecycleOwner) { proveedores ->
            proveedorList.clear()
            proveedorList.addAll(proveedores)
            proveedorAdapter.notifyDataSetChanged()
        }
        // pasar el id al txt
        autoCompleteProveedor.setOnItemClickListener { _, _, position, _ ->
            val selectedProveedor = proveedorAdapter.getItem(position)
            val proveedorCode = selectedProveedor?.id_provee.toString()
            autoCompleteProveedor.setText(proveedorCode, false)
        }


        val hotelSelecc = args.hoteles;

        if (hotelSelecc == null){

            binding.btnAgregar.visibility = View.VISIBLE
            binding.btnEditar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE
            binding.btnVolverMante.visibility = View.VISIBLE
        } else {



           val nom = hotelSelecc?.nombreHotel.toString()
            val des = hotelSelecc?.destinoHotel.toString()
            val descr = hotelSelecc?.descripcion.toString()
            val pre = hotelSelecc?.precio.toString()
           val cod = hotelSelecc?.idProveedor.toString()


           binding.txtNombreEdit.setText(nom)
            binding.txtDestinoEdit.setText(des)
            binding.txtDescripcionEdit.setText(descr)
            binding.txtPrecioEdit.setText(pre)
            binding.autoComProveedor.setText(cod)


            binding.btnAgregar.visibility = View.GONE
            binding.btnEditar.visibility = View.VISIBLE
            binding.btnEliminar.visibility = View.VISIBLE
            binding.btnVolverMante.visibility = View.VISIBLE

        }

        binding.btnAgregar.setOnClickListener {
           val nombr = binding.txtNombre.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
           val prov = binding.autoComProveedor.text.toString()
            if(destin.isEmpty()){
                binding.txtDestino.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(nombr.isEmpty()){
                binding.txtNombre.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(descri.isEmpty()){
                binding.txtDescripcion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if(preci.isEmpty()){
                binding.txtPrecio.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if(prov.isEmpty()){
                binding.txtProveedor.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            // pasamos al objeto los valores
          val objHotel = Hotel(0,destin,nombr,descri,preci.toDouble(),prov.toInt())

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar))
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    hViewModel.insertar(objHotel)
                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

        binding.btnEditar.setOnClickListener{
            //Declaramos los datos a actualizar
            val nombr = binding.txtNombre.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
           val prov = binding.txtProveedor.editText?.text.toString()
            val co = hotelSelecc?.id
            if(destin.isEmpty()){
                binding.txtDestino.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(nombr.isEmpty()){
                binding.txtNombre.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(descri.isEmpty()){
                binding.txtDescripcion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if(preci.isEmpty()){
                binding.txtPrecio.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if(prov.isEmpty()){
                binding.txtProveedor.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if (co != null) {
            // pasamos al objeto los valores
          val objHotel = Hotel(co,destin,nombr,descri,preci.toDouble(),prov.toInt())

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage("¿Quiere actualizar el hotel $nombr?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    hViewModel.actualizar(objHotel)

                    val action = HotelAgregarFragmentDirections.actionHotelAgregarFragmentToManteHotelFragment()
                    findNavController().navigate(action)
                }
                .show()
                 }
        }
        binding.txtNombre.editText?.addTextChangedListener {
            binding.txtNombre.error = null
        }
        binding.txtDescripcion.editText?.addTextChangedListener {
            binding.txtDescripcion.error = null
        }
        binding.txtDestino.editText?.addTextChangedListener {
            binding.txtDestino.error = null
        }
        binding.txtPrecio.editText?.addTextChangedListener {
            binding.txtPrecio.error = null
        }
        binding.txtProveedor.editText?.addTextChangedListener {
            binding.txtProveedor.error = null
        }
        binding.btnEliminar.setOnClickListener {
            val co = hotelSelecc?.id
            if (co != null) {
                hViewModel.eliminar(co)
            }

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_eliminar))
                .setNegativeButton("Cancelar", null)
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