package com.example.gradeup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val diasSemana = listOf("SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA", "SABADO")

    // FragmentStateAdapter que se encarrega de chamar essa função automaticamente.
    override fun getItemCount() = diasSemana.size

    // FragmentStateAdapter que se encarrega de chamar essa função automaticamente.
    override fun createFragment(position: Int): Fragment {
        return DayFragment.newInstance(diasSemana[position])
    }

    fun getDiaNome(position: Int): String {
        return when (diasSemana[position]) {
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