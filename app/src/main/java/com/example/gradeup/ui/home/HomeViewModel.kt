package com.example.gradeup.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.gradeup.data.local.SubjectEntity
import com.example.gradeup.data.repository.SubjectRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SubjectRepository = SubjectRepository(application.applicationContext)
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
}