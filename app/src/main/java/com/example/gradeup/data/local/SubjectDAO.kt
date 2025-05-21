package com.example.gradeup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubjectDAO {

    @Query("SELECT * FROM Subject")
    fun getAllSubject()

    @Insert
    fun create(subjects: List<SubjectEntity>)

}