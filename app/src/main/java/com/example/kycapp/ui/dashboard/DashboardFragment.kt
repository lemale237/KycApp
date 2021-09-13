package com.example.kycapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    lateinit var img_agent_1 : ImageView;

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
        val root: View = binding.root

        val textView: TextView = binding.agentsEnregistrer

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

//le root de la navigation
        img_agent_1=root.findViewById(R.id.img_agent_1)


        return root


    }
//bouton fragment il manque le lien de la navigation vers la prochaine vue
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        img_agent_1.setOnClickListener {



        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}