package com.example.gradeup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gradeup.databinding.FragmentDayBinding
import com.example.gradeup.databinding.FragmentFilterBinding

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_DIA = "dia"

        fun newInstance(dia: String): DayFragment {
            return DayFragment().apply { // apply para configurar o objeto antes de retorná-lo
                arguments = Bundle().apply { // Bundle é um pacote que armazena pares de chave-valor e apply retorna o bundle depois de rodas o codigo
                    putString(ARG_DIA, dia) // agora posso acessar arguments na nova instancia da DayFragment
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dia = arguments?.getString(ARG_DIA)
        binding.title.text = dia

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}