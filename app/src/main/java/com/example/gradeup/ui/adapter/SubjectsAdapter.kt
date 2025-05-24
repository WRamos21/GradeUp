package com.example.gradeup.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gradeup.data.local.SubjectEntity
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.databinding.ItemSubjectBinding
import com.example.gradeup.ui.viewholder.SubjectsViewHolder

class SubjectsAdapter : RecyclerView.Adapter<SubjectsViewHolder>() {
    private var subjectList: List<SubjectEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        val view = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bind(subjectList[position])
    }

    fun updateSubjects(listCurrentData: List<SubjectEntity>){
        subjectList = listCurrentData
        notifyDataSetChanged()
    }
}