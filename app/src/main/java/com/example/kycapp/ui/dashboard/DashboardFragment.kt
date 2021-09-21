package com.example.kycapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kycapp.GridItemAdapter
import com.example.kycapp.api.AgentApi
import com.example.kycapp.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {



    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = AgentApi()

       val listDeMesAgent = api.listAgent()

        val adapter = GridItemAdapter(listDeMesAgent)
        val gridLayout = GridLayoutManager(requireContext(), 2)
        gridItems.layoutManager = gridLayout
        gridItems.adapter = adapter

        binding.gridItems.adapter=adapter
        binding.gridItems.layoutManager=gridLayout


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}