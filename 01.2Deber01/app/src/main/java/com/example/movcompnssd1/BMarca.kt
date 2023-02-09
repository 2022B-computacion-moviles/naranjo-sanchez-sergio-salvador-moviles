package com.example.movcompnssd1

import java.time.LocalDate

open class BMarca(
    private var idMarca: Int, //Propiedad
    private var nombre: String,
    private var pais: String,
    private var fundacion: Int,
    private var creador: String
) {
    override fun toString(): String {
        return "${nombre}  - ${pais}"
    }
}