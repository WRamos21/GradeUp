package com.example.gradeup

import android.app.Application
import com.example.gradeup.data.repository.RepositoryManager

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Tudo vai ser incializado uma unica vez
        RepositoryManager.initialize(this)
    }
}