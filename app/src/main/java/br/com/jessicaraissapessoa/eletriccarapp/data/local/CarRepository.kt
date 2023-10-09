package br.com.jessicaraissapessoa.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro

class CarRepository (private val context: Context) {

    fun save(carro: Carro): Boolean {

        var isSaved = false

        try {
            val dbHelper = CarsDbHelper(context) //Instanciando e pegando o contexto
            val db = dbHelper.writableDatabase //Abrindo uma forma de escrever no banco de dados

            //Dados que vão popular o carro que vai ser passado para esse método (saveOnDataBase)
            val values = ContentValues().apply {
                put(CarrosContract.CarEntry.COLLUMN_NAME_PRECO, carro.preco)
                put(CarrosContract.CarEntry.COLLUMN_NAME_BATERIA, carro.bateria)
                put(CarrosContract.CarEntry.COLLUMN_NAME_POTENCIA, carro.potencia)
                put(CarrosContract.CarEntry.COLLUMN_NAME_RACARGA, carro.recarga)
                put(CarrosContract.CarEntry.COLLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }

            val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values) //Salvando informações

            if (inserted != null) {
                isSaved = true
            }

        } catch (ex : Exception) {
            ex.message?.let {
                Log.e("Erro ao inserir ->", it)
            }
        }

        return isSaved
    }
}