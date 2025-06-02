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
    private var _selectedChips = MutableStateFlow<Set<String>>(emptySet())
    val selectedChips: StateFlow<Set<String>> get() = _selectedChips

    init {
        getChips()
    }

    fun saveSelectedChips(list: List<String>) {
        viewModelScope.launch {
            _selectedChips = MutableStateFlow(list.toSet())
            prefManager.saveSelectedChips(_selectedChips.value)
        }
    }

    private fun getChips() {
        viewModelScope.launch {
            prefManager.getSelectedChips().collect { chips ->
                _selectedChips.value = chips
            }
        }
    }
}

