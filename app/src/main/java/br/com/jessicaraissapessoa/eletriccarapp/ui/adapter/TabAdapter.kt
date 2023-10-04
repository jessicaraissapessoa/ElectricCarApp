package br.com.jessicaraissapessoa.eletriccarapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.jessicaraissapessoa.eletriccarapp.ui.CarFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            1 -> CarFragment()
            2 -> CarFragment() //Temporário
            else -> CarFragment() //Temporário
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}