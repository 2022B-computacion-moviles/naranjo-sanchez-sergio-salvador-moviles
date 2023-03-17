package com.example.movcompssnex2b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import java.util.concurrent.ExecutionException

class crear_Auto : AppCompatActivity() {
    val FirestoreManager  = FirestoreManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_auto)

        val bundle = intent.extras
        val idMarca = bundle?.getString("idMarca")

        val modelo = findViewById<EditText>(R.id.input_modelo)
        val cilindraje = findViewById<EditText>(R.id.input_cilindraje)
        val precio = findViewById<EditText>(R.id.input_precio)
        val disponible = findViewById<EditText>(R.id.input_disponible)

        val BotonCrearAuto = findViewById<Button>(R.id.button_crear_auto)
        BotonCrearAuto
            .setOnClickListener{
                FirestoreManager.crearAuto(modelo.text.toString(),cilindraje.text.toString().toDouble(),
                    precio.text.toString().toDouble(),disponible.text.toString().toInt(), idMarca.toString())
                confirmacion("Se creo correctamente el Auto")
                limpiarCampos()
            }
    }

    fun confirmacion(Mensaje:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Mensaje)
        builder.setPositiveButton(
            "Aceptar",
            null
        )

        val dialog = builder.create()
        dialog.show()
    }

    fun limpiarCampos(){
        val modelo = findViewById<EditText>(R.id.input_modelo)
        val cilindraje = findViewById<EditText>(R.id.input_cilindraje)
        val precio = findViewById<EditText>(R.id.input_precio)
        val disponible = findViewById<EditText>(R.id.input_disponible)
        modelo.setText("")
        cilindraje.setText("")
        precio.setText("")
        disponible.setText("")
    }
}