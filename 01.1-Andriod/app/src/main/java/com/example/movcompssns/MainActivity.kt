package com.example.movcompssns

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                if (result.data != null){
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }
    val contenidoIntentImplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == RESULT_OK){
                if (result.data != null){
                    if(result.data!!.data != null){
                        //Cuando se esta seguro de que el dato no es nulo usar !!
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn", "Telefono ${telefono}")
                    }
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
        val botonIntentImplicit = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicit
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener{
                abrirActividadConParametros(CIntentExplicitParametros::class.java)
            }

        val botonCrudEntrenador = findViewById<Button>(R.id.btn_sqlite)
        botonIntent
            .setOnClickListener{
                irActividad(ECrudEntrenador::class.java)
            }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView
            .setOnClickListener{
                irActividad(GRecyclerView::class.java)
            }

        val botonGmaps = findViewById<Button>(R.id.btn_google_maps)
        botonGmaps
            .setOnClickListener{
                irActividad(HGoogleMaps::class.java)
            }
        val botonFAuth = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFAuth
            .setOnClickListener{
                irActividad(IFirebaseAuth::class.java)
            }
        val botonFiretore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFiretore
            .setOnClickListener{
                irActividad(JFirebaseFirestore::class.java)
            }
    }

    //launch para parametros
    fun abrirActividadConParametros(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar paremetros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Sergio")
        intentExplicito.putExtra("apellido", "Naranjo")
        intentExplicito.putExtra("edad", 23)
        intentExplicito.putExtra("entrenador",
            BEntrenador(
                1,
                "ash",
                "pueblo paleta"
            )
        )

        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}