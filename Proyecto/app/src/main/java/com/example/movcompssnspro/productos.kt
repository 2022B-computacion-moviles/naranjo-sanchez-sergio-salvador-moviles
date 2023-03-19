package com.example.movcompssnspro

class productos {
    var cod_barra: String= ""
    var descripcion: String= ""
    var precio: Double?=null
    var disponibilidad: Boolean= false
    var stock: Int?=null
    var imagenProducto: Int?=null

    constructor(descripcion:String, precio: Double, disponibilidad:Boolean, stock:Int?, imagen: Int?){
        this.descripcion=descripcion
        this.precio=precio
        this.disponibilidad=disponibilidad
        this.stock=stock
        this.imagenProducto=imagen
    }

    constructor(){}

    override fun toString() : String{
        return "${descripcion}"
    }
}