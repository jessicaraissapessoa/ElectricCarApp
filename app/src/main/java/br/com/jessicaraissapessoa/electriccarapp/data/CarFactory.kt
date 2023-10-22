package br.com.jessicaraissapessoa.electriccarapp.data

import br.com.jessicaraissapessoa.electriccarapp.domain.Carro

object CarFactory {

    val list = listOf<Carro>(
        Carro(
            id = 1,
            preco = "R$300.000,00",
            bateria = "300 kWh",
            potencia = "200cv",
            recarga = "30 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro(
            id = 2,
            preco = "R$200.000,00",
            bateria = "200 kWh",
            potencia = "150cv",
            recarga = "40 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        )
    )

}