package com.example.movcompssns

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movcompssns.databinding.ActivityAcicloVidaBinding

class ACicloVida : AppCompatActivity() {

    var textoGlobal = ""
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAcicloVidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //mostrar
        mostrarSnackbar("OnCreate")
    }//Final bloque codigo  onCreate

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("OnStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("OnResume")
    }
    override fun onRestart() {
        super.onResume()
        mostrarSnackbar("OnRestart")
    }
    override fun onPause() {
        super.onResume()
        mostrarSnackbar("OnPause")
    }
    override fun onStop() {
        super.onResume()
        mostrarSnackbar("OnPause")
    }
    override fun onDestroy() {
        super.onResume()
        mostrarSnackbar("OnPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //gUardar variables
            //Primitivos
            putString("textoGuardado", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")
        //val textoRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if(textoRecuperado != null){
            mostrarSnackbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun mostrarSnackbar(texto:String){
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
            textoGlobal, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}