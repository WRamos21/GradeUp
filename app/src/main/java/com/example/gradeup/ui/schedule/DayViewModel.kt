package com.example.gradeup.ui.schedule

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.data.repository.RepositoryManager
import com.example.gradeup.data.repository.SelectedSubjectRepository

class DayViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SelectedSubjectRepository = RepositoryManager.getSelectedSubjectRepository()

    var selectedSubjectsList: LiveData<List<SelectedSubjectEntity>> = repository.getSelectSubjectWithDayWeek("").asLiveData()

    fun getSelectSubjectWithDayWeek(dayWeek: String){
        selectedSubjectsList = repository.getSelectSubjectWithDayWeek(dayWeek).asLiveData()
    }

    fun deselectSubject(subject: SelectedSubjectEntity) {
    repository.deselectSubject(subject)
    }

}