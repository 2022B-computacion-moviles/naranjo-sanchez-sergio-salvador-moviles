package com.example.movcompssns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicit_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)

        //lamar a devolver respuesta
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener{devolverRespuesta()}
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Pedro")
        intentDevolverParametros.putExtra("edadModificado", 32)
        //this.resulte
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        //this.finish()
        finish()
    }
}