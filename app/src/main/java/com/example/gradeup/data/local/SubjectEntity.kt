package com.example.gradeup.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subject")
class SubjectEntity (

    @PrimaryKey
    @ColumnInfo(name="codigo")
    var codigo: String,

    @ColumnInfo(name="turmaCodigo")
    var turmaCodigo: String,

    @ColumnInfo(name="curso")
    var curso: String,

    @ColumnInfo(name="disciplina")
    var disciplina: String,

    @ColumnInfo(name="teoria")
    var teoria: String,

    @ColumnInfo(name="pratica")
    var pratica: String,

    @ColumnInfo(name="campus")
    var campus: String,

    @ColumnInfo(name="turno")
    var turno: String,

    @ColumnInfo(name="tpi")
    var tpi: String,

    @ColumnInfo(name="vagasTotais")
    var vagasTotais: Int,

    @ColumnInfo(name="vagasIngressantes")
    var vagasIngressantes: Int,

    @ColumnInfo(name="vagasVeteranos")
    var vagasVeteranos: Int,

    @ColumnInfo(name="docenteTeoria")
    var docenteTeoria: String,

    @ColumnInfo(name="docentePratica")
    var docentePratica: String,

)