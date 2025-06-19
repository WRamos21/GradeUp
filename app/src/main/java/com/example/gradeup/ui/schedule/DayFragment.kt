package com.example.gradeup.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradeup.data.local.selectedsubjects.SelectedSubjectEntity
import com.example.gradeup.data.local.subject.SubjectEntity
import com.example.gradeup.databinding.FragmentDayBinding
import com.example.gradeup.ui.adapter.SelectedSubjectsAdapter
import com.example.gradeup.ui.adapter.SubjectsAdapter

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

    private val dayViewModel: DayViewModel by viewModels()
    private lateinit var adapter: SelectedSubjectsAdapter

    companion object {
        private const val ARG_DAY = "day"

        fun newInstance(day: String): DayFragment {
            return DayFragment().apply { // apply para configurar o objeto antes de retorná-lo
                arguments = Bundle().apply { // Bundle é um pacote que armazena pares de chave-valor e apply retorna o bundle depois de rodas o codigo
                    putString(ARG_DAY, day) // agora posso acessar arguments na nova instancia da DayFragment
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
        val day = arguments?.getString(ARG_DAY)
//      binding.title.text = day
        dayViewModel.getSelectSubjectWithDayWeek(day.toString())

        //configuracao recyclerView
        binding.recyclerviewSubjectsSchedule.layoutManager = LinearLayoutManager(context)
        adapter = SelectedSubjectsAdapter {subject, position -> //Estou intanciando SubjectAdapter passando a funçao que ele receberá
            selectSubjectOnLongPress(subject, position)
        }
        binding.recyclerviewSubjectsSchedule.adapter = adapter
        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver(){
        dayViewModel.selectedSubjectsList.observe(viewLifecycleOwner){
            adapter.updateSelectedSubjects(it)
        }
    }

    private fun selectSubjectOnLongPress(subject: SelectedSubjectEntity, position: Int){
        Log.e("remove", "${subject.disciplina} removida")
        dayViewModel.deselectSubject(subject)
    }
}