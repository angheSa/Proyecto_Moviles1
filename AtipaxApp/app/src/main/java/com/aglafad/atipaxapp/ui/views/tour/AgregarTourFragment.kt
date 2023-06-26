package com.aglafad.atipaxapp.ui.views.tour

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.aglafad.atipaxapp.AtipaxApplication
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentAgregarTourBinding
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.entity.Tour
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.ui.adapter.ProveedorAutoCompleteAdapter
import com.aglafad.atipaxapp.ui.viewmodel.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AgregarTourFragment : Fragment() {

    private var _binding: FragmentAgregarTourBinding? = null
    private val binding get() = _binding!!

    private val tViewModel: TourViewModel by activityViewModels {

        val repositoryTour= requireContext().applicationContext as AtipaxApplication
        val database = Room.databaseBuilder(requireContext(), AlmacenDatabase::class.java, "BDAtipaxGroup")
            .build()
        val tourDao = database.tourDao()
        TourViewModelFactory(repositoryTour.repositoryTour, tourDao)
    }
    private val proveedorViewModel: ProveedorViewModel by viewModels {
        val repositoryProvee = requireContext().applicationContext as AtipaxApplication
        ProveedorViewModelFactory(repositoryProvee.repositoryProvedor)
    }
    private val args: AgregarTourFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgregarTourBinding.inflate(inflater, container, false)
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


        val tourSelecc = args.tours;

        if (tourSelecc == null){

            binding.btnAgregar.visibility = View.VISIBLE
            binding.btnEditar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE
            binding.btnVolverMante.visibility = View.VISIBLE
        } else {
            val tip = tourSelecc?.tipo.toString()
            val des = tourSelecc?.destino.toString()
            val descr = tourSelecc?.descripcion.toString()
            val pre = tourSelecc?.precio.toString()
            val cod = tourSelecc?.idProveedor.toString()


            binding.txtTipoEdit.setText(tip)
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
            val tipo = binding.txtTipo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.autoComProveedor.text.toString()
            if(destin.isEmpty()){
                binding.txtDestino.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if(descri.isEmpty()){
                binding.txtDescripcion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(tipo.isEmpty()){
                binding.txtTipo.error = resources.getString(R.string.campo)
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
            val objTour = Tour(0,destin,tipo,descri,preci.toDouble(),prov.toInt())
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar))
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                    tViewModel.insertar(objTour)
                    val action = AgregarTourFragmentDirections.actionAgregarTourFragmentToManteTourFragment()
                    findNavController().navigate(action)
                }
                .show()
        }



        binding.btnEditar.setOnClickListener{
            //Declaramos los datos a actualizar
            val tipo = binding.txtTipo.editText?.text.toString()
            val destin = binding.txtDestino.editText?.text.toString()
            val descri = binding.txtDescripcion.editText?.text.toString()
            val preci = binding.txtPrecio.editText?.text.toString()
            val prov = binding.txtProveedor.editText?.text.toString()
            if(destin.isEmpty()){
                binding.txtDestino.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(descri.isEmpty()){
                binding.txtDescripcion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(tipo.isEmpty()){
                binding.txtTipo.error = resources.getString(R.string.campo)
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


            val co = tourSelecc?.id
            if (co != null) {
                // pasamos al objeto los valores
                val objTour = Tour(co,destin,tipo,descri,preci.toDouble(),prov.toInt())

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.alerta_p))
                    .setMessage("¿Quiere actualizar el tour?")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                        tViewModel.actualizar(objTour)

                        val action = AgregarTourFragmentDirections.actionAgregarTourFragmentToManteTourFragment()
                        findNavController().navigate(action)
                    }
                    .show()
            }
        }

        binding.txtTipo.editText?.addTextChangedListener {
            binding.txtTipo.error = null
        }
        binding.txtDestino.editText?.addTextChangedListener {
            binding.txtDestino.error = null
        }
        binding.txtProveedor.editText?.addTextChangedListener {
            binding.txtProveedor.error = null
        }
        binding.txtDescripcion.editText?.addTextChangedListener {
            binding.txtDescripcion.error = null
        }
        binding.txtPrecio.editText?.addTextChangedListener {
            binding.txtPrecio.error = null
        }
        binding.btnEliminar.setOnClickListener {
            val co = tourSelecc?.id
            if (co != null) {
                tViewModel.eliminar(co)
            }

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_eliminarT))
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(resources.getString(R.string.aceptar)){ _, _ ->
                    val action = AgregarTourFragmentDirections.actionAgregarTourFragmentToManteTourFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

        binding.btnVolverMante.setOnClickListener {
            val action = AgregarTourFragmentDirections.actionAgregarTourFragmentToManteTourFragment()
            findNavController().navigate(action)
        }
    }

}