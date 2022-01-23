package com.example.kycapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kycapp.ui.adapter.GridItemAdapter
import com.example.kycapp.api.AgentApi
import com.example.kycapp.databinding.FragmentDashboardBinding
import com.example.kycapp.entites.Agent
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {



    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    val model: DashboardViewModel by activityViewModels<DashboardViewModel>()


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
        // Navigation entre fragment
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = AgentApi()
        api.listAgent({
            Log.e("List OF AGENTS",it.toString())
            val listDeMesAgent =it
            val adapter = GridItemAdapter(listDeMesAgent as ArrayList<Agent>) {
                model.selectedAgent.value = it
            }
            val gridLayout = GridLayoutManager(requireContext(), 2)
            gridItems.layoutManager = gridLayout
            gridItems.adapter = adapter
            binding.gridItems.adapter=adapter
            binding.gridItems.layoutManager=gridLayout
        },{
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}