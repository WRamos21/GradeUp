package com.example.gradeup.ui.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.data.repository.SelectedSubjectRepository

class DayViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SelectedSubjectRepository = SelectedSubjectRepository(application.applicationContext)

    var selectedSubjectsList: LiveData<List<SelectedSubjectEntity>> = repository.getAllSelectSubject().asLiveData()

}