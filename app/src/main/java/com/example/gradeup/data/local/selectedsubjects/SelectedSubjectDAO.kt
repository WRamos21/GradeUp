package com.example.gradeup.data.local.selectedsubjects

import androidx.room.Dao
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
    suspend fun selectSubject(selected: SelectedSubjectEntity)

    @Query("SELECT * FROM SelectedSubject")
    fun getAllSelectSubject(): Flow<List<SelectedSubjectEntity>>

}