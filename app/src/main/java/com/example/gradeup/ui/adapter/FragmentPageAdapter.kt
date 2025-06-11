package com.example.gradeup.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gradeup.ui.schedule.DayFragment

class FragmentPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val dayWeek = listOf("SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA", "SABADO")

    // FragmentStateAdapter que se encarrega de chamar essa função automaticamente.
    override fun getItemCount() = dayWeek.size

    // FragmentStateAdapter que se encarrega de chamar essa função automaticamente.
    override fun createFragment(position: Int): Fragment {
        return DayFragment.newInstance(dayWeek[position])
    }

    fun getDayName(position: Int): String {
        return when (dayWeek[position]) {
            "SEGUNDA" -> "SEG"
            "TERCA" -> "TER"
            "QUARTA" -> "QUA"
            "QUINTA" -> "QUI"
            "SEXTA" -> "SEX"
            "SABADO" -> "SAB"
            else -> ""
        }
    }
}