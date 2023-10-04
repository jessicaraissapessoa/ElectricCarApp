package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.data.CarFactory
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular : Button
    lateinit var listaCarros : RecyclerView
    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "CREATE")

        setContentView(R.layout.activity_main)

        setupView()
        setupListeners()
        setupList()
        setupTabs()
    }

    fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
        viewPager = findViewById(R.id.vp_view_pager)
    }

    fun setupTabs() {
        val tabsAdapter = TabAdapter(this)
        viewPager.adapter = tabsAdapter

        //O que ele vai fazer quando clicado:
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) { //Quando ela for selecionada
                tab?.let {//Se não for null
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { //Quando não for selecionada

            }

            override fun onTabReselected(tab: TabLayout.Tab?) { //Quando der um re-select nela

            }
        })
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