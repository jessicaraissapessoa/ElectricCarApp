package br.com.jessicaraissapessoa.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var preco : EditText
    lateinit var btnCalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preco = findViewById(R.id.et_preco_kwh)
        btnCalcular = findViewById(R.id.btn_calcular)

        setupViews()
        setupListeners()

    }

    fun setupViews() {
        preco = findViewById(R.id.et_preco_kwh)
        btnCalcular = findViewById(R.id.btn_calcular)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            val textoDitado = preco.text
            Log.d("texto digitado ->", textoDitado.toString())
        }
    }
}