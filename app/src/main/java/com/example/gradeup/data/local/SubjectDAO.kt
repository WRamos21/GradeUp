package com.example.gradeup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDAO {

    @Query("SELECT * FROM Subject")
    fun getAllSubject(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM Subject " +
            "WHERE LOWER(disciplina) LIKE LOWER('%' || :filter || '%')" +
            "OR LOWER(turmaCodigo) LIKE LOWER('%' || :filter || '%')" +
            "OR LOWER(turmaCodigo || ' ' || disciplina) LIKE LOWER('%' || :filter || '%')" +
            "OR LOWER(disciplina || ' ' || turmaCodigo) LIKE LOWER('%' || :filter || '%')")
    fun getFilteredSubject(filter: String): Flow<List<SubjectEntity>>

    @Insert
    suspend fun create(subjects: List<SubjectEntity>)

    @Query("SELECT COUNT(*) FROM Subject")
    suspend fun getCount(): Int

}