package com.example.kycapp.ui.agentDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R

class AgentDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AgentDetailsFragment()
    }

    private lateinit var viewModel: AgentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.agent_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AgentDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}