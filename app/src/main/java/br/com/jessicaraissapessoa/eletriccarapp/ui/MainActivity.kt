package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "CREATE")

        setContentView(R.layout.activity_main)

        setupView()
        setupTabs()
    }

    fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
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
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //Sobrescrita do método onPageSelected() garantindo que quando for para uma tab, a tab respectiva fique selecionada
                //Faz funcionar corretamente transição entre fragments com scroll esquerda-direita
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
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