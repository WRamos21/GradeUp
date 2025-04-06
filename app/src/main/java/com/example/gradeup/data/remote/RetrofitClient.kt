package com.example.gradeup.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    // Singleton de intancia para Retrofit
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://zukmkyaeoylbbpiyvfmi.supabase.co/rest/v1/"

        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        // Criação generica de service
        fun <S>createService(serviceClass: Class<S>): S{
            return getRetrofitInstance().create(serviceClass)
        }
    }
}