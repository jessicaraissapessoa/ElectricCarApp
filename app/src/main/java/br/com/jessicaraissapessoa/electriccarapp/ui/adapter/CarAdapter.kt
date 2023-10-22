package br.com.jessicaraissapessoa.electriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.electriccarapp.domain.Carro
import br.com.jessicaraissapessoa.electriccarapp.R

class CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen : Boolean = false) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemLister : (Carro) -> Unit = {} //Com isso a gente consegue acessar ele lá no Fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Cria uma nova view
        //Onde colocamos a implementação para recuperar o layout que criamos
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Pega o conteúdo da view e troca pela informação de item de uma lista
        holder.preco.text = carros[position].preco
        holder.bateria.text = carros[position].bateria
        holder.potencia.text = carros[position].potencia
        holder.recarga.text = carros[position].recarga
        if (isFavoriteScreen) {
            holder.favorito.setImageResource(R.drawable.ic_star_selected)
        }
        holder.favorito.setOnClickListener{
            val carro = carros[position]
            carItemLister(carro) //Passando o carro que a gente clicou

            setupFavorite(carro, holder)
        }
    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite //Quando clica, se é false vira true, se é true vira false

        if (carro.isFavorite) {
            holder.favorito.setImageResource(R.drawable.ic_star_selected)
        } else {
            holder.favorito.setImageResource(R.drawable.ic_star)
        }
    }

    override fun getItemCount(): Int = carros.size //Pega a quantidade de carros da lista

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //vai pegar cada item e botar na tela, gerenciando isso para nós
        val preco : TextView
        val bateria : TextView
        val potencia : TextView
        val recarga : TextView
        val favorito : ImageView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
                favorito = findViewById(R.id.iv_favorite)
            }
        }
    }
}