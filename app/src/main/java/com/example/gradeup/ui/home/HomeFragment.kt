package com.example.gradeup.ui.home

import android.os.Bundle
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

        //Configuracao recyclerView
        binding.recyclerviewSubjects.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewSubjects.adapter = adapter

        // Chamando recycler
        homeViewModel.getAllSubjects()
        setObserver()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver(){
        homeViewModel.subjects.observe(viewLifecycleOwner) {
            adapter.updateSubjects(it)
        }
        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}