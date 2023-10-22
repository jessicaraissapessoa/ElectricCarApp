package br.com.jessicaraissapessoa.electriccarapp.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.jessicaraissapessoa.electriccarapp.data.local.CarrosContract.SQL_DELETE_ENTRIES
import br.com.jessicaraissapessoa.electriccarapp.data.local.CarrosContract.TABLE_CAR

class CarsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CAR) //Passando a query para criação do banco de CarrosContract
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES) //Quero que antes de alterar, ele delete as minhas entradas, a minha tabela
        onCreate(db) //E crie o novo banco
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DbCar.db"
    }

}