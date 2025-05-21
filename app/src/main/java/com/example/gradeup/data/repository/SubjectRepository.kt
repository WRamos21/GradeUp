package com.example.gradeup.data.repository

import android.content.Context
import com.example.gradeup.R
import com.example.gradeup.data.constants.constants
import com.example.gradeup.data.local.SubjectDatabase
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.APIListener
import com.example.gradeup.data.remote.RetrofitClient
import com.example.gradeup.data.remote.SubjectService
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectRepository(val context: Context) {

    // Recebimento dos dados de maneira assincrona
    private val remote = RetrofitClient.createService(SubjectService::class.java)

    fun getAllSubjects(listener: APIListener<List<SubjectModel>>) {
        val call: Call<List<SubjectModel>> = remote.listAllSubjects()

        call.enqueue(object : Callback<List<SubjectModel>> {
            override fun onResponse(
                call: Call<List<SubjectModel>>,
                response: Response<List<SubjectModel>>
            ) {
                if (response.code() == constants.HTTP.SUCCESS_CODE) {
                    val list = response.body()
                    response.body()?.let { listener.onSucces(it) }
                } else {
                    listener.onFailure(jsonToString(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

//    fun getFilteredSubjects(
//        filters: Map<String, String>,
//        listener: APIListener<List<SubjectModel>>
//    ) {
//        val call: Call<List<SubjectModel>> = remote.listSubjectsWithFilter(filters)
//
//        call.enqueue(object : Callback<List<SubjectModel>> {
//            override fun onResponse(
//                call: Call<List<SubjectModel>>,
//                response: Response<List<SubjectModel>>
//            ) {
//                if (response.code() == constants.HTTP.SUCCESS_CODE) {
//                    val list = response.body()
//                    response.body()?.let { listener.onSucces(it) }
//                } else {
//                    listener.onFailure(jsonToString(response.errorBody()!!.string()))
//                }
//            }
//
//            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable) {
//                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
//            }
//        })
//    }


    private fun jsonToString(json: String): String {
        val jsonObject = JSONObject(json)
        return jsonObject.getString("message")
    }
}
