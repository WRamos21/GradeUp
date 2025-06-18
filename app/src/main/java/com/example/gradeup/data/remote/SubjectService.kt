package com.example.gradeup.data.remote

import com.example.gradeup.BuildConfig
import com.example.gradeup.data.model.SubjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SubjectService {

    @Headers("apikey: ${BuildConfig.SUPABASE_KEY_ANON}")
    @GET("turmas_duplicate")
    fun listAllSubjects(): Call<List<SubjectModel>>
}