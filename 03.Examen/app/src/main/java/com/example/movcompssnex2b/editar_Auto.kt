package com.example.movcompssnex2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class editar_Auto : AppCompatActivity() {
    val FirestoreManager = FirestoreManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_auto)

        val bundle = intent.extras
        val idMarca = bundle?.getString("idMarca")
        val idAuto = bundle?.getString("idAuto")

        val modelo = findViewById<EditText>(R.id.input_actualizar_modelo)
        val cilindraje = findViewById<EditText>(R.id.input_actualizar_cilindraje)
        val precio = findViewById<EditText>(R.id.input_actualizar_precio)
        val disponible = findViewById<EditText>(R.id.input_actualizar_disponible)

        modelo.setText(bundle?.getString("modelo"))
        cilindraje.setText(bundle?.getString("cilindraje"))
        precio.setText(bundle?.getString("precio"))
        disponible.setText(bundle?.getString("disponible"))

        val BotonEditarAuto = findViewById<Button>(R.id.button_actualizar_auto)
        BotonEditarAuto
            .setOnClickListener{
                FirestoreManager.editarAuto(idAuto.toString(), modelo.text.toString(), cilindraje.text.toString().toDouble(),
                precio.text.toString().toDouble(), disponible.toString().toInt(), idMarca.toString())
            }
        confirmacion("Actualizacion realizada.")

    }

    var idMarca =""

    fun confirmacion(Mensaje:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Mensaje)
        builder.setPositiveButton(
            "Aceptar"
        ){_,_ -> irActividadID(mostrar_Autos::class.java)}
        val dialog = builder.create()
        dialog.show()
    }

    fun irActividadID(
        clase: Class<*>
    ){
        val intentMarcaAuto = Intent(this, clase)
        intentMarcaAuto.putExtra("idMarca", idMarca)
        startActivity(intentMarcaAuto)
    }
}