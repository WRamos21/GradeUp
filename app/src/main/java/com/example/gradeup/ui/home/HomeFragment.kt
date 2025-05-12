package com.example.gradeup.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradeup.data.repository.SubjectRepository
import com.example.gradeup.databinding.FragmentHomeBinding
import com.example.gradeup.ui.adapter.SubjectsAdapter
import com.example.gradeup.ui.dashboard.DashboardViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter: SubjectsAdapter = SubjectsAdapter()

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configuracao recyclerView
        binding.recyclerviewSubjects.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewSubjects.adapter = adapter

        // Configuracao da editText Serach
        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                homeViewModel.getSubjects(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        // Chamando recycler
        homeViewModel.getSubjects("")
        setObserver()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver() {
        homeViewModel.subjects.observe(viewLifecycleOwner) {
            adapter.updateSubjects(it)
        }
        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}