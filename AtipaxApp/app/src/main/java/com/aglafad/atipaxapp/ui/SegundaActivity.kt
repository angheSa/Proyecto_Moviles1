package com.aglafad.atipaxapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.ActivitySegundaBinding
import com.aglafad.atipaxapp.ui.views.InicioFragment
import com.aglafad.atipaxapp.ui.views.InicioFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class SegundaActivity : AppCompatActivity() {

    /*NECESARIO PARA EL MENU*/
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySegundaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

    /*NECESARIO PARA EL MENU*/
        val navController = findNavController(R.id.nav_host_fragment)
      //  navController.setGraph(R.navigation.nav_app)

      // appBarConfiguration = AppBarConfiguration(navController.graph)
         appBarConfiguration = AppBarConfiguration(
            setOf(
                // Se pasa el id de los fragmentos que no queremos que tengán el boton bar de volver
                 R.id.inicioFragment,R.id.hotelAgregarFragment, R.id.proveedorAgregarFragment, R.id.manteProveedorFragment
            , R.id.manteHotelFragment, R.id.manteTourFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        }

    /*NECESARIO PARA EL MENU*/
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
