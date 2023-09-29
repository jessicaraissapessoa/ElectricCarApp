package br.com.jessicaraissapessoa.eletriccarapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var preco : EditText
    lateinit var kmpercorrido : EditText
    lateinit var btnCalcular : Button
    lateinit var resultado : TextView

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
        kmpercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    fun calcular() {

        val preco = preco.text.toString().toFloat()
        val km = kmpercorrido.text.toString().toFloat()

        val result = preco / km
        resultado.text = result.toString()

        Log.d("Preço -> ", preco.toString())
        Log.d("Km percorrido -> ", km.toString())
        Log.d("Resultado -> ", resultado.toString())
    }

}