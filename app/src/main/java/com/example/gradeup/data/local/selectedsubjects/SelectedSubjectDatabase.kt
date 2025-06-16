package com.example.gradeup.data.local.selectedsubjects

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gradeup.data.local.subject.SubjectDatabase
import com.example.gradeup.data.local.subject.SubjectEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SelectedSubjectEntity::class], version = 1)
abstract class SelectedSubjectDatabase :
    RoomDatabase() { //abstract pois o metodos são cirados pela Room

    abstract fun selectedSubjectDAO(): SelectedSubjectDAO

    companion object { //define membros da classe e não apenas de suas instancias, get dataBase fica aqui para ser acessivel sem instanciar e garantir sigleton
        private lateinit var instance: SelectedSubjectDatabase
        private const val DATABASE_NAME = "selected_subjects_db"

        fun getDatabase(context: Context): SelectedSubjectDatabase {
            if (!Companion::instance.isInitialized) {
                synchronized(this) { //impede que varias thread acessem esse bloco de codigo mutuamente, this se refere ao companion object (Somente uma thread por vez pode entrar aqui, usando o companion como chave da porta. lembrando que o companion é unico para a classe (a chave de entrada é ocompanion que é unica))
                    instance =
                        Room.databaseBuilder(
                            context,
                            SelectedSubjectDatabase::class.java,
                            DATABASE_NAME
                        ) //Cria um builder usando context para acessar o sistema de arquivos do android
                            .addCallback(DataBaseCallBack(context)) //adiciona callback personalizado
                            .build()
                }
            }
            return instance
        }
    }

    private class DataBaseCallBack(val context: Context) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) { //quando o banco é criado pela primeira vez
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                instance?.let { database -> //database é o prametro que armazena instance dentro da funcao lambda
                    val dao = database.selectedSubjectDAO()
                    dao.create(getInitialSubjects())

                }
            }
        }

        private fun getInitialSubjects(): List<SelectedSubjectEntity> {
            return listOf(
                SelectedSubjectEntity(
                    codigo = "NA1NHZ6001-18SA",
                    turmaCodigo = "A1",
                    curso = "BACHARELADO EM BIOTECNOLOGIA",
                    disciplina = "FUNDAMENTOS DA BIOTECNOLOGIA",
                    teoria = "terça das 21:00 às 23:00, semanal",
                    pratica = "",
                    campus = "SA",
                    turno = "Noturno",
                    tpi = "2-0-2",
                    vagasTotais = 45,
                    vagasIngressantes = 0,
                    vagasVeteranos = 45,
                    docenteTeoria = "Danilo Trabuco Do Amaral",
                    docentePratica = ""
                ),
                SelectedSubjectEntity(
                    codigo = "DA1ESTU039-17SA",
                    turmaCodigo = "A1",
                    curso = "BACHARELADO EM ENGENHARIA AMBIENTAL E URBANA",
                    disciplina = "REGULAÇÃO AMBIENTAL E URBANÍSTICA",
                    teoria = "segunda das 10:00 às 12:00, semanal",
                    pratica = "",
                    campus = "SA",
                    turno = "Matutino",
                    tpi = "2-0-4",
                    vagasTotais = 60,
                    vagasIngressantes = 0,
                    vagasVeteranos = 60,
                    docenteTeoria = "Paula Ciminelli Ramalho",
                    docentePratica = ""
                )
            )
        }
    }


}