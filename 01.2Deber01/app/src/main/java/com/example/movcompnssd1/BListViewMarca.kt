package com.example.movcompnssd1

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class BListViewMarca: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    val arreglo:ArrayList<BMarca> = BBaseDatosMarca.arregloBMarca
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lv_list_view_entrenador)
        val adaptador  = ArrayAdapter(
            this, //contexto
        android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}