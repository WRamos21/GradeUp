package com.example.gradeup.data.repository

import android.content.Context
import android.util.Log
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectDatabase
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.data.local.subject.SubjectEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SelectedSubjectRepository(val context: Context) {

    private var localDataBase = SelectedSubjectDatabase.getDatabase(context).selectedSubjectDAO()

    suspend fun selectSubject(subject: SubjectEntity){
        localDataBase.selectSubject(subject.toSelectedSubjectEntity())
    }

    fun getAllSelectSubject(): Flow<List<SelectedSubjectEntity>> {
        return localDataBase.getAllSelectSubject()
    }

    fun getSelectSubjectWithDayWeek(dayWeek: String): Flow<List<SelectedSubjectEntity>> {
        val list =  localDataBase.getSelectSubjectWithDayWeek(dayWeek)
        CoroutineScope(Dispatchers.IO).launch {
            list.first().let { entities ->
                Log.d("Repository", "Primeira emissão: ${entities.size} itens:")
                entities.forEach { entity ->
                    val nome = entity.disciplina
                    Log.d("Repository", "Item: $nome")
                }
            }
        }
        return list
    }


    private fun SubjectEntity.toSelectedSubjectEntity(): SelectedSubjectEntity{
        return SelectedSubjectEntity(
            codigo = this.codigo,
            turmaCodigo = this.turmaCodigo,
            curso = this.curso,
            disciplina = this.disciplina,
            teoria = this.teoria,
            pratica = this.pratica,
            campus = this.campus,
            turno = this.turno,
            tpi = this.tpi,
            vagasTotais = this.vagasTotais,
            vagasIngressantes = this.vagasIngressantes,
            vagasVeteranos = this.vagasVeteranos,
            docenteTeoria = this.docenteTeoria,
            docentePratica = this.docentePratica,
            segunda = this.segunda,
            terca = this.terca,
            quarta = this.quarta,
            quinta = this.quinta,
            sexta = this.sexta,
            sabado = this.sabado

        )
    }
}