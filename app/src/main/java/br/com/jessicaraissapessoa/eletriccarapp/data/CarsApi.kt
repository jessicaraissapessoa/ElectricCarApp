package br.com.jessicaraissapessoa.eletriccarapp.data

import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {

    @GET("cars.json") //O endpoint
    fun getAllCars() : Call<List<Carro>> // : Retorno (Lista de Carro)
}