package com.example.movcompnssd1

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class BBaseDatosAuto {
    @RequiresApi(Build.VERSION_CODES.O)
    companion object{
        val arregloAuto = arrayListOf<BAuto>()
        init {
            arregloAuto
                .add(
                    BAuto(1, "Sail", 1.4, 17000.50, true, 2, "Chevrolet", "USA", LocalDate.parse("1911-11-03"), "Luis Chevrolet")
                )
            arregloAuto
                .add(
                    BAuto(2, "Cerato", 2.0, 21000.90, true, 4, "Kia", "Corea del Sur", LocalDate.parse("1944-12-11"), "Kim Cheol-ho")
                )
            arregloAuto
                .add(
                    BAuto(3, "6", 2.5, 43999.99, false, 1, "Mazda", "Japon", LocalDate.parse("1920-12-12"), "Jujiro Matsuda")
                )
            arregloAuto
                .add(
                    BAuto(4, "CX-3", 2.7, 24999.99, true, 1, "Mazda", "Japon", LocalDate.parse("1920-12-12"), "Jujiro Matsuda")
                )
            arregloAuto
                .add(
                    BAuto(5, "CLIO E-TECH", 2.0, 19873.78, false, 5, "Renault", "Francia", LocalDate.parse("1898-12-24"), "Louis Renault")
                )
        }
    }
}