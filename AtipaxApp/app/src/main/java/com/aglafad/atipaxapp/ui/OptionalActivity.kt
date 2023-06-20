package com.aglafad.atipaxapp.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import com.aglafad.atipaxapp.databinding.ActivityOptionalBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.aglafad.atipaxapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class OptionalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOptionalBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fbdb: FirebaseFirestore
    private val rojo = Color.RED
    private val blanco = Color.WHITE
    private val negro = Color.BLACK
    lateinit var document : DocumentSnapshot

    //Credentials
    val sEmail : String = "groupatipax@hotmail.com"
    val sPassword : String = "Atipax123"
    private var etTo = ""
    private var etSubject = ""
    private var etMessage = ""
    private var user = ""

    val properties = Properties()
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityOptionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializando FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        fbdb = FirebaseFirestore.getInstance()


        binding.btnRecuperar.setOnClickListener() {

            properties["mail.smtp.auth"] = "true"
            properties["mail.smtp.starttls.enable"] = "true"
            properties["mail.smtp.host"] = "smtp.office365.com"
            properties["mail.smtp.port"] = "587"
            user = binding.txtId.text.toString().trim()

            binding.txtId.addTextChangedListener{
                binding.txtId.hint = ""
                binding.lblId.setTextColor(negro)
            }
            checkIfUserExists(user)
        }
        binding.imgHome.setOnClickListener(){
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(R.string.confirmacion)
            alertDialogBuilder.setMessage("¿Desea volver al Login?")
            alertDialogBuilder.setPositiveButton(R.string.aceptar) { dialog, which ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            alertDialogBuilder.setNegativeButton(R.string.cancelar) { dialog, which ->
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
    private inner class SendMail : AsyncTask<Message, String, String>() {
        override fun doInBackground(vararg messages: Message): String {
            return try {
                Transport.send(messages[0])
                "Success"
            } catch (e: MessagingException) {
                e.printStackTrace()
                "Error"
            }
        }
    }

    private fun checkIfUserExists(user: String) {

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(sEmail, sPassword)
            }
        })
        if (user == ""){
            binding.txtId.hint = "Ingrese usuario"
            binding.txtId.setHintTextColor(rojo)
            binding.lblId.setTextColor(rojo)
        }
        else{
            fbdb.collection("tb_usuario")
                .document(user)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        document = task.result
                        if (document.exists()) {
                            etTo = "" + document.getField("correo")
                            etSubject = "Recuperación de cuenta de " + document.getField("nombre")
                            etMessage = """
                                                <html>
                                                    <body>
                                                        <h3>Gracias por usar el servicio de recuperaci&oacute;n de contrase&ntilde;a.</h3>
                                                        <p>Su contrase&ntilde;a es: ${document.get("password")}</p>
                                                        <p>Por seguridad, no comparta su contrase&ntilde;a.</p>
                                                        <br>
                                                        <img src="https://i.ibb.co/zHqhzx0/atipaxgroup.jpg" alt="Descripción de la imagen" width="50%" height="auto">
                                                    </body>
                                                </html>"""
                            try {
                                val message : Message = MimeMessage(session)
                                message.setFrom(InternetAddress(sEmail))
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etTo))
                                message.subject = etSubject
                                message.setContent(etMessage, "text/html")
                                println("MENSAJE: " + message.content)
                                SendMail().execute(message)
                            }
                            catch (e : Exception){
                                println(e.message)
                            }
                        }
                    }
                }
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(R.string.confirmacion)
            alertDialogBuilder.setMessage("Si el usuario existe, se enviará la contraseña al correo vinculado.")
            alertDialogBuilder.setPositiveButton(R.string.aceptar) { dialog, which ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}