package com.example.gradeup.ui.home

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradeup.R
import com.example.gradeup.data.local.subject.SubjectEntity
import com.example.gradeup.databinding.FragmentHomeBinding
import com.example.gradeup.ui.adapter.SubjectsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SubjectsAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuracao recyclerView
        binding.recyclerviewSubjects.layoutManager = LinearLayoutManager(context)

        adapter = SubjectsAdapter {subject, position -> //Estou intanciando SubjectAdapter passando a funçao que ele receberá
            selectSubjectOnLongPress(subject, position)
        }

        binding.recyclerviewSubjects.adapter = adapter

        // Configuracao da editText Search
        setEditSearchListener()

        // Chamando recycler
        setObserver()

        binding.buttonFilters.setOnClickListener {
            openFilterFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        val s = binding.editSearch.text.toString()
        if (s.isNotBlank()){
            homeViewModel.getAllSubjects(binding.editSearch.text.toString())
        } else {
            homeViewModel.getAllSubjects("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver() {
        homeViewModel.subjectsRemote.observe(viewLifecycleOwner) {
            adapter.updateSubjects(it)
        }
        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setEditSearchListener() {
        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotBlank()){
                    homeViewModel.getAllSubjects(s.toString())
                } else {
                    homeViewModel.getAllSubjects("")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun openFilterFragment(){
        findNavController().navigate(R.id.action_homeFragment_to_filterFragment)
    }

    private fun selectSubjectOnLongPress(subject: SubjectEntity, position: Int){
        Log.e("select", "${subject.disciplina} selecionada")
    }
}