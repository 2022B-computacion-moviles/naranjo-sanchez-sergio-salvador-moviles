package com.example.movcompnssd1

import java.time.LocalDate

class BAuto(
    private var idAuto: Int, //Propiedad
    private var modelo: String,
    private var cilindraje: Double,
    private var precio: Double,
    private var disponible: Boolean,
    idMarca: Int, //Propiedad
    nombre: String,
    pais: String,
    fundacion: LocalDate,
    creador: String,
):BMarca(
    idMarca,
    nombre,
    pais,
    fundacion,
    creador
) {

}