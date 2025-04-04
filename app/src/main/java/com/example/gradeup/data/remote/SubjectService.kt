package com.example.gradeup.data.remote

import com.example.gradeup.data.model.SubjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface SubjectService {

    @Headers("apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp1a21reWFlb3lsYmJwaXl2Zm1pIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIxNTk0NTcsImV4cCI6MjA1NzczNTQ1N30.DMYpxP-rYP8DgE3-K-ZmEJPqlcxKSyWj2Cr3OcGgpiM")
    @GET("turmas")
    fun list(): Call<List<SubjectModel>>

}