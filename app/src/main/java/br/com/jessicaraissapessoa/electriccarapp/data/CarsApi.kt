package br.com.jessicaraissapessoa.electriccarapp.data

import br.com.jessicaraissapessoa.electriccarapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {

    @GET("cars.json") //O endpoint
    fun getAllCars() : Call<List<Carro>> // : Retorno (Lista de Carro)
}