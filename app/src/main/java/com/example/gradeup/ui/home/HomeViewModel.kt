package com.example.gradeup.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.APIListener
import com.example.gradeup.data.repository.SubjectRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SubjectRepository = SubjectRepository(application.applicationContext)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllSubjects(){
        repository.getAllSubjects(object : APIListener<List<SubjectModel>> {
            override fun onSucces(result: List<SubjectModel>) {
            }

            override fun onFailure(messageError: String) {
            }

        })
    }
}