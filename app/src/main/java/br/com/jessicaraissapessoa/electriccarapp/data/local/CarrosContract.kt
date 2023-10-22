package br.com.jessicaraissapessoa.electriccarapp.data.local

import android.provider.BaseColumns

object CarrosContract {

    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLLUMN_NAME_CAR_ID = "car_id"
        const val COLLUMN_NAME_PRECO = "preco"
        const val COLLUMN_NAME_BATERIA = "bateria"
        const val COLLUMN_NAME_POTENCIA = "potencia"
        const val COLLUMN_NAME_RACARGA = "recarga"
        const val COLLUMN_NAME_URL_PHOTO = "url_photo"
    }

    //Estrutura do banco
    const val TABLE_CAR =
        "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${CarEntry.COLLUMN_NAME_CAR_ID} TEXT," +
                "${CarEntry.COLLUMN_NAME_PRECO} TEXT," +
                "${CarEntry.COLLUMN_NAME_BATERIA} TEXT," +
                "${CarEntry.COLLUMN_NAME_POTENCIA} TEXT," +
                "${CarEntry.COLLUMN_NAME_RACARGA} TEXT," +
                "${CarEntry.COLLUMN_NAME_URL_PHOTO} TEXT)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}