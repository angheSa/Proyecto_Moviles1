package com.aglafad.atipaxapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.aglafad.atipaxapp.databinding.ActivityMainBinding
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.UsuarioDao
import kotlinx.coroutines.flow.Flow
import com.google.android.material.R

class MainActivity : AppCompatActivity() {

    private lateinit var db: AlmacenDatabase
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var user: EditText
    private lateinit var psw: EditText
    private lateinit var btn_ingresar: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, SegundaActivity::class.java)
        user = binding.txtUsuario
        psw = binding.txtPsw
        btn_ingresar = binding.btnIngresar
        db = Room.databaseBuilder(applicationContext, AlmacenDatabase::class.java, "BDAtipaxGroup")
            .fallbackToDestructiveMigration()
            .build()
        usuarioDao = db.usuarioDao()

        binding.btnIngresar.setOnClickListener {
            val usuario = user.text.toString()
            val contraseña = psw.text.toString()
            val usuarioDao = db.usuarioDao()
            var listaUsuarios: Flow<List<Usuario>> = usuarioDao.getUsuarios()
            val rojo = ContextCompat.getColor(this@MainActivity, R.color.m3_ref_palette_error40)
            val blanco = ContextCompat.getColor(this@MainActivity, R.color.m3_ref_palette_white)

            val nuevoUsu = Usuario(7, "1234")
            lifecycleScope.launch {
                usuarioDao.insert(nuevoUsu)
            }

            GlobalScope.launch(Dispatchers.Main) {
                val user = withContext(Dispatchers.IO) {
                    usuarioDao.getUsuarioLogin(usuario, contraseña)
                }
                if (user != null) {
                    // Usuario válido, realizar acción deseada
                    Toast.makeText(this@MainActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                } else {
                    // Usuario inválido, mostrar mensaje de error
                    Toast.makeText(this@MainActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                    if (usuario == ""){
                        binding.txtUsuario.setHint("Ingrese usuario")
                        binding.txtUsuario.setHintTextColor(rojo)
                        binding.lblUsuario.setTextColor(rojo)
                    }
                    if (contraseña == ""){
                        val rojo = ContextCompat.getColor(this@MainActivity, com.google.android.material.R.color.m3_ref_palette_error40)
                        binding.txtPsw.setHint("Ingrese contraseña")
                        binding.txtPsw.setHintTextColor(rojo)
                        binding.lblPsw.setTextColor(rojo)

                    }
                }
                //Recorrido para ver los usuarios en la tabla
                listaUsuarios.collect {elemento ->
                    println(elemento)
                }
            }
            binding.txtUsuario.addTextChangedListener{
                binding.txtUsuario.setHint("")
                binding.lblUsuario.setTextColor(blanco)
            }
            binding.txtPsw.addTextChangedListener {
                binding.txtPsw.setHint("")
                binding.lblPsw.setTextColor(blanco)
            }
        }

    }
}