package br.com.jessicaraissapessoa.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var preco : EditText
    lateinit var kmpercorrido : EditText
    lateinit var btnCalcular : Button
    lateinit var resultado : TextView

    //Os outros que não estão na aula:
    lateinit var radioGroup : RadioGroup
    lateinit var checkBox1 : CheckBox
    lateinit var checkBox2 : CheckBox
    lateinit var switch : Switch


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

        //Os outros que não estão na aula:
        radioGroup = findViewById(R.id.rg_comida_favorita)
        checkBox1 = findViewById(R.id.cb_compromisso_1)
        checkBox2 = findViewById(R.id.cb_compromisso_2)
        switch = findViewById(R.id.s_lampada)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }

        //Extra:
        if (checkBox1.isChecked) Log.d("compromisso", " 1 feito")
        else Log.d("Compromisso", " 2 não feito")

        //Extra:
        if (checkBox2.isChecked) Log.d("compromisso", " 2 feito")
        else Log.d("Compromisso", " 2 não feito")

        //Extra:
        radioGroup.setOnCheckedChangeListener { _, checkedId : Int ->
            val radio = findViewById<RadioButton>(checkedId)
            Log.d("Opção selecionada ->", radio.text.toString())
        }

        //Extra:
        switch.setOnCheckedChangeListener { _, isChecked : Boolean ->
            if (isChecked) {
                Log.d("Interruptor ->", "Ligado $isChecked")
            }
            else Log.d("Interruptor ->", "Ligado $isChecked")
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