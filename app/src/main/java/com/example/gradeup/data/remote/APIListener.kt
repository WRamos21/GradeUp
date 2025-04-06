package com.example.gradeup.data.remote

interface APIListener <T> {

    fun onSucces(result: T)
    fun onFailure(messageError: String)
}