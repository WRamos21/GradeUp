package com.example.gradeup.data.repository

import android.content.Context
import android.util.Log
import com.example.gradeup.R
import com.example.gradeup.data.PreferencesManager
import com.example.gradeup.data.constants.constants
import com.example.gradeup.data.local.FilterModel
import com.example.gradeup.data.local.subject.SubjectDatabase
import com.example.gradeup.data.local.subject.SubjectEntity
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

class SubjectRepository(
    val context: Context,
    private var selectedRepo: SelectedSubjectRepository? = null
) {

    //Cria singleton para instanciar SelectedRepository enviando a instancia desse SubjectRepository
    private val selectedRepository by lazy {
        selectedRepo ?: SelectedSubjectRepository(context, this)
    }

    private val filter = FilterModel()
    private val prefManager = PreferencesManager(context)
    private val remote = RetrofitClient.createService(SubjectService::class.java)
    private var localDataBase = SubjectDatabase.getDatabase(context).subjectDAO()

    private fun getAllSubjects(listener: APIListener<List<SubjectModel>>) {
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
        return localDataBase.getFilteredSubject(
            string,
            filter.campus,
            filter.shifts,
            filter.courses
        )
    }

    private fun createFilter() {
        CoroutineScope(Dispatchers.IO).launch {
            prefManager.getSelectedChips().collect { chips ->
                val campusChips =
                    listOf(constants.University.CAMPUS_SA, constants.University.CAMPUS_SB)
                val selectedCampus =
                    chips.filter { it in campusChips } // Reduz o chip [SA, SB, Noturno, Matutino ....] para apenas SA ou SB
                val shiftChips =
                    listOf(constants.University.SHIFT_MORNING, constants.University.SHIFT_NIGHT)
                val selectedShifts = chips.filter { it in shiftChips }
                val selectedCourses = chips.filter { it in (constants.University.LIST_COURSES) }

                filter.campus = when (selectedCampus.size) {
                    0, 2 -> listOf("ALL")  // Quando SA e SB ou nenhum dos dois, filter campus = ALL
                    else -> selectedCampus
                }

                filter.shifts = when (selectedShifts.size) {
                    0, 2 -> listOf("ALL")  // nenhum ou ambos selecionados
                    else -> selectedShifts
                }

                filter.courses = when (selectedCourses.size) {
                    0, 16 -> listOf("ALL")
                    else -> selectedCourses
                }
            }
        }
    }

    fun selectSubject(subject: SubjectEntity) {
        if (!subject.selected) {
            CoroutineScope(Dispatchers.IO).launch {
                localDataBase.toggleSelectionSubject(subject.codigo, true)
                selectedRepository.selectSubject(subject)
            }
        }
    }

    fun deselectSubject(subjectID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataBase.toggleSelectionSubject(subjectID, false)
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
