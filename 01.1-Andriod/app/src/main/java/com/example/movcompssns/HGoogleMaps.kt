package com.example.movcompssns

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMaps : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps)

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton
            .setOnClickListener{
                irCarolina()
            }

        inicializarLogicaMapa()
    }

    fun inicializarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{ googleMap ->
            if (googleMap != null){
                mapa = googleMap
                establecerConfiguraciones()
                irCarolina()

                val quicentro = LatLng(-0.176125, -78.480208)
                val titulo = "Quicentro"
                val marcador = anadirMarcador(quicentro, titulo)
                marcador.tag = "quicentro"

                val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.17650666916201632, -78.48365336170615),
                                LatLng(-0.1772362265237109, -78.48135739089254),
                                LatLng(-0.1890808007619437, -78.48339586965228),
                                LatLng(-0.1875787718569888, -78.48734408114484)
                            )
                    )
                val poligonoUno =googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.1964836555299014, -78.48097115478582),
                                LatLng(-0.20000269262415962, -78.4692552663351),
                                LatLng(-0.1895314093484852, -78.47157269481986),
                                LatLng(-0.18854436182252837, -78.47826748822027)
                            )
                    )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2"
                escucharListeners()
            }
        }
    }

    fun irCarolina(){
        val carolina = LatLng(-0.1825684318486696, -78.48447277600916)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun establecerConfiguraciones(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                uiSettings.isMyLocationButtonEnabled = true
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun solicitarpermiso(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation ==
                PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this, //contexto
            arrayOf(//Arreglo de permisos
            android.Manifest.permission.ACCESS_FINE_LOCATION// )
            ),
                1//codigo de peticion de los permisos
            )
        }
    }

    fun anadirMarcador(latLng: LatLng, title: String): Marker{
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolyLineClickListener ${it}")
            it.tag
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }
}