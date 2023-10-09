package br.com.jessicaraissapessoa.eletriccarapp.data.local

import android.provider.BaseColumns

object CarrosContract {
    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLLUMN_NAME_ID = "id"
        const val COLLUMN_NAME_PRECO = "preco"
        const val COLLUMN_NAME_BATERIA = "bateria"
        const val COLLUMN_NAME_POTENCIA = "potencia"
        const val COLLUMN_NAME_RACARGA = "recarga"
        const val COLLUMN_NAME_URL_PHOTO = "url_photo"
    }
}