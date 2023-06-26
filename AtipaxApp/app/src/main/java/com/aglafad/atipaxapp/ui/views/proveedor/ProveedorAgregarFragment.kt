package com.aglafad.atipaxapp.ui.views.proveedor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aglafad.atipaxapp.AtipaxApplication

import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentProveedorAgregarBinding
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.ui.viewmodel.ProveedorViewModel
import com.aglafad.atipaxapp.ui.viewmodel.ProveedorViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ProveedorAgregarFragment : Fragment() {
    private var _binding: FragmentProveedorAgregarBinding? = null
    private val binding get() = _binding!!

    private val proViewModel: ProveedorViewModel by viewModels {
        val repositoryProvee = requireContext().applicationContext as AtipaxApplication
        ProveedorViewModelFactory(repositoryProvee.repositoryProvedor)
    }

    private val args: ProveedorAgregarFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProveedorAgregarBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val proveSelecc = args.proveedor

          if (proveSelecc == null){

              binding.btnAgregar.visibility = View.VISIBLE
              binding.btnEditar.visibility = View.GONE
              binding.btnEliminar.visibility = View.GONE
              binding.btnVolverMante.visibility = View.VISIBLE
          } else {


            //  val co = proveSelecc?.id_provee.toString()
              val nom = proveSelecc?.nombre.toString()
              val di = proveSelecc?.direc.toString()
              val corr = proveSelecc?.correo.toString()
              val telef = proveSelecc?.telefono.toString()

             // binding.txtCodigoEdit.setText(co)
              binding.txtNombreEdit.setText(nom)
              binding.txtDireccionEdit.setText(di)
              binding.txtCorreoEdit.setText(corr)
              binding.txtTelefonoEdit.setText(telef)

              binding.btnAgregar.visibility = View.GONE
              binding.btnEditar.visibility = View.VISIBLE
              binding.btnEliminar.visibility = View.VISIBLE
              binding.btnVolverMante.visibility = View.VISIBLE
          }

        binding.btnAgregar.setOnClickListener {

            val nombr = binding.txtNombre.editText?.text.toString()
            val direc = binding.txtDireccion.editText?.text.toString()
            val corr = binding.txtCorreo.editText?.text.toString()
            val tef = binding.txtTelefono.editText?.text.toString()
            if(nombr.isEmpty()){
                binding.txtNombre.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(direc.isEmpty()){
                binding.txtDireccion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(corr.isEmpty()){
                binding.txtCorreo.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(tef.isEmpty()){
                binding.txtTelefono.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            val objProveedor = Proveedor(0,nombr,direc,corr,tef.toInt())
            // pasamos al objeto los valores
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar_p))
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(resources.getString(R.string.aceptar_p)) { _, _ ->
                    proViewModel.insertar(objProveedor)
                    val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                    findNavController().navigate(action)
                }
                .show()





        }

        binding.btnEditar.setOnClickListener{
        //Declaramos los datos a actualizar

            val nombr = binding.txtNombre.editText?.text.toString()
            val direc = binding.txtDireccion.editText?.text.toString()
            val corr = binding.txtCorreo.editText?.text.toString()
            val tef = binding.txtTelefono.editText?.text.toString()
            val co = proveSelecc?.id_provee

            if(nombr.isEmpty()){
                binding.txtNombre.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(direc.isEmpty()){
                binding.txtDireccion.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(corr.isEmpty()){
                binding.txtCorreo.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }
            if(tef.isEmpty()){
                binding.txtTelefono.error = resources.getString(R.string.campo)
                return@setOnClickListener
            }

            if (co != null) {
                val objProveedor = Proveedor(co,nombr,direc,corr,tef.toInt())

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.alerta))
                    .setMessage("¿Quiere actualizar el proveedor $nombr?")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton(resources.getString(R.string.aceptar_p)){ _, _ ->
                        proViewModel.actualizar(objProveedor)
                        val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                        findNavController().navigate(action)

                    }
                    .show()

            }


    }
        binding.txtNombre.editText?.addTextChangedListener {
            binding.txtNombre.error = null
        }
        binding.txtDireccion.editText?.addTextChangedListener {
            binding.txtDireccion.error = null
        }
        binding.txtCorreo.editText?.addTextChangedListener {
            binding.txtCorreo.error = null
        }
        binding.txtTelefono.editText?.addTextChangedListener {
            binding.txtTelefono.error = null
        }
        binding.btnEliminar.setOnClickListener {
            val co = proveSelecc?.id_provee
            if (co != null) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.alerta_p))
                    .setMessage(resources.getString(R.string.mensaje_eliminar_p))
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton(resources.getString(R.string.aceptar_p)){ _, _ ->
                        proViewModel.eliminar(co)
                        val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                        findNavController().navigate(action)
                    }
                    .show()

            }


        }

    binding.btnVolverMante.setOnClickListener {
        val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
        findNavController().navigate(action)
    }

    }




    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}