package com.aglafad.atipaxapp.ui.views.mapas

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /*centro*/
        val centro = LatLng(-12.103295606571992, -77.03186400006827)
        /*marcador 1 -12.103295606571992, -77.03186400006827*/
        val Atipax = LatLng(-12.103295606571992, -77.03186400006827)
        /*marcador 2 -12.104187284533925, -77.0317459828462*/
        val Hotel2 = LatLng(-12.104187284533925, -77.03186400006827)

        googleMap.addMarker(MarkerOptions().position(Atipax).title("Atipax Group"))
        googleMap.addMarker(MarkerOptions().position(Hotel2).title("Agregar Ubicacion"))

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 15f))

        /*otra vista*/
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        binding.tgbOpciones.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when(checkedId) {
                    binding.btnNormal.id -> googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                    binding.btnSatelite.id -> googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                    binding.btnHibrido.id -> googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapsBinding.inflate(inflater,container,false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)



        

    }
}