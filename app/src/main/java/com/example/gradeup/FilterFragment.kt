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

    private var listChipsCheked = mutableListOf<String>()
    private val chipsMap by lazy {
        mapOf(
            binding.chipFilterSa.tag to binding.chipFilterSa,
            binding.chipFilterSbc.tag to binding.chipFilterSbc,
            binding.chipFilterDaytime.tag to binding.chipFilterDaytime,
            binding.chipFilterNightly.tag to binding.chipFilterNightly,

            binding.checkboxBachareladoCienciaTecnologia.tag to binding.checkboxBachareladoCienciaTecnologia,
            binding.checkboxBachareladoBiotecnologia.tag to binding.checkboxBachareladoBiotecnologia,
            binding.checkboxBachareladoCienciaComputacao.tag to binding.checkboxBachareladoCienciaComputacao,
            binding.checkboxBachareladoCienciaDados.tag to binding.checkboxBachareladoCienciaDados,
            binding.checkboxBachareladoCienciasBiologicas.tag to binding.checkboxBachareladoCienciasBiologicas,
            binding.checkboxBachareladoEngenhariaAeroespacial.tag to binding.checkboxBachareladoEngenhariaAeroespacial,
            binding.checkboxBachareladoEngenhariaAmbientalUrbana.tag to binding.checkboxBachareladoEngenhariaAmbientalUrbana,
            binding.checkboxBachareladoEngenhariaBiomedica.tag to binding.checkboxBachareladoEngenhariaBiomedica,
            binding.checkboxBachareladoEngenhariaInformacao.tag to binding.checkboxBachareladoEngenhariaInformacao,
            binding.checkboxBachareladoEngenhariaEnergia.tag to binding.checkboxBachareladoEngenhariaEnergia,
            binding.checkboxBachareladoEngenhariaGestao.tag to binding.checkboxBachareladoEngenhariaGestao,
            binding.checkboxBachareladoEngenhariaAutomacaoRobotica.tag to binding.checkboxBachareladoEngenhariaAutomacaoRobotica,
            binding.checkboxBachareladoEngenhariaMateriais.tag to binding.checkboxBachareladoEngenhariaMateriais,
            binding.checkboxBachareladoFisica.tag to binding.checkboxBachareladoFisica,
            binding.checkboxBachareladoMatematica.tag to binding.checkboxBachareladoMatematica,
            binding.checkboxBachareladoNeurociencia.tag to binding.checkboxBachareladoNeurociencia,
            binding.checkboxBachareladoQuimica.tag to binding.checkboxBachareladoQuimica,

            binding.checkboxBachareladoCienciasHumanidades.tag to binding.checkboxBachareladoCienciasHumanidades,
            binding.checkboxBachareladoCienciasEconomicas.tag to binding.checkboxBachareladoCienciasEconomicas,
            binding.checkboxBachareladoFilosofia.tag to binding.checkboxBachareladoFilosofia,
            binding.checkboxBachareladoPlanejamentoTerritorial.tag to binding.checkboxBachareladoPlanejamentoTerritorial,
            binding.checkboxBachareladoPoliticasPublicas.tag to binding.checkboxBachareladoPoliticasPublicas,
            binding.checkboxBachareladoRelacoesInternacionais.tag to binding.checkboxBachareladoRelacoesInternacionais,

            binding.checkboxLicenciaturaCienciasHumanas.tag to binding.checkboxLicenciaturaCienciasHumanas,
            binding.checkboxLicenciaturaFilosofia.tag to binding.checkboxLicenciaturaFilosofia,
            binding.checkboxLicenciaturaHistoria.tag to binding.checkboxLicenciaturaHistoria,

            binding.checkboxLicenciaturaCienciasNaturaisExatas.tag to binding.checkboxLicenciaturaCienciasNaturaisExatas,
            binding.checkboxLicenciaturaCienciasBiologicas.tag to binding.checkboxLicenciaturaCienciasBiologicas,
            binding.checkboxLicenciaturaFisica.tag to binding.checkboxLicenciaturaFisica,
            binding.checkboxLicenciaturaMatematica.tag to binding.checkboxLicenciaturaMatematica,
            binding.checkboxLicenciaturaQuimica.tag to binding.checkboxLicenciaturaQuimica,

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

    private fun activateChipsById(idsToActivte: List<String>) {
        chipsMap.values.forEach { it.isChecked = false }
        idsToActivte.forEach { id ->
            chipsMap[id]?.isChecked = true
        }
    }

    private fun observeSelectedChips() {
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
                    listChipsCheked.add(chip.tag.toString())
                } else if (listChipsCheked.contains(chip.tag.toString())) {
                    listChipsCheked.remove(chip.tag.toString())
                }
            }
        }
    }

}
