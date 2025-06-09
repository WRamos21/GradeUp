package com.example.gradeup.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gradeup.FragmentPageAdapter
import com.example.gradeup.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = FragmentPageAdapter(this)

        binding.viewPager.adapter = pagerAdapter

        // TabLayoutMediator É uma classe que sincroniza automaticamente um TabLayout (as abas) com um ViewPager2, a função lambda é chamada para cada aba
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position -> // tab é uma aba e position é o numero dela
            tab.text = pagerAdapter.getDiaNome(position)
        }.attach() //Ativa a sincronização entre TabLayout e ViewPager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}