package com.aglafad.atipaxapp.ui.views.proveedor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

              binding.btnAdd.visibility = View.VISIBLE
              binding.btnUpdate.visibility = View.GONE
              binding.btnDelete.visibility = View.GONE
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

              binding.btnAdd.visibility = View.GONE
              binding.btnUpdate.visibility = View.VISIBLE
              binding.btnDelete.visibility = View.VISIBLE

          }

        binding.btnAdd.setOnClickListener {

            val nombr = binding.txtNombre.editText?.text.toString()
            val direc = binding.txtDireccion.editText?.text.toString()
            val corr = binding.txtCorreo.editText?.text.toString()
            val tef = binding.txtTelefono.editText?.text.toString()


            // pasamos al objeto los valores
                val objProveedor = Proveedor(0,nombr,direc,corr,tef.toInt())
                proViewModel.insertar(objProveedor)


            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_guardar_p))
                .setPositiveButton(resources.getString(R.string.aceptar_p)) { _, _ ->
                    val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

        binding.btnUpdate.setOnClickListener{
        //Declaramos los datos a actualizar

            val nombr = binding.txtNombre.editText?.text.toString()
            val direc = binding.txtDireccion.editText?.text.toString()
            val corr = binding.txtCorreo.editText?.text.toString()
            val tef = binding.txtTelefono.editText?.text.toString()
            val co = proveSelecc?.id_provee
            if (co != null) {
                val objProveedor = Proveedor(co,nombr,direc,corr,tef.toInt())
                proViewModel.actualizar(objProveedor)

            }
                // pasamos al objeto los valores

            // pasamos al objeto los valores


        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.alerta))
            .setMessage(resources.getString(R.string.mensaje_actualizacion_p))
            .setPositiveButton(resources.getString(R.string.aceptar_p)){ _, _ ->
                val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                findNavController().navigate(action)

            }
            .show()

    }

        binding.btnDelete.setOnClickListener {
            val co = proveSelecc?.id_provee
            if (co != null) {
                proViewModel.eliminar(co)
            }

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.alerta_p))
                .setMessage(resources.getString(R.string.mensaje_eliminar_p))
                .setPositiveButton(resources.getString(R.string.aceptar_p)){ _, _ ->
                    val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
                    findNavController().navigate(action)
                }
                .show()
        }

    binding.btnVovler.setOnClickListener {
        val action = ProveedorAgregarFragmentDirections.actionProveedorAgregarFragmentToManteProveedorFragment()
        findNavController().navigate(action)
    }

    }




    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}