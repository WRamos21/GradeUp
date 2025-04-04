package com.example.gradeup.data.repository

import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.RetrofitClient
import com.example.gradeup.data.remote.SubjectService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectRepository () {

    // Recebimento dos dados de maneira assincrona
    private val remote = RetrofitClient.createService(SubjectService::class.java)
    fun getSubjects() {
        val call: Call<List<SubjectModel>> = remote.list()

        call.enqueue(object : Callback<List<SubjectModel>> {
            override fun onResponse(
                call: Call<List<SubjectModel>>,
                response: Response<List<SubjectModel>>
            ) {
                val list = response.body()
            }

            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable) {
                val s = ""
            }

        })


    }
}