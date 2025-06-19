package com.example.gradeup.data.repository

import android.content.Context

//Object é como se fosse uma classe singleton automatica para a aplicação inteira
object RepositoryManager {
    private var subjectRepository: SubjectRepository? = null
    private var selectedSubjectRepository: SelectedSubjectRepository? = null

    fun getSubjectRepository(context: Context): SubjectRepository{
        if (subjectRepository == null){
            subjectRepository = SubjectRepository(context)
            selectedSubjectRepository = SelectedSubjectRepository(context)
        }
        return subjectRepository!!
    }

    fun getSelectedSubjectRepository(context: Context): SelectedSubjectRepository{
        if (selectedSubjectRepository == null){
            getSubjectRepository(context)
        }
        return selectedSubjectRepository!!
    }

}

