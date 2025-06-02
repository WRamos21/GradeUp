package com.example.gradeup.data.repository

import android.content.Context
import android.util.Log
import com.example.gradeup.R
import com.example.gradeup.data.PreferencesManager
import com.example.gradeup.data.constants.constants
import com.example.gradeup.data.local.FilterModel
import com.example.gradeup.data.local.SubjectDatabase
import com.example.gradeup.data.local.SubjectEntity
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.APIListener
import com.example.gradeup.data.remote.RetrofitClient
import com.example.gradeup.data.remote.SubjectService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectRepository(val context: Context) {
    private val filter = FilterModel()

    private val prefManager = PreferencesManager(context)

    private val remote = RetrofitClient.createService(SubjectService::class.java)
    private var localDataBase = SubjectDatabase.getDatabase(context).subjectDAO()

    fun getAllSubjects(listener: APIListener<List<SubjectModel>>) {
        localDataBase = SubjectDatabase.getDatabase(context).subjectDAO()
        val call: Call<List<SubjectModel>> = remote.listAllSubjects()

        call.enqueue(object : Callback<List<SubjectModel>> {
            override fun onResponse(
                call: Call<List<SubjectModel>>,
                response: Response<List<SubjectModel>>
            ) {
                if (response.code() == constants.HTTP.SUCCESS_CODE) {
                    val listSubjectModel: List<SubjectModel>? = response.body()
                    val listSubjectEntity = listSubjectModel?.map { it.toEntity() }
                    if (listSubjectEntity != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            localDataBase.create(listSubjectEntity)
                        }
                    }
                } else {
                    listener.onFailure(jsonToString(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    private fun jsonToString(json: String): String {
        val jsonObject = JSONObject(json)
        return jsonObject.getString("message")
    }

    suspend fun updateLocalDataBase() {
        if (localDataBase.getCount() == 0) {
            Log.d("DataBase", "zerado")
            getAllSubjects(object : APIListener<List<SubjectModel>> {
                override fun onSucces(result: List<SubjectModel>) {
                    Log.d("DataBase", "Valores passados")
                }

                override fun onFailure(messageError: String) {
                    Log.e("DataBase", "Erro")
                }
            })
        }
    }

    fun getAllFromLocal(string: String): Flow<List<SubjectEntity>> {
        createFilter()
        return localDataBase.getFilteredSubject(string, filter.campu, filter.turno)
    }

    fun createFilter(){
        CoroutineScope(Dispatchers.IO).launch {
            prefManager.getSelectedChips().collect { chips ->
                Log.e("TesteRepository", "Tags: ${chips}")
                if ((chips.contains("SA") && chips.contains("SB")) || (!chips.contains("SA") && !chips.contains("SB"))){
                    filter.campu = listOf("ALL")
                    Log.e("TesteRepository", "FilterCampus: ${filter.campu}")
                } else if (chips.contains("SA")){
                    filter.campu = listOf("SA")
                    Log.e("TesteRepository", "FilterCampus: ${filter.campu}")
                } else if (chips.contains("SB")){
                    filter.campu = listOf("SB")
                    Log.e("TesteRepository", "FilterCampus: ${filter.campu}")
                    }

                if ((chips.contains("Matutino") && chips.contains("Noturno")) || (!chips.contains("Matutino") && !chips.contains("Noturno"))){
                    filter.turno = listOf("ALL")
                    Log.e("TesteRepository", "FilterTurno: ${filter.turno}")
                } else if (chips.contains("Matutino")){
                    filter.turno = listOf("Matutino")
                    Log.e("TesteRepository", "FilterTurno: ${filter.turno}")
                } else if (chips.contains("Noturno")){
                    filter.turno = listOf("Noturno")
                    Log.e("TesteRepository", "FilterTurno: ${filter.turno}")
                }
            }
        }
    }

    fun SubjectModel.toEntity(): SubjectEntity {
        return SubjectEntity(
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
            docentePratica = this.docentePratica
        )
    }


}
