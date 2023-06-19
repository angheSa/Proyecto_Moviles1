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
        val centro = LatLng(-150.0, -155.0)
        /*marcador 1*/
        val Hotel1 = LatLng(-34.0, 151.0)
        /*marcador 2*/
        val Hotel2 = LatLng(-35.0, -152.0)

        googleMap.addMarker(MarkerOptions().position(Hotel1).title("Hotel1: name"))
        googleMap.addMarker(MarkerOptions().position(Hotel2).title("Hotel2: name"))

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 14f))

        /*otra vista*/
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
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