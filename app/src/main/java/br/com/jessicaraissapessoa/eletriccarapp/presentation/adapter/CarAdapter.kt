package br.com.jessicaraissapessoa.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R

class CarAdapter(private val carros: Array<String>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Cria uma nova view
        //Onde colocamos a implementação para recuperar o layout que criamos
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Pega o conteúdo da view e troca pela informação de item de uma lista
        holder.textView.text = carros[position]
    }

    override fun getItemCount(): Int = carros.size //Pega a quantidade de carros da lista

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //vai pegar cada item e botar na tela, gerenciando isso para nós
        val textView : TextView
        init {
            textView = view.findViewById(R.id.tv_preco_value)
        }
    }
}