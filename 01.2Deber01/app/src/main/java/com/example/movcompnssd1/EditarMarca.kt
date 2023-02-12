package com.example.movcompnssd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarMarca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        val arreglo = ESqliteHelper(this).consultarElementos()
        //Recivir datos
        val idMarca = intent.extras?.getInt("idMarca")?: "No encontrado"
        println("Marca a actualizar: ${idMarca}")

        //Modificar llamada al boton modificar Marca
        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_marca)
        botonActualizar
            .setOnClickListener{
                val nombre = findViewById<EditText>(R.id.et_nombre_marca)
                val pais = findViewById<EditText>(R.id.et_pais_marca)
                val fundacion = findViewById<EditText>(R.id.et_anio_marca)
                val creador = findViewById<EditText>(R.id.et_creador_marca)
                println("Datos ${nombre.text.toString()} ${pais.text.toString()} ${fundacion.text.toString().toInt()} ${creador.text.toString()}")
                BaseDeDatos.tablaMarca!!.actualizarMarcaFormulario(
                    nombre.text.toString(),
                    pais.text.toString(),
                    fundacion.text.toString().toInt(),
                    creador.text.toString(),
                    idMarca as Int
                )
            }
    }
}