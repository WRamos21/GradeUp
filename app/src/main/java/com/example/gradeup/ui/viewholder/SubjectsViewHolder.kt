package com.example.gradeup.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.databinding.ItemSubjectBinding

class SubjectsViewHolder(private val item: ItemSubjectBinding): RecyclerView.ViewHolder(item.root) {

    fun bind(subject: SubjectModel){
        item.textviewTitle.text = subject.disciplina
    }
}