package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.jessicaraissapessoa.eletriccarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var preco : EditText
    lateinit var kmpercorrido : EditText
    lateinit var btnCalcular : Button
    lateinit var resultado : TextView
    lateinit var btnClose : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)

        setupView()
        setupListeners()
    }

    fun setupView() {
        preco = findViewById(R.id.et_preco_kwh)
        kmpercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
        btnClose = findViewById(R.id.iv_close)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    fun calcular() {

        val preco = preco.text.toString().toFloat()
        val km = kmpercorrido.text.toString().toFloat()

        val result = preco / km
        resultado.text = result.toString()
        saveSharedPref(result)

        Log.d("Preço -> ", preco.toString())
        Log.d("Km percorrido -> ", km.toString())
        Log.d("Resultado -> ", resultado.toString())
    }

    fun saveSharedPref(resultado : Float) {
        //Falando que a nossa preferência é do escopo do app só (é privada para o uso do app):
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), resultado)
            apply()
        }
    }

}