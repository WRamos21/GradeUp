package com.example.gradeup.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SubjectEntity::class], version = 1)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun subjectDAO(): SubjectDAO

    companion object {
        private lateinit var instance: SubjectDatabase
        private const val DATABASE_NAME = "subjects_db"

        fun getDatabase(context: Context): SubjectDatabase {
            if (!::instance.isInitialized) {
                synchronized(this) { // nunca vai haver acesso simultaneo
                    instance =
                        Room.databaseBuilder(context, SubjectDatabase::class.java, DATABASE_NAME)
                            .addCallback(DataBaseCallBack(context))
                            // Permite usar a thread principal da aplicação para executar consultas
                            .build()
                }
            }
            return instance
        }
    }

    private class DataBaseCallBack(val context: Context) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                getDatabase(context).subjectDAO()
            }
        }

        private fun getInitialSubjects(): List<SubjectEntity> {
            return listOf(
                SubjectEntity(
                    curso = "BACHARELADO EM BIOTECNOLOGIA",
                    codigo = "NA1NHZ6001-18SA",
                    disciplina = "FUNDAMENTOS DA BIOTECNOLOGIA",
                    turmaCodigo = "A1",
                    teoria = "terça das 21:00 às 23:00, semanal ",
                    pratica = "",
                    campus = "SA",
                    turno = "Noturno",
                    tpi = "2-0-2",
                    vagasTotais = 45,
                    vagasIngressantes = 0,
                    vagasVeteranos = 45,
                    docenteTeoria = "Danilo Trabuco Do Amaral",
                    docentePratica = "0",
                )
            )

        }

    }

}