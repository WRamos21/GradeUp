package com.example.gradeup.data.remote

import com.example.gradeup.BuildConfig
import com.example.gradeup.data.model.SubjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface SubjectService {

    @Headers("apikey: ${BuildConfig.apiKeySafe}")
    @GET("turmas")
    fun listAllSubjects(): Call<List<SubjectModel>>

}