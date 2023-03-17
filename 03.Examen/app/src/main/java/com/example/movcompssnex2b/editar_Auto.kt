package com.example.movcompssnex2b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class editar_Auto : AppCompatActivity() {
    val FirestoreManager = FirestoreManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_auto)

        val bundle = intent.extras
        val idMarca = bundle?.getString("idMarca")
        val IdAuto = bundle?.getString("idAuto")

        
    }
}