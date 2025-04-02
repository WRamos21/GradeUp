package com.example.gradeup

import com.google.gson.annotations.SerializedName

class ClassEntity {

    @SerializedName("codigo")
    var codigo: String = ""

    @SerializedName("turma_codigo")
    var turmaCodigo: String = ""

    @SerializedName("curso")
    var curso: String = ""

    @SerializedName("disciplina")
    var disciplina: String = ""

    @SerializedName("teoria")
    var teoria: String = ""

    @SerializedName("pratica")
    var pratica: String = ""

    @SerializedName("campus")
    var campus: String = ""

    @SerializedName("turno")
    var turno: String = ""

    @SerializedName("t-p-e-i")
    var tpi: String = ""

    @SerializedName("vagas_totais")
    var vagasTotais: Int = 0

    @SerializedName("vagas_ingressantes")
    var vagasIngressantes: Int = 0

    @SerializedName("vagas_veteranos")
    var vagasVeteranos: Int = 0

    @SerializedName("docente_teoria")
    var docenteTeoria: String = ""

    @SerializedName("docente_pratica")
    var docentePratica: String = ""















}