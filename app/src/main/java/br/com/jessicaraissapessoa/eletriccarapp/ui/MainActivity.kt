package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.data.CarFactory
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular : Button
    lateinit var listaCarros : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "CREATE")

        setContentView(R.layout.activity_main)

        setupView()
        setupListeners()
        setupList()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "RESUME")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "START")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "DESTROY")
    }

    fun setupView() {
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    fun setupList() {

        //listaCarros.layoutManager = LinearLayoutManager(this) aplicamos no recyclerView em activity_main.xml

        val adapter = CarAdapter(CarFactory.list) //Acesso direto à CarFactory por ser object
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }

}