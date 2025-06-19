package com.example.gradeup.data.repository

import android.content.Context
import com.example.gradeup.MyApplication

//Object é como se fosse uma classe singleton automatica para a aplicação inteira
object RepositoryManager {
    private lateinit var appContext: Context
    private var subjectRepository: SubjectRepository? = null
    private var selectedSubjectRepository: SelectedSubjectRepository? = null

    fun getSubjectRepository(): SubjectRepository{
        if (subjectRepository == null){
            subjectRepository = SubjectRepository(appContext)
            selectedSubjectRepository = SelectedSubjectRepository(appContext)
        }
        return subjectRepository!!
    }

    fun getSelectedSubjectRepository(): SelectedSubjectRepository{
        if (selectedSubjectRepository == null){
            getSubjectRepository()
        }
        return selectedSubjectRepository!!
    }

    fun initialize(myApplication: MyApplication) {
        appContext = myApplication.applicationContext
    }

}

