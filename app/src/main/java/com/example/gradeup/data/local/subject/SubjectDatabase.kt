package com.example.gradeup.data.local.subject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SubjectEntity::class], version = 1)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun subjectDAO(): SubjectDAO

    companion object {
        private lateinit var instance: SubjectDatabase
        private const val DATABASE_NAME = "subjects_db"

        fun getDatabase(context: Context): SubjectDatabase {
            if (!Companion::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, SubjectDatabase::class.java, DATABASE_NAME)
                            .addCallback(DataBaseCallBack(context))
                            .build()
                }
            }
            return instance
        }
    }

    private class DataBaseCallBack(val context: Context) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                getDatabase(context).subjectDAO()
            }
        }
    }
}