package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.data.CarFactory
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular : Button
    lateinit var listaCarros : RecyclerView
    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "CREATE")

        setContentView(R.layout.activity_main)

        setupView()
        setupListeners()
        setupList()
    }

    fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    fun setupTabs() {
        val tabsAdapter = TabAdapter(this)
        viewPager.adapter = tabsAdapter
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

    //LOGS:
    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "RESUME")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "START")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "DESTROY")
    }

}