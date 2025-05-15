package com.example.gradeup.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubjectEntity::class], version = 1)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun subjectDAO(): SubjectDAO

    companion object {
        private lateinit var instance: SubjectDatabase
        private const val DATABASE_NAME = "books_db"

        fun getDatabase(context: Context): SubjectDatabase {
            if (!::instance.isInitialized){
                synchronized(this){ // nunca vai haver acesso simultaneo
                    instance = Room.databaseBuilder(context, SubjectDatabase::class.java, DATABASE_NAME)
                        .allowMainThreadQueries() // Permite usar a thread principal da aplicação para executar consultas
                        .build()
                }
            }
            return instance
        }
    }

}