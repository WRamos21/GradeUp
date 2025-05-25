package com.example.gradeup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDAO {

    @Query("SELECT * FROM Subject")
    fun getAllSubject(): Flow<List<SubjectEntity>>

    @Insert
    suspend fun create(subjects: List<SubjectEntity>)

    // Teste simples primeiro
    @Query("SELECT codigo FROM Subject LIMIT 1")
    suspend fun getFirstCode(): String?

    @Query("SELECT COUNT(*) FROM Subject")
    suspend fun getCount(): Int

}