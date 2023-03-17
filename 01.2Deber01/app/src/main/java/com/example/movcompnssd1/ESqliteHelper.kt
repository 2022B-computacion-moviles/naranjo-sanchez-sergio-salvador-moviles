package com.example.movcompnssd1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper (
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "concesionario",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        //script de creacionde base de datos
        val scriptSQLCrearTablaMarca =
            """
                CREATE TABLE tablaMarca(
                idMarca INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(100),
                pais VARCHAR(100),
                fundacion INTEGER,
                creador VARCHAR(100)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaMarca)
        val scriptSQLCrearTablaAuto = """
                CREATE TABLE tablaAuto(
                idAuto INTEGER PRIMARY KEY AUTOINCREMENT,
                modelo VARCHAR(100),
                cilindraje DOUBLE,
                precio DOUBLE,
                disponible INTEGER,
                idMarca INTEGER
                )                        
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaAuto)

        val scripIngrsarMarcas = """
            INSERT INTO tablaMarca (nombre, pais, fundacion, creador)
            VALUES ("Mazda", "Japon", 1920, "Jujiro Matsuda"),
            ("Chevrolet", "USA", 1911, "Luis Chevrolet"),
            ("Volkswagen", "Alemania", 19376, "Frente Aleman de Trabajo"),
            ("Kia", "Corea del Sur", 1944, "Kim Cheol-ho"),
            ("Renault", "Francia", 1898,"Louis Renault");
        """.trimIndent()
        db?.execSQL(scripIngrsarMarcas)

        val scriptIngresarAutos = """
            INSERT INTO tablaAuto (modelo, cilindraje, precio, disponible, idMarca)
            VALUES ("Sail", 1.4, 17000.50, 1, 1),
            ("Cerato", 2.0, 21000.90, 1, 1),
            ("6", 2.5, 43999.99, 0, 1),
            ("CX-3", 2.7, 24999.99, 0, 1),
            ("CLIO E-TECH", 2.0, 19873.78, 0, 2),
            ("Cerato", 2.0, 21000.90, 1, 2),
            ("6", 2.5, 43999.99, 0, 2),
            ("CX-3", 2.7, 24999.99, 0, 2),
            ("Cerato", 2.0, 21000.90, 1, 3),
            ("6", 2.5, 43999.99, 1, 3),
            ("CX-3", 2.7, 24999.99, 0, 3),
            ("Cerato", 2.0, 21000.90, 1, 4),
            ("6", 2.5, 43999.99, 0, 4),
            ("CX-3", 2.7, 24999.99, 1, 4),
            ("6", 2.5, 43999.99, 1, 5),
            ("CX-3", 2.7, 24999.99, 1, 5),
            ("CLIO E-TECH", 2.0, 19873.78, 0, 5)
        """.trimIndent()
        db?.execSQL(scriptIngresarAutos)

    }

    fun crearMarca(
        nombre: String,
        pais: String,
        fundacion: Int,
        creador: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("pais", pais)
        valoresAGuardar.put("fundacion", fundacion)
        valoresAGuardar.put("creador", creador)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "tablaMarca", // Tabla
                null, //
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun crearAuto(
        modelo: String,
        cilindraje: Double,
        precio: Double,
        disponible: Int,
        idMarca: Int
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("modelo", modelo)
        valoresAGuardar.put("cilindraje", cilindraje)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("disponible", disponible)
        valoresAGuardar.put("idMarca", idMarca)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "tablaAuto", // Tabla
                null, //
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarMarcaFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "tablaMarca", // TABLA
                "idMarca=?", //  id=? and nombre=? Where (podemos mandar parametros en orden)
                arrayOf( // Arreglo parametros en orden [1,"Adrian"]
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun eliminarAutoMarca(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "tablaAuto", // TABLA
                "idAuto=?", //  id=? and nombre=? Where (podemos mandar parametros en orden)
                arrayOf( // Arreglo parametros en orden [1,"Adrian"]
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarMarcaFormulario(
        nombre: String,
        pais: String,
        fundacion: Int,
        creador: String,
        idMarca: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("pais", pais)
        valoresAActualizar.put("fundacion", fundacion)
        valoresAActualizar.put("creador", creador)

        val resultadoActualizacion = conexionEscritura
            .update(
                "tablaMarca", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "idMarca=?", // Clausula Where
                arrayOf(
                    idMarca.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun actualizarAutoFormulario(
        modelo: String,
        cilindraje: Double,
        precio: Double,
        idAuto: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("modelo", modelo)
        valoresAActualizar.put("cilindraje", cilindraje)
        valoresAActualizar.put("precio", precio)
        val resultadoActualizacion = conexionEscritura
            .update(
                "tablaAuto", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "idAuto=?", // Clausula Where
                arrayOf(
                    idAuto.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun consultarMarcaPorId(id: Int): BMarca {
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM tablaMarca WHERE idMarca = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BMarca("0", "", "",0,"")
        // LOGICA OBTENER EL USUARIO
        do {
            val id = resultadoConsultaLectura.getString(0) // columna indice 0 -> ID
            val nombre = resultadoConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
            val pais = resultadoConsultaLectura.getString(2) // Columna indice 2 -> DESCRIPCION
            val fundacion = resultadoConsultaLectura.getInt(3)
            val creador = resultadoConsultaLectura.getString(4)
            if (id !== null) {
                usuarioEncontrado.idMarca = id
                usuarioEncontrado.nombre = nombre
                usuarioEncontrado.pais = pais
                usuarioEncontrado.fundacion = fundacion
                usuarioEncontrado.creador = creador
            }
        } while (resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun consultarElementos(): ArrayList<BMarca> {
        val baseDatosLectura = readableDatabase
        val scriptConsultarMarcas = "SELECT * FROM tablaMarca"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMarcas,
            null
        )
        var lista = ArrayList<BMarca>()
        if (resultadoConsultaLectura.moveToFirst()){
            do {
                lista.add(BMarca(
                    resultadoConsultaLectura.getString(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getInt(3),
                    resultadoConsultaLectura.getString(4)
                ))
            }while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()

        return lista
    }


    fun autosMarca(id: Int): ArrayList<BAuto>{
        val baseDatosLectura = readableDatabase
        val scriptConsultarAutos = "SELECT * FROM tablaAuto WHERE idMarca=${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarAutos,
            null
        )
        var lista = ArrayList<BAuto>()
        if (resultadoConsultaLectura.moveToFirst()){
            do {
                lista.add(BAuto(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getDouble(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getInt(4),
                    resultadoConsultaLectura.getInt(5)
                ))
            }while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()

        return lista
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}