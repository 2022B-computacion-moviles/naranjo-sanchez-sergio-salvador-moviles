package com.example.movcompnssd1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearMarca : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)

        val botonCrear = findViewById<Button>(R.id.btn_crear_marca2)
        botonCrear
            .setOnClickListener{
                val nombre = findViewById<EditText>(R.id.et_nuevo_marca)
                val pais = findViewById<EditText>(R.id.et_nuevo_pais)
                val fundacion = findViewById<EditText>(R.id.et_nuevo_anio)
                val creador = findViewById<EditText>(R.id.et_nuevo_creador)

                ESqliteHelper(this).crearMarca(
                    nombre.text.toString(),
                    pais.text.toString(),
                    fundacion.text.toString().toInt(),
                    creador.text.toString()
                )
                val intent = Intent(this@CrearMarca, MainActivity::class.java)
                startActivity(intent)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@CrearMarca, MainActivity::class.java)
        startActivity(intent)
    }
}