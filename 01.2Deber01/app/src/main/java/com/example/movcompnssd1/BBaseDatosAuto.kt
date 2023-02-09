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
                    BAuto(1, "Sail", 1.4, 17000.50, true, 2, "Chevrolet", "USA", 1911, "Luis Chevrolet")
                )
            arregloAuto
                .add(
                    BAuto(2, "Cerato", 2.0, 21000.90, true, 4, "Kia", "Corea del Sur", 1944, "Kim Cheol-ho")
                )
            arregloAuto
                .add(
                    BAuto(3, "6", 2.5, 43999.99, false, 1, "Mazda", "Japon", 1920, "Jujiro Matsuda")
                )
            arregloAuto
                .add(
                    BAuto(4, "CX-3", 2.7, 24999.99, true, 1, "Mazda", "Japon", 1920, "Jujiro Matsuda")
                )
            arregloAuto
                .add(
                    BAuto(5, "CLIO E-TECH", 2.0, 19873.78, false, 5, "Renault", "Francia",1898, "Louis Renault")
                )
        }
    }
}