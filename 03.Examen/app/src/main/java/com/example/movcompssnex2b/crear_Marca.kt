package com.example.movcompssnex2b

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class crear_Marca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)

        //Instanciar la base de datos
        val FirestoreManager = FirestoreManager()

        val nombreMarca = findViewById<EditText>(R.id.input_nombre_marca)
        val  pais = findViewById<EditText>(R.id.input_pais_marca)
        val fundacion = findViewById<EditText>(R.id.input_fundacion)
        val creador = findViewById<EditText>(R.id.input_creador)

        val botonCrearMarca = findViewById<Button>(R.id.button_crear_marca)
        botonCrearMarca
            .setOnClickListener{
                FirestoreManager.crearMarca(nombreMarca.text.toString(), pais.text.toString(),
                    fundacion.text.toString().toInt(), creador.text.toString())
                confirmacion("Marca Ingresada Correctamente")
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
        val nombreMarca = findViewById<EditText>(R.id.input_nombre_marca)
        val  pais = findViewById<EditText>(R.id.input_pais_marca)
        val fundacion = findViewById<EditText>(R.id.input_fundacion)
        val creador = findViewById<EditText>(R.id.input_creador)
        nombreMarca.setText("")
        pais.setText("")
        fundacion.setText("")
        creador.setText("")
    }
}