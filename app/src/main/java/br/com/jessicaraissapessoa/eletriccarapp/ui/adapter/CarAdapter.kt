package br.com.jessicaraissapessoa.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro

class CarAdapter(private val carros: List<Carro>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
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
    }

    override fun getItemCount(): Int = carros.size //Pega a quantidade de carros da lista

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //vai pegar cada item e botar na tela, gerenciando isso para nós
        val preco : TextView
        val bateria : TextView
        val potencia : TextView
        val recarga : TextView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
            }
        }
    }
}