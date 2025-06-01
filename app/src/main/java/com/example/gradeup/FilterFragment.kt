package com.example.gradeup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gradeup.databinding.FragmentFilterBinding
import kotlinx.coroutines.launch

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val filterViewModel: FilterViewModel by activityViewModels()

    private var listChipsCheked = mutableListOf<Int>()
    private val chipsMap by lazy {
        mapOf(
            binding.chipFilterSa.id to binding.chipFilterSa,
            binding.chipFilterSbc.id to binding.chipFilterSbc,
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

        activateChipsById(listChipsCheked)
        observeSelectedChips()
        setOnClikListenerAllChips()


        binding.iconCheck.setOnClickListener {
            filterViewModel.saveSelectedChips(listChipsCheked)
            findNavController().popBackStack()
        }

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun activateChipsById(idsToActivte: List<Int>) {
        chipsMap.values.forEach { it.isChecked = false }
        idsToActivte.forEach { id ->
            chipsMap[id]?.isChecked = true
        }
    }

    private fun observeSelectedChips(){
        viewLifecycleOwner.lifecycleScope.launch {
            filterViewModel.selectedChips.collect { chips ->
                listChipsCheked = chips.toMutableList()
                activateChipsById(listChipsCheked)
            }
        }
    }

    private fun setOnClikListenerAllChips() {
        chipsMap.values.forEach { chip ->
            chip.setOnClickListener {
                if (chip.isChecked) {
                    listChipsCheked.add(chip.id)
                } else if (listChipsCheked.contains(chip.id)) {
                    listChipsCheked.remove(chip.id)
                }
            }
        }
    }

}
