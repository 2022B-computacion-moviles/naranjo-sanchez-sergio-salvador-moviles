import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>){
    println("Hola mundo examen")

    val fecha = LocalDate.parse("2018-12-12")
    val marca: MarcaAuto = MarcaAuto(1, "Chevrolet", "USA", fecha, "Juan Perez")
    println(marca.getCreador())

    val auto: Auto = Auto(1,"sedan", 2.12, 3000.32, true)
    println(auto.toString())
}

class MarcaAuto(
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

    override fun toString(): String {
        return "idMarca=$idMarca, nombre='$nombre', pais='$pais', fundacion=$fundacion, creador='$creador'"
    }

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
    private var disponible: Boolean
){
    init {
        idAuto
        modelo
        cilindraje
        precio
        disponible
    }

    override fun toString(): String {
        return "idAuto=$idAuto, modelo='$modelo', cilindraje=$cilindraje, precio=$precio, disponible=$disponible"
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