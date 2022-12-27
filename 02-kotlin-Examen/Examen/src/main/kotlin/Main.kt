import java.io.File
import java.io.InputStream
import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>){
    println("Hola mundo examen")

    val fecha = LocalDate.parse("2018-12-12")
    val marca: MarcaAuto = MarcaAuto(1, "Chevrolet", "USA", fecha, "Juan Perez")
    println(marca.toString())

    val auto: Auto = Auto(1,"sedan", 2.12, 3000.32, true, 1, "Chevrolet", "USA", fecha, "Juan Perez")
    println(auto.toString())


    val inputStream: InputStream = File("resources/bd.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

    //Hilo principal

    //cambiar control para iniciar
    var control = true
    while (control){
        println("Opciones: ")
        println("1.-Mostrar")
        println("2.-Crear")
        println("3.-Actualizar")
        println("4.-Eliminar")
        println("\n 5.-Salir")
        println("\n Que desea hacer: ")
        val opcion = readln().toInt()
        when(opcion){
            1 -> mostrar()
            2 -> escribir()
            3 -> modificar()
            4 -> println("Esto es Eliminar")
            5 -> control = false
            else -> {
                println("Ninguna opcion correcta seleccionada")
                control = false
            }
        }
    }
}

fun mostrar(): Int{
    println("\nAutos:")
    val inputStream: InputStream = File("resources/bd.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }
    var n = 1
    lineas.forEach { println("${n++}.- "+ it) }

    return n
}

fun escribir(){

    //Ingresar datos
    println("\nId de Marca: ")
    val idMarca = readln().toInt()
    println("Nombre marca: ")
    val marca = readln().toString()
    println("Pais de origen: ")
    val pais = readln().toString()
    println("Año de la marca: ")
    val fundacion = readln().toString()
    val fecha = LocalDate.parse(fundacion)
    println("Creador de la marca: ")
    val creador = readln().toString()
    println("Id de Auto: ")
    val idAuto = readln().toInt()
    println("Modelo de Auto: ")
    val modelo = readln().toString()
    println("Cilindraje de Motor: ")
    val cilindraje = readln().toDouble()
    println("Precio de Auto: ")
    val precio = readln().toDouble()
    println("Disponibilidad de Auto: ")
    val disponibilidad = readln().toBoolean()

    //Crear un objeto auto para guardarlo
    val auto: Auto = Auto(idAuto,modelo,cilindraje,precio, disponibilidad, idAuto, marca, pais, fecha, creador)
    val dat = auto.toString()
    //Guardar datos
    val bd = File("resources/bd.txt")
    bd.appendText(dat.toString())
    bd.appendText("\n")
}

fun modificar(){
    val id = mostrar()
    println("Codigo de registro de Auto a modificar: ")
    val marca = readln().toString()
    println("El codigo de Auto ingresado es 5")
}
open class MarcaAuto(
    private var idMarca: Int, //Propiedad
    private var nombre: String,
    private var pais: String,
    private var fundacion: LocalDate,
    private var creador: String
){
    init {
        idMarca
        nombre
        pais
        fundacion
        creador
    }
    //Ver Objeto
    override fun toString(): String {
        return "idMarca=$idMarca, nombre='$nombre', pais='$pais', fundacion=$fundacion, creador='$creador'"
    }
    //getters
    fun getIdMarca(): Int {
        return this.idMarca
    }
    fun getNombre(): String{
        return this.nombre
    }
    fun getPais(): String{
        return this.pais
    }
    fun getFundacion(): LocalDate{
        return this.fundacion
    }
    fun getCreador(): String{
        return this.creador
    }
    //setters
    fun setIdMarca(id: Int){
        this.idMarca = id
    }
    fun setNombre(nombre: String){
        this.nombre = nombre
    }
    fun setPais(pais: String){
        this.pais = pais
    }
    fun setFundacion(fund: LocalDate){
        this.fundacion = fund
    }
    fun setCreador(cred: String){
        this.creador = cred
    }

}

class Auto(
    private var idAuto: Int, //Propiedad
    private var modelo: String,
    private var cilindraje: Double,
    private var precio: Double,
    private var disponible: Boolean,
    idMarca: Int,
    nombre: String,
    pais: String,
    fundacion: LocalDate,
    creador: String
):MarcaAuto(
    idMarca,
            nombre,
            pais,
            fundacion,
            creador
){
    init {
        idAuto
        modelo
        cilindraje
        precio
        disponible
    }

    override fun toString(): String {
        return "idMarca=${getIdMarca()}, nombre=${getNombre()}, pais=${getPais()}, fundacion=${getFundacion()}, creador=${getCreador()}, idAuto=$idAuto, modelo='$modelo', cilindraje=$cilindraje, precio=$precio, disponible=$disponible"
    }
    //getters
    fun getIdAuto(): Int{
        return this.idAuto
    }
    fun getModelo(): String{
        return this.modelo
    }
    fun getCilindraje(): Double{
        return this.cilindraje
    }
    fun getPrecio():Double{
        return this.precio
    }
    fun getDisponible(): Boolean{
        return this.disponible
    }
    //setters
    fun setIdAuto(id: Int){
        this.idAuto = id
    }
    fun setModelo(model: String){
        this.modelo = model
    }
    fun setCilindraje(cilin: Double){
        this.cilindraje = cilin
    }
    fun setPrecio(prec: Double){
        this.precio = prec
    }
    fun setDisponible(dis: Boolean){
        this.disponible = dis
    }

}