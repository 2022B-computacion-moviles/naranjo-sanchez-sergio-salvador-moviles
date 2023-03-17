package com.example.movcompnssd1

import android.os.Build
import androidx.annotation.RequiresApi

class BBaseDatosMarca {

    @RequiresApi(Build.VERSION_CODES.O)
    companion object{
        val arregloBMarca = arrayListOf<BMarca>()

        init {
            arregloBMarca
                .add(
                    BMarca("1", "Mazda", "Japon", 1920, "Jujiro Matsuda")
                )
            arregloBMarca
                .add(
                    BMarca("2", "Chevrolet", "USA", 1911, "Luis Chevrolet")
                )
            arregloBMarca
                .add(
                    BMarca("3", "Volkswagen", "Alemania", 19376-28, "Frente Aleman de Trabajo")
                )
            arregloBMarca
                .add(
                    BMarca("4", "Kia", "Corea del Sur", 1944, "Kim Cheol-ho")
                )
            arregloBMarca
                .add(
                    BMarca("5", "Renault", "Francia", 1898,"Louis Renault")
                )
        }
    }
}
