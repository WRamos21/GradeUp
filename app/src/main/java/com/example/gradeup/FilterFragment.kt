package com.example.gradeup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.gradeup.databinding.FragmentFilterBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val filterViewModel: FilterViewModel by activityViewModels()
    private var listChipsCheked = mutableListOf("")
    private val chipsMap by lazy {
        mapOf(
            binding.chipFilterDaytime.id to binding.chipFilterDaytime,
            binding.chipFilterNightly.id to binding.chipFilterNightly
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listIds = listOf(binding.chipFilterDaytime.id)

        ativarChipsPorId(listIds)

        viewLifecycleOwner.lifecycleScope.launch {
            filterViewModel.selectedChips.collect { chips ->
                Log.d("testePrintFragmentButton", "Chips observados: $chips")
                listChipsCheked = chips.toMutableList()
                if (chips.contains("SBC")) {
                    binding.chipFilterSbc.isChecked = true
                }
                if (chips.contains("SA")) {
                    binding.chipFilterSa.isChecked = true
                }
            }

        }

        binding.iconCheck.setOnClickListener {

            if (binding.chipFilterSbc.isChecked) {
                Log.e("teste.Cheked.SBC", "Lista antiga: $listChipsCheked")
                val newList = mutableListOf("SBC")
                viewLifecycleOwner.lifecycleScope.launch {
                    Log.e("teste", "Lista nova na fragment: $newList")
                    filterViewModel.saveChip(listChipsCheked)
                    findNavController().popBackStack()
                }
            } else {
                Log.e("teste.Cheked.SBC", "Lista antiga: $listChipsCheked")
                val newList = mutableListOf("SA")
                viewLifecycleOwner.lifecycleScope.launch {
                    Log.e("teste", "Lista nova na fragment: $newList")
                    filterViewModel.saveChip(listChipsCheked)
                    findNavController().popBackStack()
                }
            }
        }

        binding.chipFilterSa.setOnClickListener {
            if (binding.chipFilterSa.isChecked) {
                listChipsCheked.add("SA")
            } else if (listChipsCheked.contains("SA")) {
                listChipsCheked.remove("SA")
            }
            Log.e("testeListener", "Lista nova na fragment: $listChipsCheked")
        }

        binding.chipFilterSbc.setOnClickListener {
            if (binding.chipFilterSbc.isChecked) {
                listChipsCheked.add("SBC")
            } else if (listChipsCheked.contains("SBC")) {
                listChipsCheked.remove("SBC")
            }
            Log.e("testeListener", "Lista nova na fragment: $listChipsCheked")
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ativarChipsPorId(idsParaAtivar: List<Int>) {
        chipsMap.values.forEach { it.isChecked = false }
        idsParaAtivar.forEach { id ->
            chipsMap[id]?.isChecked = true
        }
    }
}
