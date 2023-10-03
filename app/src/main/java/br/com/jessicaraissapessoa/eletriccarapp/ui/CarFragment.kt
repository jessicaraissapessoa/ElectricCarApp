package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.jessicaraissapessoa.eletriccarapp.R

class CarFragment : Fragment() {

    override fun onCreateView( //Esse onCreateView é onde vai nos dar a possibilidade de retornar um layout que a gente precisa
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }
}