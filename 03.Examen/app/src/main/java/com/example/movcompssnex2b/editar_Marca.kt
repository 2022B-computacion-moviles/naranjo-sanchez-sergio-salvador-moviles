package com.example.movcompssnex2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class editar_Marca : AppCompatActivity() {
    val FirestoreManager = FirestoreManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)

        val bundle = intent.extras

        val nombre = findViewById<EditText>(R.id.input_actualizar_nombre)
        val pais = findViewById<EditText>(R.id.input_actualizar_pais)
        val fundacion = findViewById<EditText>(R.id.input_actualizar_fundacion)
        val creador = findViewById<EditText>(R.id.input_actualizar_creador)

        val idMarca = bundle?.getString("idMarca")
        nombre.setText(bundle?.getString("nombre"))
        pais.setText(bundle?.getString("pais"))
        fundacion.setText(bundle?.getString("fundacion"))
        creador.setText(bundle?.getString("creador"))

        val BotonEditarMarca = findViewById<Button>(R.id.button_actualizar_marca)
        BotonEditarMarca
            .setOnClickListener{
                FirestoreManager.editarMarca(idMarca.toString(),nombre.text.toString(),
                pais.text.toString(), fundacion.text.toString().toInt(), creador.text.toString())
            }
        confirmacion("Actualizacion realizada")
    }

    fun confirmacion(Mensaje:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Mensaje)
        builder.setPositiveButton(
            "Aceptar"
        ){_,_ -> irActividad(MainActivity::class.java)}
        val dialog = builder.create()
        dialog.show()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
