package com.example.movcompnssd1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarAuto : AppCompatActivity() {
    var idMarca2 = 0
    var idAuto2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_auto)

        //Recivir datos
        val idMarca = intent.extras?.getInt("idMarca")?: "No encontrado"
        idMarca2 = idMarca as Int
        val idAuto = intent.extras?.getInt("idAuto")?: "No encontrado"
        idAuto2 = idAuto as Int
        println("Marca a visualizar: ${idMarca}")

        val botonActualizar = findViewById<Button>(R.id.btn_editar_auto)
        botonActualizar
            .setOnClickListener{
                val modelo = findViewById<EditText>(R.id.et_editar_modelo)
                val cilindraje = findViewById<EditText>(R.id.et_editar_cilindraje)
                val precio = findViewById<EditText>(R.id.et_editar_precio)

                ESqliteHelper(this).actualizarAutoFormulario(
                    modelo.text.toString(),
                    cilindraje.text.toString().toDouble(),
                    precio.text.toString().toDouble(),
                    idAuto as Int
                )

                val intent = Intent(this@EditarAuto, VerAutosMarca::class.java)
                //Enviar parametros
                intent.putExtra("idMarca", idMarca2)
                intent.putExtra("idAuto", idAuto2)
                //Iniciar Editar
                startActivity(intent)
            }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@EditarAuto, VerAutosMarca::class.java)
        //Enviar parametros
        intent.putExtra("idMarca", idMarca2)
        intent.putExtra("idAuto", idAuto2)
        //Iniciar Editar
        startActivity(intent)
    }
}