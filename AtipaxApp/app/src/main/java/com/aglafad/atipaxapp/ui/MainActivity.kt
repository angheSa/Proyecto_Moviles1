package com.aglafad.atipaxapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.aglafad.atipaxapp.databinding.ActivityMainBinding
import com.aglafad.atipaxapp.entity.Usuario
import com.aglafad.atipaxapp.room.AlmacenDatabase
import com.aglafad.atipaxapp.room.UsuarioDao
import kotlinx.coroutines.flow.Flow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.graphics.Color
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField

class MainActivity : AppCompatActivity() {

    private lateinit var db: AlmacenDatabase
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var user: EditText
    private lateinit var psw: EditText
    private lateinit var btn_ingresar: Button
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fbdb: FirebaseFirestore
    private val rojo = Color.RED
    private val blanco = Color.WHITE
    private val negro = Color.BLACK
    public lateinit var document : DocumentSnapshot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializando FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        fbdb = FirebaseFirestore.getInstance()

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
            binding.txtUsuario.addTextChangedListener{
                binding.txtUsuario.setHint("")
                binding.lblUsuario.setTextColor(negro)
            }
            binding.txtPsw.addTextChangedListener {
                binding.txtPsw.setHint("")
                binding.lblPsw.setTextColor(negro)
            }
            checkIfUserExists(usuario, contraseña)

        }
    }
    private fun checkIfUserExists(user: String, psw : String) {
        val intent = Intent(this, SegundaActivity::class.java)
        if (user == "" && psw == ""){
            binding.txtUsuario.setHint("Ingrese usuario")
            binding.txtUsuario.setHintTextColor(rojo)
            binding.lblUsuario.setTextColor(rojo)
            binding.txtPsw.setHint("Ingrese contraseña")
            binding.txtPsw.setHintTextColor(rojo)
            binding.lblPsw.setTextColor(rojo)
        }
        else if (user == ""){
            binding.txtUsuario.setHint("Ingrese usuario")
            binding.txtUsuario.setHintTextColor(rojo)
            binding.lblUsuario.setTextColor(rojo)
        }
        else if (psw == ""){
            binding.txtPsw.setHint("Ingrese contraseña")
            binding.txtPsw.setHintTextColor(rojo)
            binding.lblPsw.setTextColor(rojo)
        }
        else {
            fbdb.collection("tb_usuario")
                .document(user)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        document = task.result
                        if (document.exists()) {
                            // El usuario existe en la colección
                            // Puedes acceder a los datos del usuario utilizando document.data
                            if (document.get("password") == psw ) {
                                startActivity(intent)
                                Toast.makeText(this@MainActivity,"Bienvenido, " + document.getField("nombre"),Toast.LENGTH_SHORT).show()
                            }
                            else
                                Toast.makeText(
                                    this@MainActivity,
                                    "Contraseña no válida",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                        else {
                            Toast.makeText(
                                this@MainActivity,
                                "El usuario no existe",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }
}