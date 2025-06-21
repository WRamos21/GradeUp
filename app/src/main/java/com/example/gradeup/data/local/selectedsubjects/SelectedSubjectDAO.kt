package com.example.gradeup.data.local.selectedsubjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gradeup.data.local.subject.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedSubjectDAO {

    @Insert
    suspend fun create(selected: List<SelectedSubjectEntity>)

    @Query("SELECT COUNT(*) FROM SelectedSubject")
    suspend fun getCount(): Int

    @Insert
    suspend fun selectSubject(selecteds: List<SelectedSubjectEntity>)

    @Query("SELECT * FROM SelectedSubject")
    fun getAllSelectSubject(): Flow<List<SelectedSubjectEntity>>

    @Query(
        """
    SELECT * FROM SelectedSubject 
    WHERE CASE 
        WHEN :dayWeek = 'SEGUNDA' THEN segunda IS NOT NULL AND segunda != ''
        WHEN :dayWeek = 'TERCA' THEN terca IS NOT NULL AND terca != ''
        WHEN :dayWeek = 'QUARTA' THEN quarta IS NOT NULL AND quarta != ''
        WHEN :dayWeek = 'QUINTA' THEN quinta IS NOT NULL AND quinta != ''
        WHEN :dayWeek = 'SEXTA' THEN sexta IS NOT NULL AND sexta != ''
        WHEN :dayWeek = 'SABADO' THEN sabado IS NOT NULL AND sabado != ''
        ELSE 0
    END
"""
    )
    fun getSelectSubjectWithDayWeek(dayWeek: String): Flow<List<SelectedSubjectEntity>>

    @Query("DELETE FROM selectedsubject WHERE codigo = :subjectCodigo ")
    suspend fun deselectSubject(subjectCodigo: String)

}