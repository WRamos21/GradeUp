package com.example.gradeup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradeup.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilterViewModel(application: Application) : AndroidViewModel(application) {
    private val prefManager = PreferencesManager(application)
    private var _selectedChips = MutableStateFlow<Set<Int>>(emptySet())
    val selectedChips: StateFlow<Set<Int>> get() = _selectedChips

    init {
        getChips()
    }

    fun saveSelectedChips(list: List<Int>) {
        viewModelScope.launch {
            _selectedChips = MutableStateFlow(list.toSet())
            prefManager.saveSelectedChips(_selectedChips.value.map { it.toString() }.toSet())
        }
    }

    private fun getChips() {
        viewModelScope.launch {
            prefManager.getSelectedChips().collect { chips ->
                _selectedChips.value = chips.mapNotNull { it.toIntOrNull() }.toSet()
            }
        }
    }
}

