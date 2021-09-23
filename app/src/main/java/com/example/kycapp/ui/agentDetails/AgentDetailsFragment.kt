package com.example.kycapp.ui.agentDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kycapp.R
import kotlinx.android.synthetic.main.agent_details_fragment.*

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

        // implémentation bouton retour
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        // TODO: Use the ViewModel
    }

}