package com.example.gradeup.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.gradeup.data.local.subject.SubjectEntity
import com.example.gradeup.data.repository.RepositoryManager
import com.example.gradeup.data.repository.SubjectRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SubjectRepository = RepositoryManager.getSubjectRepository()
    private val _filter = MutableLiveData<String>()

    var subjectsRemote: LiveData<List<SubjectEntity>> =
        _filter.switchMap { filter ->
            repository.getAllFromLocal(filter).asLiveData()
        }

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getAllSubjects(filter: String) {
        viewModelScope.launch {
            repository.updateLocalDataBase()
            _filter.value = filter
        }
    }

    fun selectSubject(subject: SubjectEntity) {

        repository.selectSubject(breakSubjectIntoIndividualSchedules(subject))
    }

    private fun breakSubjectIntoIndividualSchedules(subject: SubjectEntity): List<SubjectEntity> {
        val newListSelectedSubject = mutableListOf<SubjectEntity>()

        val dayOfWeekAndSchedule = listOf(
            "segunda" to subject.segunda,
            "terca" to subject.terca,
            "quarta" to subject.quarta,
            "quinta" to subject.quinta,
            "sexta" to subject.sexta,
            "sabado" to subject.sabado
        )

        for ((day, schedule) in dayOfWeekAndSchedule) {
            if (schedule != "") {
                val splitSchedule = schedule.split("\n")

                for (newSchedule in splitSchedule) {
                    val newSubject = subject.copy(
                        segunda = if (day == "segunda") newSchedule else "",
                        terca = if (day == "terca") newSchedule else "",
                        quarta = if (day == "quarta") newSchedule else "",
                        quinta = if (day == "quinta") newSchedule else "",
                        sexta = if (day == "sexta") newSchedule else "",
                        sabado = if (day == "sabado") newSchedule else ""
                    )
                    newListSelectedSubject.add(newSubject)
                    Log.e("Select", "${day}: ${newSchedule}")
                }
            }
        }

        if (newListSelectedSubject.size > 0) return newListSelectedSubject else newListSelectedSubject.add(subject)
        return newListSelectedSubject
    }
}