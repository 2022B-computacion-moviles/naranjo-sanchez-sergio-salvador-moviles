package com.example.movcompssnspro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Carrito : AppCompatActivity() {
    var totalPagar = 0.0
    val listaProductos = arrayListOf<productos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        listaProductos.add(
            productos("bateria"
                ,1.35
                ,true
                ,10
                ,R.drawable.mapa)
        )
        listaProductos.add(
            productos("Acrilico"
                ,0.75
                ,true
                ,40
                ,R.drawable.mapa)
        )
        listaProductos.add(
            productos("Alfires"
                ,0.35
                ,true
                ,13
                ,R.drawable.mapa)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_productos)
        inicializarRecyclerView(listaProductos, recyclerView)
    }
    fun inicializarRecyclerView(
        lista: ArrayList<productos>,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorCarrito(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotal(precio: Double){
        totalPagar += precio
        val totalPagarText =findViewById<TextView>(R.id.text_total_pagar)
        totalPagarText.text = totalPagar.toString()
    }
    fun reducirTotal(precio: Double){
        totalPagar -= precio
        val totalPagarText =findViewById<TextView>(R.id.text_total_pagar)
        totalPagarText.text = totalPagar.toString()
    }
    fun eliminar(descripcion: String){
        listaProductos.removeIf { it.descripcion == descripcion }
        irActividad(Carrito::class.java)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}