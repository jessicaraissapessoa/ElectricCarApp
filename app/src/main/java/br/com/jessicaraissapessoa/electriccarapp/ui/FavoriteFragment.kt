package br.com.jessicaraissapessoa.electriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.electriccarapp.data.local.CarRepository
import br.com.jessicaraissapessoa.electriccarapp.domain.Carro
import br.com.jessicaraissapessoa.electriccarapp.ui.adapter.CarAdapter
import br.com.jessicaraissapessoa.electriccarapp.R

class FavoriteFragment : Fragment() {

    lateinit var listaCarrosFavoritos : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
        setupList()
    }

    private fun getCarsOnLLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()

        return carList
    }

    fun setupView(view: View) {
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }

    fun setupList() {

        val cars = getCarsOnLLocalDb()

        val carroAdapter = CarAdapter(cars, isFavoriteScreen = true)

        listaCarrosFavoritos.apply {
            isVisible = true
            adapter = carroAdapter
        }
        carroAdapter.carItemLister = {carro ->
            val deletar = CarRepository(requireContext()).delete(carro)
        }
    }

}