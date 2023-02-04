package com.example.movcompnssd1

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class BBaseDatosMarca {

    @RequiresApi(Build.VERSION_CODES.O)
    companion object{
        val arregloBMarca = arrayListOf<BMarca>()

        init {
            arregloBMarca
                .add(
                    BMarca(1, "Mazda", "Japon", LocalDate.parse("1920-12-12"), "Jujiro Matsuda")
                )
            arregloBMarca
                .add(
                    BMarca(2, "Chevrolet", "USA", LocalDate.parse("1911-11-03"), "Luis Chevrolet")
                )
            arregloBMarca
                .add(
                    BMarca(3, "Volkswagen", "Alemania", LocalDate.parse("1937-06-28"), "Frente Aleman de Trabajo")
                )
            arregloBMarca
                .add(
                    BMarca(4, "Kia", "Corea del Sur", LocalDate.parse("1944-12-11"), "Kim Cheol-ho")
                )
            arregloBMarca
                .add(
                    BMarca(5, "Renault", "Francia", LocalDate.parse("1898-12-24"), "Louis Renault")
                )
        }
    }
}
