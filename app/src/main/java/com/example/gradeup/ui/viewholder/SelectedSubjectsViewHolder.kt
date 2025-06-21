package com.example.gradeup.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.databinding.ItemScheduleBinding

class SelectedSubjectsViewHolder(
    private val item: ItemScheduleBinding,
    private val onItemLongClick: (SelectedSubjectEntity, Int) -> Unit,
    private val day: String
) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(subject: SelectedSubjectEntity) {


        // Observaçao de click longo ao subject
        setClickSubjectListener(subject)

        // Atribuiçao de dados
        setTextOnLayout(subject)

        val schedule = when (day){
            "SEGUNDA" -> subject.segunda
            "TERCA" -> subject.terca
            "QUARTA" -> subject.quarta
            "QUINTA" -> subject.quinta
            "SEXTA" -> subject.sexta
            "SABADO" -> subject.sabado
            else -> ""
        }
        val periodicity = schedule.substring(0, 3)
        val timeStart = schedule.substring(4, 9)
        val timeEnd = schedule.substring(12, 17)
        val typeClass = schedule.last().toString()

        item.textTimeStart.text = timeStart
        item.textTimeEnd.text = timeEnd

        //Tramento de visbilidade
        toggleVisbility(subject)
    }

    private fun toggleVisbility(subject: SelectedSubjectEntity){
        if (subject.docenteTeoria == "0" && subject.teoria == "") {
            item.textTheoryTeacher.visibility = View.GONE
            item.textSchedulesTheory.visibility = View.GONE
            item.textTheory.visibility = View.GONE
            item.iconTheory.visibility = View.GONE

        } else {
            item.textTheoryTeacher.visibility = View.VISIBLE
            item.textSchedulesTheory.visibility = View.VISIBLE
            item.textTheory.visibility = View.VISIBLE
            item.iconTheory.visibility = View.VISIBLE
        }

        if (subject.docentePratica == "0" && subject.pratica == "") {
            item.textPraticeTeacher.visibility = View.GONE
            item.textSchedulesPratice.visibility = View.GONE
            item.textPratice.visibility = View.GONE
            item.iconPratice.visibility = View.GONE
        } else {
            item.textPraticeTeacher.visibility = View.VISIBLE
            item.textSchedulesPratice.visibility = View.VISIBLE
            item.textPratice.visibility = View.VISIBLE
            item.iconPratice.visibility = View.VISIBLE
        }

    }

    private fun setClickSubjectListener(subject: SelectedSubjectEntity){
        item.root.setOnLongClickListener() { //Quando clicar no item
            onItemLongClick(
                subject,
                adapterPosition
            ) //Subject é o dado e adpterPosition é a posição do item clicado
            true //True Indica que o envento de click foi consumido
        }

    }

    private fun setTextOnLayout(subject: SelectedSubjectEntity){
        item.textTitleSubject.text = subject.disciplina
        item.textCampus.text = buildString {
            append(subject.turmaCodigo)
            append(" | ")
            append(subject.turno)
            append(" | ")
            append(subject.campus)
        }
        item.textCourse.text = subject.curso
        item.textVacancies.text = subject.vagasTotais.toString()

        item.textTheoryTeacher.text = (subject.docenteTeoria).replace("0", "")
        item.textSchedulesTheory.text = subject.teoria.replace("; ", "\n")
        item.textPraticeTeacher.text = (subject.docentePratica).replace("0", "")
        item.textSchedulesPratice.text = subject.pratica.replace("; ", "\n")
    }

}