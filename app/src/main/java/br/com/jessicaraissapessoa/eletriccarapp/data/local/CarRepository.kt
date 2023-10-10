package br.com.jessicaraissapessoa.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_BATERIA
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_CAR_ID
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_POTENCIA
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_PRECO
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_RACARGA
import br.com.jessicaraissapessoa.eletriccarapp.data.local.CarrosContract.CarEntry.COLLUMN_NAME_URL_PHOTO
import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro

class CarRepository (private val context: Context) {

    fun save(carro: Carro): Boolean {

        var isSaved = false

        try {
            val dbHelper = CarsDbHelper(context) //Instanciando e pegando o contexto
            val db = dbHelper.writableDatabase //Abrindo uma forma de escrever no banco de dados

            //Dados que vão popular o carro que vai ser passado para esse método (saveOnDataBase)
            val values = ContentValues().apply {
                put(COLLUMN_NAME_CAR_ID, carro.id)
                put(COLLUMN_NAME_PRECO, carro.preco)
                put(COLLUMN_NAME_BATERIA, carro.bateria)
                put(COLLUMN_NAME_POTENCIA, carro.potencia)
                put(COLLUMN_NAME_RACARGA, carro.recarga)
                put(COLLUMN_NAME_URL_PHOTO, carro.urlPhoto)
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

    fun findCarById(id : Int) : Carro {
        val dbHelper = CarsDbHelper(context) //Instanciando e pegando o contexto
        val db = dbHelper.readableDatabase

        //Listagem das colunas a serem exibidas no resultado da Query:
        val columns = arrayOf(
            BaseColumns._ID,
            COLLUMN_NAME_CAR_ID,
            COLLUMN_NAME_PRECO,
            COLLUMN_NAME_BATERIA,
            COLLUMN_NAME_POTENCIA,
            COLLUMN_NAME_RACARGA,
            COLLUMN_NAME_URL_PHOTO
        )

        val filter = "$COLLUMN_NAME_CAR_ID = ?" //Filtro por ID / Quantidade de parâmetros -> ? (Informaremos depois)
        val filterValues = arrayOf(id.toString())

        val cursor = db.query( //Faz um select de SQL
            CarrosContract.CarEntry.TABLE_NAME, //Primeiro parâmetro é o nome da tabela
            columns, //as colunas a serem exibidas
            filter, //where (filtro)
            filterValues, //valor do where, substituindo o parâmetro ?
            null,
            null,
            null
        )

        val itemCar = mutableListOf<Carro>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID)) //Pegando o id do banco de dados gravado
                Log.d("ID -> ", itemId.toString())

                val preco = getString(getColumnIndexOrThrow(COLLUMN_NAME_PRECO))
                Log.d("preco -> ", preco)
            }
        }
        //Depois de percorrer esse cursor, para não utilizar recursos de memória, vamos fechar ele:
        cursor.close()

    }
}