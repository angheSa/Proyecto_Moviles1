package com.aglafad.atipaxapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.ActivitySegundaBinding
import com.google.firebase.auth.FirebaseAuth

class SegundaActivity : AppCompatActivity() {

    /*NECESARIO PARA EL MENU*/
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySegundaBinding
    private lateinit var btnExit: ImageButton
    private lateinit var auth: FirebaseAuth

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
            , R.id.manteHotelFragment, R.id.manteTourFragment, R.id.agregarTourFragment, R.id.consultPaquetesFragment,
            R.id.consultFullDayFragment, R.id.mapsFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

      // } supportActionBar.displayOptions(Action)
        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()?.setCustomView(R.layout.app_bar)

        auth = FirebaseAuth.getInstance()

        btnExit = findViewById(R.id.btn_exit)
        btnExit.setOnClickListener {
            auth.signOut()
            startActivity(
                Intent(this,MainActivity::class.java

                )
            )
            finish()
        }

    }
        

    /*NECESARIO PARA EL MENU*/
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
