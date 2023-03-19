package com.example.movcompssnspro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Instances
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //Login
    lateinit var btnLogin: Button
    lateinit var btnLogout: Button
    lateinit var tvNombre: TextView
    fun cambiarNombre(nombre:String){
        tvNombre.text =nombre
    }
    private val respuestaLoginIntent = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){ res: FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode === RESULT_OK){
            if (res.idpResponse != null){
                seLogeo(res.idpResponse!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonContacto = findViewById<Button>(R.id.button_contactos)
        botonContacto
            .setOnClickListener {
                irActividad(Contacto::class.java)
            }

        //Autenticacion
        btnLogin = findViewById(R.id.btn_login_firebase)
        btnLogout = findViewById(R.id.btn_logout_firebase)
        tvNombre = findViewById(R.id.tv_nombre_firebase)
        cambiarNombre("Ingrese por favor")

        btnLogin.setOnClickListener{enviarIntentLogin()}
        btnLogout.setOnClickListener{logout()}

    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

    fun enviarIntentLogin(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
            //AuthUI.IdpConfig.EmailBuilder().build()
        )
        //Create and launch sign-in intent
        val signInIntent = AuthUI
            .getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        respuestaLoginIntent.launch(signInIntent)
    }

    fun seLogeo(
        res: IdpResponse
    ){
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        cambiarNombre(res.email!!)
        if (res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(
        usuario: IdpResponse
    ){
        //
    }

    fun logout(){
        btnLogout.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        cambiarNombre("Ingrese por favor")
        FirebaseAuth.getInstance().signOut()
    }
}