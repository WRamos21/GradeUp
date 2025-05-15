package com.example.gradeup.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.APIListener
import com.example.gradeup.data.repository.SubjectRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SubjectRepository = SubjectRepository(application.applicationContext)

    private val _subjects = MutableLiveData<List<SubjectModel>>()
    val subjects: LiveData<List<SubjectModel>> = _subjects

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getAllSubjects(){
        repository.getAllSubjects(object : APIListener<List<SubjectModel>> {
            override fun onSucces(result: List<SubjectModel>) {
                _subjects.value = result
            }

            override fun onFailure(messageError: String) {
                _errorMessage.value = messageError
            }
        })

    }

//    fun getSubjects(filter: String){
//        repository.getFilteredSubjects(filters = mapOf("disciplina" to "ilike.*%$filter*"), object : APIListener<List<SubjectModel>> {
//            override fun onSucces(result: List<SubjectModel>) {
//                _subjects.value = result
//            }
//
//            override fun onFailure(messageError: String) {
//                _errorMessage.value = messageError
//            }
//        })
//    }
}