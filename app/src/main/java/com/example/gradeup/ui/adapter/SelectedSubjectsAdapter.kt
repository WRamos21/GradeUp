package com.example.gradeup.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.data.local.subject.SubjectEntity
import com.example.gradeup.databinding.ItemScheduleBinding
import com.example.gradeup.databinding.ItemSubjectBinding
import com.example.gradeup.ui.viewholder.SelectedSubjectsViewHolder

class SelectedSubjectsAdapter(private val onItemLongClick: (SelectedSubjectEntity, Int) -> Unit) : RecyclerView.Adapter<SelectedSubjectsViewHolder>() {
    private var selectedSubjectList: List<SelectedSubjectEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedSubjectsViewHolder {
        val view = ItemScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ) // criar um LaouteInflate com o contexto da recycler, parent diz onde o item vai ser criado, false delega a propria RecyclerView colocar o item
        return SelectedSubjectsViewHolder(view, onItemLongClick)
    }

    override fun getItemCount(): Int {
        return selectedSubjectList.size
    }

    override fun onBindViewHolder(holder: SelectedSubjectsViewHolder, position: Int) {
        holder.bind(selectedSubjectList[position])
    }

    fun updateSelectedSubjects(listCurrentData: List<SelectedSubjectEntity>) {
        selectedSubjectList = listCurrentData
        notifyDataSetChanged()
    }

}