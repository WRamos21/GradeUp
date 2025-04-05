package com.example.gradeup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gradeup.data.repository.SubjectRepository

class HomeViewModel (private val repository: SubjectRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllSubjects(){
        repository.getSubjects()
    }
}