package com.example.movcompnssd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CrearAutoMarca : AppCompatActivity() {
    var idMarca2 = 0
    var idAuto2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_auto_marca)

        //Recivir datos
        val idMarca = intent.extras?.getInt("idMarca")?: "No encontrado"
        idMarca2 = idMarca as Int
        println("Marca a visualizar: ${idMarca}")

        //Buscar los datos de Marca
        var marca = ESqliteHelper(this).consultarMarcaPorId(idMarca as Int)

        //Setear el nombre de la marca
        val textInicio = findViewById<TextView>(R.id.tv_autonuevo)
        textInicio.setText(marca.nombre)

        val botonCrearAuto = findViewById<Button>(R.id.btn_nuevo_guardar)
        botonCrearAuto
            .setOnClickListener{
                val modelo = findViewById<EditText>(R.id.et_nuevo_modelo)
                val cilindraje = findViewById<EditText>(R.id.et_nuevo_cilindraje)
                val precio = findViewById<EditText>(R.id.et_nuevo_precio)
                val disponible = 1
                val idMarca = idMarca

                ESqliteHelper(this).crearAuto(
                    modelo.text.toString(),
                    cilindraje.text.toString().toDouble(),
                    precio.text.toString().toDouble(),
                    disponible as Int,
                    idMarca as Int
                )
                val intent = Intent(this@CrearAutoMarca, VerAutosMarca::class.java)
                //Enviar parametros
                intent.putExtra("idMarca", idMarca2)
                intent.putExtra("idAuto", idAuto2)
                //Iniciar Editar
                startActivity(intent)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@CrearAutoMarca, VerAutosMarca::class.java)
        //Enviar parametros
        intent.putExtra("idMarca", idMarca2)
        intent.putExtra("idAuto", idAuto2)
        //Iniciar Editar
        startActivity(intent)
    }
}