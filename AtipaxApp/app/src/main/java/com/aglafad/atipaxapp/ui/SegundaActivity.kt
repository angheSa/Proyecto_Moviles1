package com.aglafad.atipaxapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.ActivitySegundaBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(R.navigation.nav_app)

        // Passing each menu ID as a set of Ids because each  bottom_navigation
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(

            )
        )

        //appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)

    }



}