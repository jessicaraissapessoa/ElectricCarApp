package br.com.jessicaraissapessoa.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular : Button
    lateinit var listaCarros : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupListeners()
        setupList()
    }

    fun setupView() {
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    fun setupList() {
        var dados = arrayOf(
            "Cupcake", "Donut", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean"
        )
        val adapter = CarAdapter(dados)

        //listaCarros.layoutManager = LinearLayoutManager(this) aplicamos no recyclerView em activity_main.xml
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }

}